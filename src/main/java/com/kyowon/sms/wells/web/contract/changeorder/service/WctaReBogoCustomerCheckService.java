package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaReBogoCustomerCheckDvo;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaReBogoCustomerCheckResultDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctaReBogoCustomerCheckMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaReBogoCustomerCheckService {
    private final WctaReBogoCustomerCheckMapper mapper;
    WctaReBogoCustomerCheckResultDvo result = new WctaReBogoCustomerCheckResultDvo();
    public String rsltMchnChgYn; //기기변경여부
    public String rsltPdCd; //W-SS-S-0009 상품코드 결과값
    public String rsltPmotCd; //W-SS-S-0009 상품코드 결과값
    public String rsltPdTpCd; //W-SS-S-0009 상품유형 결과값
    public String chekPmotCd; //프로모션코드 체크값
    public String rsltCanDt; //W-SS-S-0009 취소일
    public String rsltReqdDt; //W-SS-S-0009 철거일
    public String rsltRcvrDt; //W-SS-S-0009 회수일
    public String rsltApyTpCd; //적용유형
    public String rsltRgstAmt; //등록비
    public String rsltRgstAmtDsc; //등록비할인
    public String rsltRntlDsc1; //렌탈할인１
    public String rsltRntlDsc2; //렌탈할인2
    public String rsltFreeMnts; //무료개월
    public String rsltPrcNoCnt; //가격차수（채널）

    public List<WctaReBogoCustomerCheckResultDvo> getRerentalBogoInqr(WctaReBogoCustomerCheckDvo dvo) {
        //체크1. 필수입력값　체크
        //기변일때 계약번호 미존재 시 오류
        if (StringUtil.isEmpty(dvo.getMchnChgYn()) && ((StringUtil.isEmpty(dvo.getOjCntrNo())
            && StringUtil.isEmpty(dvo.getBaseCntrNo())))) {
            BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CNTR_NO"); //계약번호를 확인해주세요.
        }

        //계약번호 존재 시 또는 계약상세번호존재 시 모두 존재
        if ((StringUtil.isEmpty(dvo.getBaseCntrNo()) && StringUtil.isNotEmpty(dvo.getBaseCntrDtlNo()))
            || (StringUtil.isEmpty(dvo.getOjCntrNo()) && StringUtil.isNotEmpty(dvo.getOjCntrDtlNo()))
            || (StringUtil.isNotEmpty(dvo.getBaseCntrNo()) && StringUtil.isEmpty(dvo.getBaseCntrDtlNo()))
            || (StringUtil.isNotEmpty(dvo.getOjCntrNo()) && StringUtil.isEmpty(dvo.getOjCntrDtlNo()))) {
            BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CNTR_NO"); //계약번호를 확인해주세요.
        }

        //접수일자 필수
        BizAssert.hasText(dvo.getRcpDt(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_RCPDT"}); //접수일자를 확인하세요.

        //접수시분초 필수
        BizAssert.hasText(dvo.getRcpHms(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_RCPT_HHMMSS"}); //접수시분초를 확인하세요.

        //렌탈기간1 필수
        BizAssert.hasText(dvo.getRntlPrd(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_RENT_PRD_MN"}); //렌탈기간을 확인하세요.

        //ＡＳ기간 필수
        BizAssert.hasText(dvo.getAsPrd(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_TXT_MSG_AS_PTRM"}); //ＡＳ기간을 확인하세요.

        //상품코드 필수
        BizAssert.hasText(dvo.getPdCd(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_PRDT_CODE"}); //상품코드를 확인하세요.

        //상품분류ID 필수(상품유형/상품분류)
        BizAssert.hasText(dvo.getPdTpCd(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_PRDT_TYPE"}); //상품유형을 확인하세요.
        BizAssert.hasText(dvo.getPdClsfCd(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_PRDT_CATE"}); //상품분류를 확인하세요.

        //할인구분 필수
        BizAssert.hasText(dvo.getDscApyDtlCd(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_PD_DC_CLASS"}); //할인구분을 확인하세요.

        //할인유형 필수 (할인구분 직원구매(2)가 아닌경우 유형 필수)
        if (!"202".equals(dvo.getDscApyDtlCd()) && StringUtil.isEmpty(dvo.getDscApyDrmVal())) {
            BizAssert.hasText(dvo.getDscApyDrmVal(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_DISC_CODE"}); //할인유형을 확인하세요.
        }

        //판매자사번 필수
        BizAssert.hasText(dvo.getSellPrtnrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_PTNR_NO"}); //판매자사번을 확인하세요.

        //계약구분 필수
        BizAssert.hasText(dvo.getCntrGubn(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_DV"}); //계약구분을 확인하세요.

        //고객번호 필수
        BizAssert.hasText(dvo.getCntrCstNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CST_NO"}); //고객번호를 확인하세요.

        //체크2. 기본 반환값 설정
        rsltRgstAmt = dvo.getRgstAmt(); //등록비
        rsltPmotCd = dvo.getPmotCd(); //프로모션코드

        //체크3. 화면선택 프로모션인 경우 선택값 그대로 적용
        if (Integer.parseInt(rsltPmotCd) >= 81 && Integer.parseInt(rsltPmotCd) <= 89) {
            rsltApyTpCd = "1"; //적용유형
            //가격차수　설정
            if ("202".equals(dvo.getDscApyDtlCd())) {
                rsltPrcNoCnt = "03";
            } else if ("68".equals(dvo.getAlncmpCd())) {
                rsltPrcNoCnt = "04";
            } else {
                rsltPrcNoCnt = "01";
            }
        }

        //체크4. 재렌탈，１＋１은 프로모션코드 CLEAR (02:재렌탈, 18:특별할인(자동1), 17:특별할인(자동2))
        if (Arrays.asList(new String[] {"02", "18", "71"}).contains(dvo.getPmotCd())) {
            rsltPmotCd = "";
        }

        //체크5. １＋１변경 후 기변，법인，８－３이외건은　프로모션 CLEAR (프로모션코드 03일 경우 1+1)
        if (Integer.parseInt(dvo.getRcpDt()) >= 20160701 && "03".equals(dvo.getPmotCd())
            && "Y".equals(dvo.getMchnChgYn())) {
            chekPmotCd = "";
            rsltPmotCd = "";
        }

        //체크6. 등록비 할인 설정
        //재렌탈 프로모션 대상여부 검사
        //재렌탈 제외（법인－소상공인지원 프로모션）
        if ("201".equals(dvo.getDscApyDtlCd()) && "04".equals(dvo.getPmotCd())) {
            return null;
        }

        //기변 정보 조회(동일 제품군 체크)
        if ("Y".equals(dvo.getMchnChgYn())) {
            //W-SS-S-0009 (KSS기기변경 정보검색)호출하여 상품분류 ID와 조회결과의 상품유형 체크
            if (StringUtil.isNotEmpty(rsltPdTpCd)) {
                if (!rsltPdTpCd.equals(dvo.getPdTpCd())) {
                    rsltMchnChgYn = "Y"; //W-SS-S-0009 결과값 상태
                    rsltPdCd = "";
                }
            }
        }

        //재렌탈 프로모션 대상여부 검사
        if ("4390".equals(rsltPdCd)
            && Integer.parseInt(dvo.getRcpDt()) >= 20200427) {} else if ("Y".equals(rsltMchnChgYn)) {
            return null;
        }

        //일정이 지난 코드 삭제(02:재렌탈, 03:1+1, 18:특별할인(자동1), 71:특별할인(자동2))
        if (Arrays.asList(new String[] {"  ", "02", "03", "18", "71"}).contains(dvo.getPmotCd())) {
            //할인구분 1(법인) && 할인유형(2:2년, 3:3년)
            if ("201".equals(dvo.getDscApyDtlCd())
                && Arrays.asList(new String[] {"2", "3"}).contains(dvo.getDscApyDrmVal())) {
                rsltPmotCd = "";
            } //기기변경여부 Y && 상품유형(1:정수기, 2:비데, 3:공기청청기, 4{연수기, B:커피머신)
            else if ("Y".equals(dvo.getMchnChgYn())
                && Arrays.asList(new String[] {"1", "2", "3", "4", "B"}).contains(dvo.getPdTpCd())) {
                rsltPmotCd = "";

                //기변전 대상계약의 가입유형이 렌탈(2)인 경우
                if ("2".equals(dvo.getSellTpCd())) {
                    if ((Integer.parseInt(dvo.getRcpDt()) >= 20140401)
                        && Arrays.asList(new String[] {"15", "16", "19"}).contains(dvo.getMchnChTpCd())) {} else {
                        rsltPmotCd = "";
                        return null;
                    }
                }

                //대상(기변전정보) 취소일/철거일/회수일을 +14일 계산(W-SS-S-0009 개발 후 적용)
                //１４일내 취소／철거／회수건 재렌탈 허용(2016.12.19 ~)
                if (Integer.parseInt(dvo.getRcpDt()) >= 20161219) {
                    rsltCanDt = getCalcDate(rsltCanDt, 14);
                    rsltReqdDt = getCalcDate(rsltReqdDt, 14);
                    rsltRcvrDt = getCalcDate(rsltRcvrDt, 14);
                }

                //취소제외(기기변경유형코드가 기변취소의 경우 접수일자와 비교)
                if (Integer.parseInt(rsltCanDt) > 0) {
                    if ("19".equals(dvo.getMchnChTpCd())) { //19(57개월~60개월)
                        if (Integer.parseInt(rsltCanDt) < Integer.parseInt(dvo.getRcpDt())) {
                            rsltPmotCd = "";
                            return null;
                        }
                    } else if (Integer.parseInt(rsltCanDt) < Integer.parseInt(dvo.getRcpDt())) {
                        rsltPmotCd = "";
                        return null;
                    }
                }

                //철거제외
                if (Integer.parseInt(rsltReqdDt) > 0) {
                    if (Integer.parseInt(rsltReqdDt) < Integer.parseInt(dvo.getRcpDt())) {
                        rsltPmotCd = "";
                        return null;
                    }
                }

                //자가회수제외
                if (Integer.parseInt(rsltRcvrDt) > 0) {
                    if (Integer.parseInt(rsltRcvrDt) < Integer.parseInt(dvo.getRcpDt())) {
                        rsltPmotCd = "";
                        return null;
                    }
                }

                //３년이상 약정 체크
                if (("201".equals(dvo.getDscApyDtlCd())
                    && Arrays.asList(new String[] {"1", "3", "4", "5", "6"}).contains(dvo.getDscApyDrmVal()))
                    || ("203".equals(dvo.getDscApyDtlCd())
                        && Arrays.asList(new String[] {"1", "3", "4", "5", "6"}).contains(dvo.getDscApyDrmVal()))
                    || ("205".equals(dvo.getDscApyDtlCd()))
                    || ("206".equals(dvo.getDscApyDtlCd())
                        && Arrays.asList(new String[] {"1", "3", "4", "5", "6"})
                            .contains(dvo.getDscApyDrmVal()))) {} else {
                    rsltPmotCd = "";
                    return null;
                }

                //재렌탈 프로모션 적용여부 체크(As-Is LC31C171)
                String linkCheck = mapper.selectReRntlPmotApyYn(dvo);
                if (linkCheck.equals("Y")) {
                    rsltPmotCd = "";
                    return null;
                }
            }
        }

        //체크7. 특별할인 설정
        //특별할인 대상인 경우 재렌탈보다 우선적용
        if (Integer.parseInt(dvo.getRcpDt()) >= 20191001 && Integer.parseInt(dvo.getRcpDt()) <= 99991231) {
            //특별할인 프로모션 대상여부 체크（프로모션기준정보）
            //특별할인 대상이 아닌 경우 재렌탈 적용가능

            //재렌탈 제외（에듀제휴，Ｗ머니，구몬，더오름）
            if (Arrays.asList(new String[] {"21", "22", "30", "33"}).contains(dvo.getAlncmpCd())) {
                return null;
            }

            //프로모션코드가 적용된 경우는 특별할인 제외(18:특별할인(자동1), 20:특별할인(선택), 71:특별할인(자동2))
            if (!Arrays.asList(new String[] {"18", "20", "71", ""}).contains(dvo.getPmotCd())) {
                return null;
            }

            //W-SS-S-0024 wells 상품 렌탈 프로모션 적용 조회 연계
            //1. W-SS-S-0024 파라미터 셋팅
            //2. W-SS-S-0024 호출
            //3. W-SS-S-0024 결과값 체크
        }

        //체크8 １＋１여부 설정 및 차수설정
        //환경가전 １＋１ 적용 조회
        if (!"02".equals(rsltPmotCd)) {
            //상품코드 6090 커피머신（ＫＷ－Ｅ０１Ｗ１） && 계약구분 2:법인 && 기기변경여부
            if ("6090".equals(dvo.getPdCd()) && "2".equals(dvo.getCntrGubn()) && "".equals(dvo.getMchnChgYn())) {
                //１＋１ 프로모션 검사(P06W1(4090)제외 / S01W0(4410, 4420)만 적용 제외)
                chekPmotCd = "";
                if ("4090".equals(dvo.getPdCd()) || (Integer.parseInt(dvo.getRcpDt()) >= 20141016
                    && Arrays.asList(new String[] {"4410", "4420"}).contains(dvo.getPdCd()))) {
                    return null;
                }

                //리퍼제품 제외
                if (Arrays.asList(new String[] {"4072", "4151"}).contains(dvo.getPdCd())) {
                    return null;
                }

                //일반 ３년 약정만 적용
                if (("206".equals(dvo.getDscApyDtlCd())
                    && Arrays.asList(new String[] {"1", "3", "4", "5", "6"}).contains(dvo.getDscApyDrmVal())
                    && !"6090".equals(dvo.getPdCd()))
                    || ((Arrays.asList(new String[] {"201", "206"}).contains(dvo.getDscApyDtlCd()))
                        && Arrays.asList(new String[] {"3", "5"}).contains(dvo.getDscApyDrmVal())
                        && "6090".equals(dvo.getPdCd()))) {} else {
                    return null;
                }

                //기기변경여부 제외
                if ("Y".equals(dvo.getMchnChgYn())) {
                    return null;
                }

                //취소요청건 제외(컨택기본@컨택결과코드)
                if (Arrays.asList(new String[] {"90", "91", "92", "93", "94"}).contains(dvo.getCttRsltCd())) {
                    return null;
                }

                //접수일자로 적용종료일을 설정
                int rsltApyEndDate = calcYearMonthDc(dvo.getRcpDt());

                //동일제품군 검사(W-SS-S-0037 wells 렌탈/멤버십 사용고객 건수 조회 As-Is LC31C051)
                //결과값 CNT1-정수기수(1), CNT2-비데건수(2), CNT3-청정기수(3), CNT4-연수기건수(4), CNT5-음처건수(5), CNT6-커피머신(B), CNT7-전기레인지(C), CNT8-재배기(E)
                //결과 체크 1 : 신규 고객일 경우 제외
                //결과 체크 2 : 동일제품군 있으면 제외
                //결과 체크 3 : 전기레인지 R01B1(4390)은 정수기에서 추가만 인정
                //결과 체크 4 : 가격구조 변경 적용
                if (Integer.parseInt(dvo.getRcpDt()) >= 20160301) {
                    chekPmotCd = "Y";
                    rsltPmotCd = "03";
                    return null;
                }
                //결과 체크 5 :
                //상풐코드 4070,4060, 4340은 ２０만원 구간 적용＋２개월 무료(무료개월설정은 FIELD-MMON-RTN에서 설정)
                if (Arrays.asList(new String[] {"4060", "4070", "4340", "4190"}).contains(dvo.getPdCd())) {
                    rsltPmotCd = "03"; //프로모션코드
                    rsltApyTpCd = "3"; //적용유형 １:일반, 2:등록할인, 3:렌탈할인, 4:무료개월, 5:등록＋렌탈할인
                    rsltFreeMnts = "0"; //무료개월 적용유형 4일 경우
                    rsltRntlDsc1 = "1000"; //렌탈할인１ 적용유형 3,5일 경우
                    rsltRntlDsc2 = "1000"; //렌탈할인2 적용유형 3,5일 경우
                    if ("4340".equals(dvo.getPdCd())) {
                        rsltRntlDsc2 = "0"; //렌탈할인2 적용유형 3,5일 경우
                        return null;
                    }
                }
                //상풐코드 4640 && 접수일자 20131209 이후일 경우 ３０만원 구간 적용＋렌탈료 할인
                if ("4640".equals(dvo.getPdCd()) && Integer.parseInt(dvo.getRcpDt()) >= 20131209) {
                    rsltPmotCd = "03"; //프로모션코드
                    rsltApyTpCd = "3"; //적용유형 １:일반, 2:등록할인, 3:렌탈할인, 4:무료개월, 5:등록＋렌탈할인
                    rsltFreeMnts = "0"; //무료개월 적용유형 4일 경우
                    rsltRntlDsc1 = "2500"; //렌탈할인１ 적용유형 3,5일 경우
                    rsltRntlDsc2 = "1000"; //렌탈할인2 적용유형 3,5일 경우
                    return null;
                }
                //상풐코드 4660(P16W1) && 접수일자 20141126 이후일 경우
                if ("4660".equals(dvo.getPdCd()) && Integer.parseInt(dvo.getRcpDt()) >= 20141126) {
                    rsltPmotCd = "03"; //프로모션코드
                    rsltApyTpCd = "3"; //적용유형 １:일반, 2:등록할인, 3:렌탈할인, 4:무료개월, 5:등록＋렌탈할인
                    rsltFreeMnts = "0"; //무료개월 적용유형 4일 경우
                    rsltRntlDsc1 = "1000"; //렌탈할인１ 적용유형 3,5일 경우
                    rsltRntlDsc2 = "1000"; //렌탈할인2 적용유형 3,5일 경우
                    return null;
                }
                //상풐코드 4690(P15W1) && 접수일자 20150302 이후일 경우
                if ("4690".equals(dvo.getPdCd()) && Integer.parseInt(dvo.getRcpDt()) >= 20150302) {
                    rsltPmotCd = "03"; //프로모션코드
                    rsltApyTpCd = "3"; //적용유형 １:일반, 2:등록할인, 3:렌탈할인, 4:무료개월, 5:등록＋렌탈할인
                    rsltFreeMnts = "0"; //무료개월 적용유형 4일 경우
                    rsltRntlDsc1 = "2500"; //렌탈할인１ 적용유형 3,5일 경우
                    rsltRntlDsc2 = "2500"; //렌탈할인2 적용유형 3,5일 경우
                    return null;
                }
                //할인구간이 이미 적용되어 있으면，무료개월만 설정
                if ((("1".equals(dvo.getPdTpCd()))
                    && (!Arrays.asList(new String[] {"4121", "4790", "4060", "4070"}).contains(dvo.getPdCd()))
                    && ("300000".equals(rsltRgstAmt))
                    && ("300000".equals(rsltRgstAmtDsc)))
                    || (("1".equals(dvo.getPdTpCd()))
                        && (Arrays.asList(new String[] {"4121", "4060", "4070"}).contains(dvo.getPdCd()))
                        && ("200000".equals(rsltRgstAmt))
                        && ("200000".equals(rsltRgstAmtDsc)))
                    || (("2".equals(dvo.getPdTpCd()))
                        && ("200000".equals(rsltRgstAmt))
                        && ("200000".equals(rsltRgstAmtDsc)))
                    || (("3".equals(dvo.getPdTpCd()))
                        && ("4330".equals(dvo.getPdCd()))
                        && ("300000".equals(rsltRgstAmt))
                        && ("300000".equals(rsltRgstAmtDsc)))
                    || (("3".equals(dvo.getPdTpCd()))
                        && ("4340".equals(dvo.getPdCd()))
                        && ("200000".equals(rsltRgstAmt))
                        && ("200000".equals(rsltRgstAmtDsc)))) {
                    rsltPmotCd = "03"; //프로모션코드
                    rsltApyTpCd = "4"; //적용유형 １:일반, 2:등록할인, 3:렌탈할인, 4:무료개월, 5:등록＋렌탈할인
                    rsltFreeMnts = "02"; //무료개월 적용유형 4일 경우
                    return null;
                } else if (("5".equals(dvo.getPdTpCd()))
                    && ("4510".equals(dvo.getPdCd()))
                    && ("300000".equals(rsltRgstAmt))
                    && ("300000".equals(rsltRgstAmtDsc))) {
                    rsltPmotCd = "03"; //프로모션코드
                    rsltApyTpCd = "3"; //적용유형 １:일반, 2:등록할인, 3:렌탈할인, 4:무료개월, 5:등록＋렌탈할인
                    rsltFreeMnts = "0"; //무료개월 적용유형 4일 경우
                    rsltRntlDsc1 = "3500"; //렌탈할인１ 적용유형 3,5일 경우
                    rsltRntlDsc2 = "3500"; //렌탈할인2 적용유형 3,5일 경우
                    return null;
                }
                chekPmotCd = "Y";
                rsltPmotCd = "03"; //프로모션코드
            }
        }
        //결과값 셋팅
        result.setPmotCd(rsltPmotCd);
        result.setApyTpCd(rsltApyTpCd);
        result.setRgstAmt(rsltRgstAmt);
        result.setRgstAmtDsc(rsltRgstAmtDsc);
        result.setRntlDsc1(rsltRntlDsc1);
        result.setRntlDsc2(rsltRntlDsc2);
        result.setFreeMnts(rsltFreeMnts);
        result.setPrcNoCnt(rsltPrcNoCnt);

        return (List<WctaReBogoCustomerCheckResultDvo>)result;
    }

    private String getCalcDate(String baseDate, int days) {
        int iYear = Integer.parseInt(baseDate.substring(0, 4));
        int iMonth = Integer.parseInt(baseDate.substring(4, 6));
        int iDay = Integer.parseInt(baseDate.substring(6, 8));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate dt = LocalDate.of(iYear, iMonth, iDay);

        return dtf.format(dt.plusDays(days));
    }

    private int calcYearMonthDc(String baseDate) {
        int iYear = Integer.parseInt(baseDate.substring(0, 4));
        int iMonth = Integer.parseInt(baseDate.substring(4, 6));

        YearMonth yearMonth = YearMonth.of(iYear, iMonth);

        return yearMonth.lengthOfMonth();
    }
}
