package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbMembershipBulkChangeConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto.SearchCntrRes;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMembershipBulkChangeDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbMembershipBulkChangeMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbMembershipBulkChangeService {
    private final WctbMembershipBulkChangeMapper mapper;
    private final WctbMembershipBulkChangeConverter converter;

    public List<SearchRes> getMembershipBulkChanges(String cntrNo, String cntrSn, String rfdt) {
        return mapper.selectMembershipBulkChanges(cntrNo, cntrSn, rfdt);
    }

    public SearchCntrRes getMembershipBulkChangesContracts(String cntrNo, String cntrSn) {
        return mapper.selectMembershipBulkChangesContracts(cntrNo, cntrSn);
    }

    @Transactional
    public int saveMembershipBulkChange(
        WctbMembershipBulkChangeDto.SaveReq dto
    ) {
        int res = 0;
        List<WctbMembershipBulkChangeDto.SaveListReq> listDtos = dto.saveListReqs();
        WctbMembershipBulkChangeDto.SaveStatusReq saveStatusDto = dto.saveStatusReq();
        for (WctbMembershipBulkChangeDto.SaveListReq listDto : listDtos) {
            if (StringUtils.isAnyEmpty(listDto.cntrNo(), listDto.cntrSn())) { // 계약번호가 null 이면 continue
                continue;
            }
            WctbMembershipBulkChangeDvo dvo = converter.mapSaveListReqToWctbMembershipBulkChangeDvo(listDto);
            String bfchCn = "설치일자:" + dvo.getIstDt()
                + "|가입일자:" + dvo.getCntrCnfmDtm() +
                "|확정일자:" + dvo.getDtrmDate() +
                "|매출일자:" + dvo.getCntrPdStrtdt() +
                "|컨택코드:" + dvo.getCttRsCd() +
                "|수수료정액여부:" + dvo.getFeeFxamYn() +
                "|수수료인정기준금액:" + dvo.getFeeAckmtBaseAmt() +
                "|";
            // 데이터의 INSERT/UPDATE/유효시작일시/유효종료일시를 일관되게 맞추기 위해, 미리 조회해온다.
            WctbMembershipBulkChangeDvo dateTimeDvo = mapper.selectDateTime();
            dvo.setFstRgstDtm(dateTimeDvo.getFstRgstDtm());
            dvo.setFstRgstUsrId(dateTimeDvo.getFstRgstUsrId());
            dvo.setFstRgstPrgId(dateTimeDvo.getFstRgstPrgId());
            dvo.setFstRgstDeptId(dateTimeDvo.getFstRgstDeptId());
            dvo.setFnlMdfcDtm(dateTimeDvo.getFnlMdfcDtm());
            dvo.setFnlMdfcUsrId(dateTimeDvo.getFnlMdfcUsrId());
            dvo.setFnlMdfcPrgId(dateTimeDvo.getFnlMdfcPrgId());
            dvo.setFnlMdfcDeptId(dateTimeDvo.getFnlMdfcDeptId());

            if ("801".equals(saveStatusDto.procsDv())) { // 801:홈케어멤버십 서비스취소
                // 컨택코드(CTT_RS_CD 컨택결과코드) 업데이트 상태값 변경 계약취소로 변경
                dvo.setCttRsCd(saveStatusDto.cttCd());
                mapper.updateCntrDtlSvcCncl(dvo);
                mapper.updateCntrDetailStatChangeHist(dvo); // 계약상세상태변경이력 생성
                mapper.insertCntrDetailStatChangeHist(dvo);
                mapper.updateCntrDchHist(dvo); // 계약상세변경이력 생성
                mapper.insertCntrDchHist(dvo);
                // TODO : 마감파트의 실적처리 서비스 호출필요 > 추후 확인 필요 현재 반영 불가 20230713
            } else if ("802".equals(saveStatusDto.procsDv())) { // 802: 홈케어멤버십 가입일자 변경
                // 설치일자, 가입일자, 확정일자,매출일자 를 변경일자로 업데이트
                dvo.setSubsDt(saveStatusDto.subsDt());
                //이후 데이터 저장을 위해 dvo setting
                dvo.setIstDt(saveStatusDto.subsDt());
                dvo.setCntrCnfmDtm(saveStatusDto.subsDt());
                dvo.setDtrmDate(saveStatusDto.subsDt());
                dvo.setCntrPdStrtdt(saveStatusDto.subsDt());
                mapper.updateCntrWellsDtlHomeCareJDtCh(dvo); // 계약WELLS상세 설치일자 수정
                mapper.updateContractWellsDetailHist(dvo); // 계약WELLS상세 이력 생성
                mapper.insertContractWellsDetailHist(dvo);
                mapper.updateCntrDtlHomeCareJDtCh(dvo); // 계약상세 가입일자, 매출일자 수정
                mapper.updateCntrDchHist(dvo); // 계약상세변경이력 생성
                mapper.insertCntrDchHist(dvo);
                mapper.updateCntrBasHomeCareJDtCh(dvo); //계약기본 확정일자 수정
                mapper.updateCntrChHist(dvo); //계약기본이력 생성
                mapper.insertCntrChHist(dvo);
            } else if ("803".equals(saveStatusDto.procsDv())) { // 803:일반멤버십 가입일자 변경
                //첫영업일 조회
                String firstBznsYn = mapper.selectFirstBznsYn();

                UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
                String userId = session.getUserId();
                // 웰스파트 직원이 아니면 현재일이 현재월의 첫영업일이 아니면 '일반멤버십 가입날짜 변경가능일이 아닙니다'
                if ((!"31743".equals(userId) || !"31754".equals(userId) || !"31331".equals(userId)
                    || !"31348".equals(userId) || !"32859".equals(userId) || !"31685".equals(userId)
                    || !"37658".equals(userId) || !"37668".equals(userId) || !"37489".equals(userId)
                    || !"37730".equals(userId) || !"37614".equals(userId) || !"38935".equals(userId))
                    && "N".equals(firstBznsYn)) {
                    BizAssert.isTrue(false, "일반멤버십 가입날짜 변경가능일이 아닙니다");
                }
                //확정일자(CNTR_CNFM_DTM 계약확정일시) = 변경가입일의 전월말일로 , 가입일자(계약상품시작일자 CNTR_PD_STRTDT) = 변경가입일자로 업데이트,
                dvo.setLstmmLstDays(mapper.selectLstmmLstDays());
                dvo.setSubsDt(saveStatusDto.subsDt());

                //이후 데이터 저장을 위해 dvo setting
                dvo.setDtrmDate(mapper.selectLstmmLstDays()); // 확정일자(CNTR_CNFM_DTM 계약확정일시) = 변경가입일의 전월말일로
                dvo.setCntrCnfmDtm(saveStatusDto.subsDt()); // 가입일자(계약상품시작일자 CNTR_PD_STRTDT) = 변경가입일자로

                mapper.updateCntrBasGeMshJDtCh(dvo); //계약기본 확정일자 수정
                mapper.updateCntrChHist(dvo); //계약기본이력 생성
                mapper.insertCntrChHist(dvo);
                mapper.updateCntrDtlGeMshJDtCh(dvo); // 계약상세 가입일자 수정
                mapper.updateCntrDchHist(dvo); // 계약상세변경이력 생성
                mapper.insertCntrDchHist(dvo);

            } else if ("804".equals(saveStatusDto.procsDv())) { // 804 : 접수후 취소
                // 취소일자(계약상품종료일자) = 현재일자, 컨택코드 업데이트 계약취소로 상태값변경
                dvo.setCttRsCd(saveStatusDto.cttCd());
                mapper.updateCntrDtlreceiptCncl(dvo);
                mapper.updateCntrDetailStatChangeHist(dvo); // 계약상세상태변경이력 생성
                mapper.insertCntrDetailStatChangeHist(dvo);
                mapper.updateCntrDchHist(dvo); // 계약상세변경이력 생성
                mapper.insertCntrDchHist(dvo);
            } else if ("805".equals(saveStatusDto.procsDv())) { // 805 : 정액여부/기준수수료 변경
                // 수수료정액여부(FEE_FXAM_YN), 수수료인정기준금액(FEE_ACKMT_BASE_AMT) 업데이트
                if ("Y".equals(saveStatusDto.fxamYnCh())) { // 정액여부 = 'Y' 이면 정액구분에 'Y'
                    dvo.setFxamYnCh("Y");
                    dvo.setPdStdFee(saveStatusDto.pdStdFee()); // 기준수수료에 입력금액
                } else { //  아니면 ''
                    dvo.setFxamYnCh("");
                    dvo.setPdStdFee(saveStatusDto.pdStdFee()); // 기준수수료에 입력금액
                }
                mapper.updateCntrDtlStdFeeAmt(dvo);
                mapper.updateCntrDchHist(dvo); // 계약상세변경이력 생성
                mapper.insertCntrDchHist(dvo);
            }

            String cntrChAkCn = "설치일자:" + dvo.getIstDt()
                + "|가입일자:" + dvo.getCntrCnfmDtm() +
                "|확정일자:" + dvo.getDtrmDate() +
                "|매출일자:" + dvo.getCntrPdStrtdt() +
                "|컨택코드:" + dvo.getCttRsCd() +
                "|수수료정액여부:" + dvo.getFeeFxamYn() +
                "|수수료인정기준금액:" + dvo.getFeeAckmtBaseAmt() +
                "|";
            dvo.setChRson(saveStatusDto.chRson()); // 변경사유
            dvo.setProcsDv(saveStatusDto.procsDv()); // 처리구분
            dvo.setCntrChAkCn(cntrChAkCn);
            dvo.setBfchCn(bfchCn);
            mapper.insertCntrChRcpBas(dvo); // 계약변경접수기본 생성
            mapper.insertCntrChRcpDtl(dvo); // 계약변경접수상세 생성
            mapper.updateCntrChRcchHist(dvo); // 계약변경접수변경이력
            mapper.insertCntrChRcchHist(dvo);
            mapper.updateCntrChRcpDchHist(dvo); // 계약변경접수상세변경이력
            mapper.insertCntrChRcpDchHist(dvo);

        }
        return res;
    }
}
