package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaWellsVlCntrEynInqrDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaWellsVlCntrEynInqrMapper;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WctaWellsVlCntrEynInqrService {
    private final WctaWellsVlCntrEynInqrMapper mapper;

    public WctaWellsVlCntrEynInqrDvo getWellsVlCntrEynInqrs(WctaWellsVlCntrEynInqrDvo reqdvo) {
        // 리턴변수를 초기화한다.
        String rtnNwYn = ""; // Y:신규, N:기존
        String rtnCntrNo = "";
        Integer rtnCntrSn = 0;

        // 작업변수를 설정한다.
        Integer wkVlStrtDt = 0; // (작업)유효시작일
        String wkSlDt = ""; // (작업)매출일자
        Integer wkIntTpyeSlDt = 0; // (작업)정수형매출일자
        Integer wkRentalCstInfoExistYn = 0; // 렌탈고객정보유무
        Integer wkMshCstInfoExistYn = 0; // 멤버십고객정보유무

        // 현재년월을 변수에 담는다.
        String currYmdHms = DateUtil.todayNnow(); // yyyyMMddHHmmss
        String currDt = currYmdHms.substring(0, 8);

        // (작업)유효시작일을 구한다.
        try {
            wkVlStrtDt = Integer.parseInt(DateUtil.addMonths(currDt, -6));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // 렌탈고객정보조회를 가져온다.
        List<WctaWellsVlCntrEynInqrDvo> resultRentalCsts = mapper.selectRentalCsts(reqdvo);

        if (resultRentalCsts != null && resultRentalCsts.size() > 0) {
            String cntrNo = ""; // 계약번호
            int cntrSn = 0; // 계약일련번호
            String copnDvCd = ""; // 법인격구분코드
            int istmMcn = 0; // 할부개월수
            int frisuBfsvcPtrmN = 0; // 무상BS기간수
            String istDt = ""; // 설치일자
            String bryyMmdd = ""; // 생년월일
            String sexDvCd = ""; // 성별구분코드
            String ogId = ""; // 조직ID
            String slDt = ""; // 매출일자
            String canDt = ""; // 취소일자

            for (WctaWellsVlCntrEynInqrDvo dvo1 : resultRentalCsts) {
                cntrNo = dvo1.getCntrNo(); // 계약번호
                cntrSn = dvo1.getCntrSn(); // 계약일련번호
                copnDvCd = dvo1.getCopnDvCd(); // 법인격구분코드
                istmMcn = dvo1.getIstmMcn(); // 할부개월수
                frisuBfsvcPtrmN = dvo1.getFrisuBfsvcPtrmN(); // 무상BS기간수
                istDt = dvo1.getIstDt(); // 설치일자
                bryyMmdd = dvo1.getBryyMmdd(); // 생년월일
                sexDvCd = dvo1.getSexDvCd(); // 성별구분코드
                ogId = dvo1.getOgId(); // 조직ID
                slDt = dvo1.getSlDt(); // 매출일자
                canDt = dvo1.getCanDt(); // 취소일자

                // 만료일계산
                // (작업)매출일자에 (추출)매출일자를 대입한다.
                wkSlDt = slDt;
                // 매출일에서 (할부개월수 - 무상BS기간수) 만큼 개월수를 더하여 날짜를 계산한다.
                try {
                    wkSlDt = DateUtil.addMonths(wkSlDt, (istmMcn - frisuBfsvcPtrmN));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                wkIntTpyeSlDt = Integer.parseInt(wkSlDt);
                // 만료일자가 6개월 경과한 경우 제외((작업)유효시작일 보다 적은 경우)
                if (wkIntTpyeSlDt < wkVlStrtDt) {
                    continue;
                }
                // 취소일계산
                // (작업)매출일자에 (추출)취소일자를 대입한다.
                wkSlDt = canDt;
                wkIntTpyeSlDt = Integer.parseInt(wkSlDt);
                // 취소일이 6개월 경과한 경우 제외
                if (wkIntTpyeSlDt > 0 && wkIntTpyeSlDt < wkVlStrtDt) {
                    continue;
                }

                wkRentalCstInfoExistYn += 1; // 렌탈고객정보유무
                // 추출계약번호보관
                rtnCntrNo = cntrNo;
                rtnCntrSn = cntrSn;
            }
        } else {
            // 멤버십고객정보를 가져온다.
            List<WctaWellsVlCntrEynInqrDvo> resultMshCsts = mapper.selectMshCsts(reqdvo);

            if (resultMshCsts != null && resultMshCsts.size() > 0) {
                String cntrNo = ""; // 계약번호
                int cntrSn = 0; // 계약일련번호
                String copnDvCd = ""; // 법인격구분코드
                String cntrCnfmDtm = ""; // 계약확정일시
                String cntrPdEnddt = ""; // 탈퇴일자
                String bryyMmdd = ""; // 생년월일
                String sexDvCd = ""; // 성별구분코드
                String ogId = ""; // 조직ID
                String cntrCanDtm = ""; // 계약취소일시
                String canDt = ""; // 취소일자
                String slDt = ""; // 매출일자
                for (WctaWellsVlCntrEynInqrDvo dvo2 : resultMshCsts) {
                    cntrNo = dvo2.getCntrNo(); // 계약번호
                    cntrSn = dvo2.getCntrSn(); // 계약일련번호
                    copnDvCd = dvo2.getCopnDvCd(); // 법인격구분코드
                    cntrCnfmDtm = dvo2.getCntrCnfmDtm(); // 계약확정일시
                    cntrPdEnddt = dvo2.getCntrPdEnddt(); // 탈퇴일자
                    bryyMmdd = dvo2.getBryyMmdd(); // 생년월일
                    sexDvCd = dvo2.getSexDvCd(); // 성별구분코드
                    ogId = dvo2.getOgId(); // 조직ID
                    cntrCanDtm = dvo2.getCntrCanDtm(); // 계약취소일시
                    slDt = dvo2.getSlDt(); // 매출일자
                    canDt = dvo2.getCanDt(); // 취소일자

                    // 탈퇴일계산
                    // (작업)매출일자 = (추출)탈퇴일자
                    wkSlDt = cntrPdEnddt;
                    wkIntTpyeSlDt = Integer.parseInt(wkSlDt);
                    // 탈퇴일이 6개월 경과한 경우 제외
                    if (wkIntTpyeSlDt > 0 && wkIntTpyeSlDt < wkVlStrtDt) {
                        continue;
                    }
                    // 취소일계산
                    // (작업)매출일자 = (추출)취소일자
                    wkSlDt = canDt;
                    wkIntTpyeSlDt = Integer.parseInt(wkSlDt);
                    // 취소일이 6개월 경과한 경우 제외
                    if (wkIntTpyeSlDt > 0 && wkIntTpyeSlDt < wkVlStrtDt) {
                        continue;
                    }
                    wkMshCstInfoExistYn += 1; // 멤버십고객정보유무
                    // 추출한계약번호보관
                    rtnCntrNo = cntrNo;
                    rtnCntrSn = cntrSn;
                }
            }
        }

        // 신규여부를 판단한다.
        if (wkRentalCstInfoExistYn > 0) { // 렌탈고객정보유무
            rtnNwYn = "N"; // 기존
        } else if (wkMshCstInfoExistYn > 0) { // 멤버십고객정보유무
            rtnNwYn = "N"; // 기존
        } else if (wkRentalCstInfoExistYn == 0 && wkMshCstInfoExistYn == 0) {
            rtnNwYn = "Y"; // 신규
        }

        // 결과를 리턴한다.
        WctaWellsVlCntrEynInqrDvo resdvo = new WctaWellsVlCntrEynInqrDvo();
        resdvo.setNwYn(rtnNwYn);
        resdvo.setCntrNo(rtnCntrNo);
        resdvo.setCntrSn(rtnCntrSn);

        return resdvo;
    }
}
