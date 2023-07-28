package com.kyowon.sms.wells.web.contract.changeorder.service;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchRes;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbRentalBulkChangeConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalBulkChangeDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbRentalBulkChangeMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbRentalBulkChangeService {

    private final WctbRentalBulkChangeMapper mapper;
    private final WctbRentalBulkChangeConverter converter;

    public List<SearchRes> getRentalBulkChanges(SearchReq dto) {
        return converter.mapAllRentalBulkChangeDvoToSearchRes(mapper.selectRentalBulkChanges(dto));
    }

    public WctbRentalBulkChangeDto.SearchCntrRes getBulkChangeContractsInfs(String cntrNo, String cntrSn) {
        return mapper.selectBulkChangeContractsInfs(cntrNo, cntrSn);
    }

    @Transactional
    public int saveRentalBulkChange(
        WctbRentalBulkChangeDto.SaveReq dto
    ) {
        int res = 0;
        List<WctbRentalBulkChangeDto.SaveListReq> listDtos = dto.saveListReqs();
        WctbRentalBulkChangeDto.SaveStatusReq saveStatusDto = dto.saveStatusReq();
        for (WctbRentalBulkChangeDto.SaveListReq listDto : listDtos) {
            //변경전내용
            String bfchCn = "";
            // 계약변경요청내용
            String cntrChAkCn = "";

            // 계약번호가 null 이면 오류
            BizAssert.isTrue(!StringUtils.isAnyEmpty(listDto.cntrNo(), listDto.cntrSn()), "MSG_ALT_SVE_ERR");
            WctbRentalBulkChangeDvo dvo = converter.mapSaveListReqToWctbRentalBulkChangeDvo(listDto);

            // 데이터의 INSERT/UPDATE/유효시작일시/유효종료일시를 일관되게 맞추기 위해, 미리 조회해온다.
            WctbRentalBulkChangeDvo dateTimeDvo = mapper.selectDateTime();
            dvo.setFstRgstDtm(dateTimeDvo.getFstRgstDtm());
            dvo.setFstRgstUsrId(dateTimeDvo.getFstRgstUsrId());
            dvo.setFstRgstPrgId(dateTimeDvo.getFstRgstPrgId());
            dvo.setFstRgstDeptId(dateTimeDvo.getFstRgstDeptId());
            dvo.setFnlMdfcDtm(dateTimeDvo.getFnlMdfcDtm());
            dvo.setFnlMdfcUsrId(dateTimeDvo.getFnlMdfcUsrId());
            dvo.setFnlMdfcPrgId(dateTimeDvo.getFnlMdfcPrgId());
            dvo.setFnlMdfcDeptId(dateTimeDvo.getFnlMdfcDeptId());

            //변경유형 세팅
            dvo.setProcsDv(saveStatusDto.procsDv());
            if ("601".equals(saveStatusDto.procsDv())) { // 렌탈취소원복
                //변경전내용
                bfchCn = "계약상세상태코드:" + dvo.getCntrDtlStatCd() + "|";

                // 이력조회 후 업데이트
                dvo.setCntrDtlStatCd(mapper.selectBfCntrDtlStatCd(dvo));
                BizAssert.isTrue(StringUtils.isNotEmpty(dvo.getCntrDtlStatCd()), "MSG_ALT_SVE_ERR");
                // 대상:계약상세 컬럼:계약상세상태코드
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 상태변경이력생성
                res = mapper.updateCntrDtlStatChangeHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDtlStatChangeHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "계약상세상태코드:" + dvo.getCntrDtlStatCd() + "|";
                ;
            } else if ("604".equals(saveStatusDto.procsDv())) { // 설치월면제
                //변경전내용
                bfchCn = "설치월청구방식유형코드:" + dvo.getIstMmBilMthdTpCd() + "|";

                res = mapper.updateCntrWellsDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                // 계약WELLS상세변경이력 생성
                res = mapper.updateCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                if (StringUtils.isNotEmpty(dvo.getIstDt())) { // 설치일자가 있을때
                    // TODO : 설치일자가 있을때 매출조정 업데이트 - 해당테이블이 수납일출금 파트 테이블
                }

                // 계약변경요청내용
                cntrChAkCn = "설치월청구방식유형코드:2|";
            } else if ("605".equals(saveStatusDto.procsDv())) { // 의무기간
                //변경전내용
                bfchCn = "약정기간:" + dvo.getStplPtrm() + "|";

                // 대상:계약상세 컬럼:입력값 변경의무기간 => 약정기간
                dvo.setDutyPtrmEnd(saveStatusDto.dutyPtrmEnd());
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "약정기간:" + dvo.getDutyPtrmEnd() + "|";
            } else if ("607".equals(saveStatusDto.procsDv())) { // 접수취소
                //변경전내용
                bfchCn = "계약상세상태코드:" + dvo.getCntrDtlStatCd() + "|";

                // 대상:계약상세 컬럼:계약상세상태코드 =303:계약취소
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 상태변경이력생성
                res = mapper.updateCntrDtlStatChangeHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDtlStatChangeHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "계약상세상태코드:303|";
                // TODO : 렌탈중지 정보 업데이트 - 해당테이블이 수납일출금파트 테이블
            } else if ("608".equals(saveStatusDto.procsDv())) { // 인정실적 금액변경
                //변경전내용
                bfchCn = "인정실적금액:" + dvo.getAckmtPerfAmt() + "|";
                //대상:계약상세  컬럼:입력 인정실적 => 인정실적금액
                dvo.setPdAccRslt(saveStatusDto.pdAccRslt());
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "인정실적금액:" + dvo.getPdAccRslt() + "|";
            } else if ("609".equals(saveStatusDto.procsDv())) { // 수수료 기준가격변경
                //변경전내용
                bfchCn = "수수료인정기준금액:" + dvo.getFeeAckmtCt() + "|";

                // 대상:계약상세 컬럼:입력 수수료기준가격 => 수수료인정기준금액
                dvo.setFeeAckmtBaseAmt(saveStatusDto.feeAckmtBaseAmt());
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "수수료인정기준금액:" + dvo.getFeeAckmtBaseAmt() + "|";
            } else if ("611".equals(saveStatusDto.procsDv())) { // 수수료 인정건수 변경
                //변경전내용
                bfchCn = "수수료인정건수:" + dvo.getFeeAckmtCt() + "|";

                // 대상:계약상세 컬럼:입력 수수료인정건수 => 수수료인정건수
                dvo.setFeeAckmtCnt(saveStatusDto.feeAckmtCnt());
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "수수료인정건수:" + dvo.getFeeAckmtCnt() + "|";
            } else if ("612".equals(saveStatusDto.procsDv())) { // 렌탈료DC 변경
                if (StringUtils.isNotEmpty(dvo.getIstDt())) {
                    //TODO : 설치일자가 있을때 WELLS매출월마감 렌탈할인금액 업데이트 - 해당테이블이 마감파트 테이블
                }
                //TODO : 매출 확정 KWLINK(14213) 호출 - LC50C01
                continue;
            } else if ("613".equals(saveStatusDto.procsDv())) { // 미관리제품 설치일자 변경
                //변경전내용
                bfchCn = "설치일자:" + dvo.getIstDt() + "|";

                // 대상 : 계약WELLS상세 컬럼 : 설치일자
                dvo.setParamIstDt(saveStatusDto.paramIstDt());
                res = mapper.updateCntrWellsDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                // 계약WELLS상세변경이력 생성
                res = mapper.updateCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "설치일자:" + dvo.getParamIstDt() + "|";
            } else if ("615".equals(saveStatusDto.procsDv())) { // 시리얼 번호 변경
                //변경전내용
                bfchCn = "시리얼번호:" + dvo.getPdctIdno() + "|";

                // 입력 시리얼번호로 업데이트
                res = mapper.updatePdctIdnoOstrIz(dvo); // 제품고유번호출고내역 업데이트
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "시리얼번호:" + dvo.getSerialNo() + "|";
            } else if ("616".equals(saveStatusDto.procsDv())) { // 법인 코로나 렌탈중지
                //변경전내용
                bfchCn = "약정기간:" + dvo.getStplPtrm()
                    + "|계약기간:" + dvo.getCntrPtrm()
                    + "|계약상품종료일자:" + dvo.getCntrPdEnddt()
                    + "|";

                // 등록기간에 기등록 기간이 있는지 확인, 있으면 -> 해당기간에 등록한 데이터가 있습니다
                BizAssert.isTrue("N".equals(mapper.selectRegyn(dvo)), "해당기간에 등록한 데이터가 있습니다");
                // 누적등록기간 체크, 12 개월을 넘으면 -> 중지기간이 누적 12개월을 넘습니다
                BizAssert.isTrue(mapper.selectRegMm(dvo) <= 12, "중지기간이 누적 12개월을 넘습니다");
                // TODO : 렌탈중지 정보 업데이트 - 해당테이블이 수납일출금파트 테이블
                // STOPPERD = (중지종료월 - 중지시작월) + 1
                int stopperd = Integer.parseInt(dvo.getStpPrdStrtYm()) - Integer.parseInt(dvo.getStpPrdEndYm()) + 1;
                // 약정기간
                dvo.setStplPtrm(String.valueOf(Integer.parseInt(dvo.getStplPtrm()) + stopperd));
                // 계약기간
                dvo.setCntrPtrm(String.valueOf(Integer.parseInt(dvo.getCntrPtrm()) + stopperd));
                // 계약상품종료일자 : CNTR_PD_ENDDT =  ADD_MONTHS(CNTR_PD_ENDDT, STOPPERD)
                dvo.setCntrPdEnddt(String.valueOf(Integer.parseInt(dvo.getCntrPdEnddt()) + (stopperd * 100)));

                //계약상세 업데이트
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                // TODO : 실적정보 업데이트 - 해당테이블이 마감파트 테이블

                // 계약변경요청내용
                cntrChAkCn = "약정기간:" + dvo.getStplPtrm()
                    + "|계약기간:" + dvo.getCntrPtrm()
                    + "|계약상품종료일자:" + dvo.getCntrPdEnddt()
                    + "|";
            } else if ("617".equals(saveStatusDto.procsDv())) { // 법인 코로나 렌탈중지 취소
                //변경전내용
                bfchCn = "약정기간:" + dvo.getStplPtrm()
                    + "|계약기간:" + dvo.getCntrPtrm()
                    + "|계약상품종료일자:" + dvo.getCntrPdEnddt()
                    + "|";

                // 중지취소년월일 기간내 등록된 중지건 확인, 없으면 -> 해당기간에 등록한 데이터가 없습니다
                BizAssert.isTrue("Y".equals(mapper.selectRegyn(dvo)), "해당기간에 등록한 데이터가 없습니다");
                // TODO : 렌탈중지 정보 업데이트 - 해당테이블이 수납일출금파트 테이블
                // 약정기간
                dvo.setStplPtrm(String.valueOf(Integer.parseInt(dvo.getStplPtrm()) - 1));
                // 계약기간
                dvo.setCntrPtrm(String.valueOf(Integer.parseInt(dvo.getCntrPtrm()) - 1));
                // 계약상품종료일자
                dvo.setCntrPdEnddt(String.valueOf(Integer.parseInt(dvo.getCntrPdEnddt()) - 100));

                //계약상세 업데이트
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "약정기간:" + dvo.getStplPtrm()
                    + "|계약기간:" + dvo.getCntrPtrm()
                    + "|계약상품종료일자:" + dvo.getCntrPdEnddt()
                    + "|";
                // TODO : 실적정보 업데이트 - 해당테이블이 마감파트 테이블
            } else if ("618".equals(saveStatusDto.procsDv())) { // 수수료 정액여부 변경
                //변경전내용
                bfchCn = "수수료정액여부:" + dvo.getFeeFxam() + "|";

                //대상:계약상세  컬럼:그리드 입력값(Y/N)  => 수수료정액여부
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "수수료정액여부:" + dvo.getFeeFxamYn() + "|";
            } else if ("619".equals(saveStatusDto.procsDv())) { // 프로모션 렌탈료 할인
                //변경전내용
                bfchCn = "할인금액:" + dvo.getCntrDscAmt() + "|";

                // 입력 할인개월 => TODO:매핑없음
                // 입력 할인금액 => 계약상세.할인금액(DSC_AMT)
                //대상:계약상세  컬럼:할인금액
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "할인금액:" + dvo.getDscAmt() + "|";
            } else if ("620".equals(saveStatusDto.procsDv())) { // 렌탈 전월 취소
                boolean masterUpdYn = false; // 마스터 변경 하지 않음
                // TODO : 잠시보류
                // 1.데이터 백업
                // 2. LC5000P UPDATE
                // 3. LC3250 UPDATE
                // 4.렌탈 취소 삭제호출
            } else if ("621".equals(saveStatusDto.procsDv())) { // (모종)인정실적금액변경
                //변경전내용
                bfchCn = "인정실적금액:" + dvo.getAckmtPerfAmt() + "|";

                //대상:계약상세  컬럼:입력 인정실적 => 인정실적금액
                dvo.setPdAccRslt(saveStatusDto.pdAccRslt());
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "인정실적금액:" + dvo.getPdAccRslt() + "|";
            } else if ("622".equals(saveStatusDto.procsDv())) { // (모종)수수료기준가격변경
                //변경전내용
                bfchCn = "수수료인정기준금액:" + dvo.getFeeAckmtCt() + "|";

                //대상:계약상세  컬럼:입력 수수료기준가격 => 수수료인정기준금액
                dvo.setFeeAckmtBaseAmt(saveStatusDto.feeAckmtBaseAmt());
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "수수료인정기준금액:" + dvo.getFeeAckmtBaseAmt() + "|";
            } else if ("623".equals(saveStatusDto.procsDv())) { // 매출(BS) 중지 해제
                boolean masterUpdYn = false; // 마스터(LC3100P) 변경 하지 않음
                //		TODO : 실적업데이트 필요 => 마감파트 서비스 필요.
            } else if ("624".equals(saveStatusDto.procsDv())) { // 포인트플러스 강제 맵핑
                //변경전내용
                bfchCn = "제휴사코드:" + dvo.getAlncmpCd()
                    + "|제휴사계약식별값:" + dvo.getAlncmpCntrDrmVal()
                    + "|선납적용구분코드:" + dvo.getPrmApyDvCd()
                    + "|";

                // 라이프 고객코드 상태확인
                WctbRentalBulkChangeDvo resultDvo = mapper.selectLifeCstCdStatus(dvo);
                BizAssert.isFalse(resultDvo == null, "라이프 주문의 고객코드를 확인하세요!");
                BizAssert.isFalse(!"41".equals(resultDvo.getKletc6()), "라이프 주문의 제휴구분을 확인하세요!");
                BizAssert.isFalse(
                    "2".equals(resultDvo.getKlflg8Gubun1()) || "2".equals(resultDvo.getKlflg8Gubun2()),
                    "라이프 주문의 제휴고객코드를 확인하세요!"
                );
                BizAssert.isFalse(
                    Integer.parseInt(StringUtils.isEmpty(dvo.getKlrevy()) ? "0" : dvo.getKlrevy()) > 0,
                    "라이프 주문이 철회 상태입니다!"
                );
                BizAssert.isFalse(
                    Integer.parseInt(StringUtils.isEmpty(dvo.getKlcany()) ? "0" : dvo.getKlcany()) > 0,
                    "라이프 주문이 취소 상태입니다!"
                );
                // 입력 라이프고객코드로 기존 매핑존재 확인
                dvo.setKlyear(resultDvo.getKlyear());
                dvo.setKlcode(resultDvo.getKlcode());
                List<WctbRentalBulkChangeDvo> mappingDvoLists = mapper.selectOtherLifeCstCdMapping(dvo);
                // 라이프고객코드 있으면 매핑된 다른계약조회하고 없으면 매핑할 대상조회
                for (WctbRentalBulkChangeDvo mappingDvoList : mappingDvoLists) {
                    // 데이터의 INSERT/UPDATE/유효시작일시/유효종료일시를 일관되게 맞추기 위해, 미리 조회해온다.
                    mappingDvoList.setFstRgstDtm(dvo.getFstRgstDtm());
                    mappingDvoList.setFstRgstUsrId(dvo.getFstRgstUsrId());
                    mappingDvoList.setFstRgstPrgId(dvo.getFstRgstPrgId());
                    mappingDvoList.setFstRgstDeptId(dvo.getFstRgstDeptId());
                    mappingDvoList.setFnlMdfcDtm(dvo.getFnlMdfcDtm());
                    mappingDvoList.setFnlMdfcUsrId(dvo.getFnlMdfcUsrId());
                    mappingDvoList.setFnlMdfcPrgId(dvo.getFnlMdfcPrgId());
                    mappingDvoList.setFnlMdfcDeptId(dvo.getFnlMdfcDeptId());
                    // 조회데이타 있으면 제휴정보 리셋
                    // 기존 다른 맵핑 등록건 CLEAR 처리(대상건이 있는 경우만 처리-없는 경우는 SKIP)
                    res = mapper.updateCntrDtlClear(mappingDvoList);
                    BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                    //계약상세 이력생성
                    res = mapper.updateCntrDchHist(mappingDvoList);
                    BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                    res = mapper.insertCntrDchHist(mappingDvoList);
                    BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                }
                // 신규 맵핑 라이프 코드로 강제 설정
                res = mapper.updateCntrDtl(dvo); // 계약상세 업데이트
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                res = mapper.updateCntrWellsDtl(dvo);//계약WELLS상세 업데이트
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                // 계약WELLS상세변경이력 생성
                res = mapper.updateCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                // TODO : 라이프 데이타 1차범위 대상 아님, 라이프 주문상태 조회만 가능

                // 계약변경요청내용
                cntrChAkCn = "제휴사코드:" + resultDvo.getAlncmpCd()
                    + "|제휴사계약식별값:" + resultDvo.getAlncmpCntrDrmVal()
                    + "|선납적용구분코드:2|";
            } else if ("625".equals(saveStatusDto.procsDv())) { // 플래너상조제휴 강제 맵핑
                //변경전내용
                bfchCn = "제휴사코드:" + dvo.getAlncmpCd()
                    + "|제휴사계약식별값:" + dvo.getAlncmpCntrDrmVal()
                    + "|선납적용구분코드:" + dvo.getPrmApyDvCd()
                    + "|";

                // TODO : 라이프 데이타 1차범위 대상 아님, 라이프 주문상태 조회만 가능. 계약에 입력값 매핑만 가능할듯(확인필요)
                // 라이프 고객코드 상태확인
                WctbRentalBulkChangeDvo resultDvo = mapper.selectLifeCstCdStatus(dvo);
                BizAssert.isFalse(resultDvo == null, "라이프 주문의 고객코드를 확인하세요!");
                BizAssert.isFalse(!"51".equals(resultDvo.getKletc6()), "라이프 주문의 제휴구분을 확인하세요!");
                BizAssert.isFalse(
                    Integer.parseInt(dvo.getKlflg8()) > 0 &&
                        ("2".equals(resultDvo.getKlflg8Gubun1()) || "2".equals(resultDvo.getKlflg8Gubun2())),
                    "라이프 주문의 제휴고객코드를 확인하세요!"
                );
                BizAssert.isFalse(Integer.parseInt(dvo.getKlrevy()) > 0, "라이프 주문이 철회 상태입니다!");
                BizAssert.isFalse(Integer.parseInt(dvo.getKlcany()) > 0, "라이프 주문이 취소 상태입니다!");

                res = mapper.insertAcmpalCntrIz(dvo); // 관계사제휴계약내역 생성
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                if (StringUtils.isNotEmpty(dvo.getLifeCstCd2())) {
                    dvo.setLifeCstCd(dvo.getLifeCstCd2());
                    // 라이프 고객코드 상태확인
                    resultDvo = mapper.selectLifeCstCdStatus(dvo);
                    BizAssert.isFalse(resultDvo == null, "라이프 주문2의 고객코드를 확인하세요!");
                    BizAssert.isFalse(!"51".equals(resultDvo.getKletc6()), "라이프 주문2의 제휴구분을 확인하세요!");
                    BizAssert.isFalse(
                        Integer.parseInt(dvo.getKlflg8()) > 0 &&
                            ("2".equals(resultDvo.getKlflg8Gubun1()) || "2".equals(resultDvo.getKlflg8Gubun2())),
                        "라이프 주문2의 제휴고객코드를 확인하세요!"
                    );
                    BizAssert.isFalse(
                        Integer.parseInt(StringUtils.isEmpty(dvo.getKlrevy()) ? "0" : dvo.getKlrevy()) > 0,
                        "라이프 주문2이 철회 상태입니다!"
                    );
                    BizAssert.isFalse(
                        Integer.parseInt(StringUtils.isEmpty(dvo.getKlcany()) ? "0" : dvo.getKlcany()) > 0,
                        "라이프 주문2이 취소 상태입니다!"
                    );

                    // 라이프고객코드, 라이프고객코드2 두가지입력이 있으면 두번 입력
                    res = mapper.insertAcmpalCntrIz(dvo); // 관계사제휴계약내역 생성
                    BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                }
                // 기존 다른 맵핑 등록건 조회
                dvo.setKlyear(resultDvo.getKlyear());
                dvo.setKlcode(resultDvo.getKlcode());
                List<WctbRentalBulkChangeDvo> mappingDvoLists = mapper.selectOtherLifeCstCdMapping(dvo);
                for (WctbRentalBulkChangeDvo mappingDvoList : mappingDvoLists) {
                    BizAssert.isFalse(StringUtils.isNotEmpty(mappingDvoList.getAlncmpCd()), "다른 제휴 상품 접수된 주문입니다. ");
                }

                res = mapper.updateCntrDtl(dvo); // 계약상세 업데이트
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                res = mapper.updateCntrWellsDtl(dvo);//계약WELLS상세 업데이트
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                // 계약WELLS상세변경이력 생성
                res = mapper.updateCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                // TODO : 라이프 주문 정보 수정
                // 계약변경요청내용
                cntrChAkCn = "제휴사코드:" + resultDvo.getAlncmpCd()
                    + "|제휴사계약식별값:" + resultDvo.getAlncmpCntrDrmVal()
                    + "|선납적용구분코드:2|";
            } else if ("626".equals(saveStatusDto.procsDv())) { // (모종)수수료 인정건수 변경 feeAckmtCnt
                //변경전내용
                bfchCn = "수수료인정건수:" + dvo.getFeeAckmtCt() + "|";

                //대상:계약상세  컬럼:입력 수수료인정건수 => 수수료인정건수
                dvo.setFeeAckmtCnt(saveStatusDto.feeAckmtCnt());
                res = mapper.updateCntrDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약변경요청내용
                cntrChAkCn = "수수료인정건수:" + dvo.getFeeAckmtCnt() + "|";
            } else if ("711".equals(saveStatusDto.procsDv())) { // BS업체구분값 변경
                // 계약WELLS상세.BS업체구분코드(BFSVC_BZS_DV_CD) = 입력값
                // 계약WELLS상세.조달업체구분코드(SPLY_BZS_DV_CD) = 입력값
                res = mapper.updateCntrWellsDtl(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

                // 계약WELLS상세변경이력 생성
                res = mapper.updateCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
                res = mapper.insertCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");
            }
            //변경전내용
            dvo.setBfchCn(bfchCn);

            // 계약변경요청내용 - 비고 추가
            if (StringUtils.isNotEmpty(dvo.getNote())) { // 비고가 널이 아니면
                cntrChAkCn = cntrChAkCn + "비고:" + dvo.getNote() + "|"; // 비고 추가
            }
            dvo.setCntrChAkCn(cntrChAkCn);

            //계약변경접수기본 생성
            res = mapper.insertCntrChRcpBas(dvo);
            BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

            // 계약변경접수변경이력 생성
            res = mapper.insertCntrChRcchHist(dvo);
            BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

            //계약변경접수상세 생성
            res = mapper.insertCntrChRcpDtl(dvo);
            BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

            // 계약변경접수상세변경이력 생성
            res = mapper.insertCntrChRcpDchHist(dvo);
            BizAssert.isTrue(res == 1, "MSG_ALT_SVE_ERR");

        }
        return res;
    }
}
