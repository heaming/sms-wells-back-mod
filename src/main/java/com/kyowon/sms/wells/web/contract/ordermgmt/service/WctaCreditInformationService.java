package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.nice.ivo.ONIC2_CBDO1004.response.*;
import com.kyowon.sflex.common.nice.service.NiceSafekeyService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCbboDefaultRegistDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCreditInformationDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaCreditInformationMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaCreditInformationService {

    private final WctaCreditInformationMapper mapper;
    private final NiceSafekeyService niceService;

    /**
     * 프로그램ID : W-SS-S-0007
     *  - E-SS-S-0225 와 동일
     * 신용정보 조회（14일이내사용）
     * @param dvo inqrDiv   조회구분(1.14일이내건,2.무조건 조회)
     *        dvo.safeKey   세이프키
     *        dvo.inqrUswy  조회용도(AS-IS KSUCDE)
     *        dvo.coCd      회사코드
     *        dvo.sellerEpNo 판매자사번
     *        dvo.dsmnEpNo  지국장사번
     *        dvo.sellDvCd  판매구분(1.EDU, 2.wells)
     *        dvo.cntrNo    계약번호
     *        dvo.cntrSn    계약일련번호
     *        dvo.cstDvCd   개인/법인구분(1.개인, 2.법인)
     * @return 신용조회결과코드(CINF_INQR_RS_CD 채무불이행여부) :
     *        1(정상),
     *        2(불량),
     *        3(해제),
     *        4,
     *        5(정보없음),
     *        A([오류]통신장애),
     *        B([오류]BUSY),
     *        C([오류]작업에러),
     *        D([오류]용도오류),
     *        E([오류]작업에러),
     *        F,
     *        Z([오류]응답시간초과)
     */

    @Transactional
    public String getCreditInformation(WctaCreditInformationDvo dvo) throws Exception{
        ValidAssert.hasText(dvo.getInqrDiv());
        ValidAssert.hasText(dvo.getSafeKey());
        ValidAssert.hasText(dvo.getInqrUswy());


        // 1. 파라미터 체크
        if (StringUtils.containsAny(dvo.getInqrUswy(), "11", "13")) {
            return "D";   //채무불이행여부 'D' 용도에러로 설정하고 종료
        }

        // 2. 조회구분이 1.14일 이내건 인 경우
        if("1".equals(dvo.getInqrDiv())){
            String resultCode = mapper.selectCinfInqrHistory(dvo);

            if(StringUtils.isNotBlank(resultCode)){
                return resultCode;
            }
        }

        // 3. 신규 신용정보 조회 및 테이블 INSERT 수행
        dvo.setKsqymd(DateUtil.getNowDayString());
        int resultInsert  = mapper.insertCinfInqr(dvo);
        if(resultInsert <= 0) {
            return "5";    //조회오류
        }

        // AS-IS 소스 사용
        // TODO : 입력값으로 회사코드(KSCOMP), 조회일자(KSQYMD), 나이스신용정보조회내역 일련번호(KSISEQ)를 받아야 함.(현재는 받지 않고 있음)
        // 대외계 신용정보 조회 I/F 호출
        Map<String,Object> updateMap = new HashMap<String, Object>();
        updateMap.put("KSCOMP", dvo.getCoCd());
        updateMap.put("KSQYMD", dvo.getKsqymd());
        updateMap.put("KSISEQ", dvo.getKsiseq());
        NiceCreditEvaluationResIvo niceLvo = niceService.getNiceCreditEvaludation();

        //통합정보
        CSHEAD HEADMap = niceLvo.getHEAD();
        CSDATA DATAMap = niceLvo.getDATA();
        String name = (StringUtils.defaultString(DATAMap.getFlnm())).substring(0, 5);
        String rplyCd = HEADMap.getRplyCd();

        updateMap.put("KSRCDE", rplyCd);    //응답코드
        updateMap.put("KSNAME", name); //성명

        //채무불이행 은행정보 : KSB10X ~ KSB50X
        List<BANK> BANKMap = (List<BANK>)niceLvo.getBANK();
        for(int i=5, totCnt=BANKMap.size(); i>totCnt; i--){
            BANKMap.add(new BANK());
        }
        int count = 1;
        for(BANK m: BANKMap){
            if(count == 6) break;
            updateMap.put("KSB" + (count) + "01", StringUtils.defaultString(m.getRsnDiv())); //채불구분
            updateMap.put("KSB" + (count) + "02", StringUtils.defaultString(m.getOvrdRsnCd())); //사유코드
            updateMap.put("KSB" + (count) + "03", StringUtils.defaultString(m.getOccrInttNm()).trim()); //발생기관명
            updateMap.put("KSB" + (count) + "04", StringUtils.defaultString(m.getOccrBrncNm()).trim()); //발생지점명
            updateMap.put("KSB" + (count) + "05", StringUtils.defaultString(m.getOccrInttBztpCd())); //발생업계코드
            updateMap.put("KSB" + (count) + "06", StringUtils.defaultString(m.getOccrInttCd())); //발생기관코드
            updateMap.put("KSB" + (count) + "07", StringUtils.defaultString(m.getOccrDt(), "0")); //발생일자
            updateMap.put("KSB" + (count) + "08", StringUtils.defaultString(m.getOffrDt(), "0")); //제공일자
            updateMap.put("KSB" + (count) + "09", StringUtils.defaultString(m.getRvctDt(), "0")); //해제일자
            updateMap.put("KSB" + (count) + "10", StringUtils.defaultString(m.getRgsnAmt(), "0")); //등록금액
            updateMap.put("KSB" + (count) + "11", StringUtils.defaultString(m.getDebtNnflAmt(), "0")); //채무불이행금액
            updateMap.put("KSB" + (count) + "12", StringUtils.defaultString(m.getRvctDiv())); //해제구분
            updateMap.put("KSB" + (count) + "13", StringUtils.defaultString(m.getCorcAskCd())); //정정청구
            updateMap.put("KSB" + (count) + "14", StringUtils.defaultString(m.getOvrdInfoDiv())); //연체공공정보
            count++;
        }

        //채무불이행 카드정보 : KSB60X ~ KSB00X
        List<CREDItem> CREDMap = niceLvo.getCRED();
        for(int i=5, totCnt=CREDMap.size(); i>totCnt; i--){
            CREDMap.add(new CREDItem());
        }
        count = 6;
        for(CREDItem m: CREDMap){
            if(count == 11) break;
            updateMap.put("KSB"+(count==10?0:count)+"01",""); //채불구분
            updateMap.put("KSB" + (count == 10 ? 0 : count) + "02", StringUtils.defaultString(m.getOvrdRsnCd())); //사유코드
            updateMap
                .put("KSB" + (count == 10 ? 0 : count) + "03", StringUtils.defaultString(m.getOccrInttNm()).trim()); //발생기관명
            updateMap
                .put("KSB" + (count == 10 ? 0 : count) + "04", StringUtils.defaultString(m.getOccrBrncNm()).trim()); //발생지점명
            updateMap.put("KSB" + (count == 10 ? 0 : count) + "05", StringUtils.defaultString(m.getOccrInttBztpCd())); //발생업계코드
            updateMap.put("KSB" + (count == 10 ? 0 : count) + "06", StringUtils.defaultString(m.getOccrInttCd())); //발생기관코드
            updateMap.put("KSB" + (count == 10 ? 0 : count) + "07", StringUtils.defaultString(m.getOccrDt(), "0")); //발생일자
            updateMap.put("KSB" + (count == 10 ? 0 : count) + "08", StringUtils.defaultString(m.getOffrDt(), "0")); //제공일자
            updateMap.put("KSB" + (count == 10 ? 0 : count) + "09", StringUtils.defaultString(m.getOffrDt(), "0")); //해제일자
            updateMap.put("KSB" + (count == 10 ? 0 : count) + "10", StringUtils.defaultString(m.getRgsnAmt(), "0")); //등록금액
            updateMap.put("KSB"+(count==10?0:count)+"11","0"); //채무불이행금액
            updateMap.put("KSB"+(count==10?0:count)+"12",""); //해제구분
            updateMap.put("KSB"+(count==10?0:count)+"13",""); //정정청구
            updateMap.put("KSB"+(count==10?0:count)+"14",""); //연체공공정보
            count++;
        }

        String bcnt = "0";  //채무불이행 미해제건수 (defalut :  없음)
        String rest = "";   //조회결과
        switch (rplyCd) {
            case "P000" ->{
                try{
                    bcnt = niceLvo.getCNTN().get(1).getBrfSrvcVl();
                    rest = "000000000".equals(bcnt)?"1":"2";
                }catch(Exception e){
                    rest = "1";
                }
            }
            case "B004" -> rest = "E";
            case "S003" -> rest = "4";
            default -> rest = "5";
        }

        updateMap.put("KSBCNT", bcnt);
        updateMap.put("KSREST", rest);

        //나이스신용정보조회내역(TB_IFIN_NICE_CINF_INQR_IZ) update 수행
        mapper.updateNiceCinfInqr(dvo, updateMap);

        //신용정보조회내역(TB_SSCT_CINF_INQR_IZ) insert 또는 update
        mapper.mergeCinfInqr(dvo);

        //채무불이행등록기본(TB_CBBO_DFLT_RGST_BAS) 조회
        WctaCbboDefaultRegistDvo cbboDvo = mapper.selectCbboDflt(dvo).orElseGet(WctaCbboDefaultRegistDvo::new);

        //채무불이행등록기본(TB_CBBO_DFLT_RGST_BAS) 데이터 set
        if(Integer.parseInt(bcnt) > 0){
            if(StringUtils.isEmpty(cbboDvo.getKwGrpCoCd())
                    || ("2".equals(cbboDvo.getDfltRgstDvCd()) && StringUtils.isNotEmpty(cbboDvo.getRlsDt()))){
                cbboDvo.setKwGrpCoCd(dvo.getCoCd());
                cbboDvo.setDfltMngtDvCd("1");
                cbboDvo.setSfkVal(dvo.getSafeKey());
                cbboDvo.setDfltRgstDvCd("2");
                cbboDvo.setCstNm(name);
                cbboDvo.setCinfInqrRsonCd(rplyCd);
                cbboDvo.setOcDt(dvo.getKsqymd());
            }
        }else{
            if("2".equals(cbboDvo.getDfltRgstDvCd()) && StringUtils.isEmpty(cbboDvo.getRlsDt())) {
                cbboDvo.setRlsDt(dvo.getKsqymd());
            }
        }

        //채무불이행등록기본(TB_CBBO_DFLT_RGST_BAS) insert 또는 update
        if(StringUtils.isNotEmpty(cbboDvo.getKwGrpCoCd())){
            mapper.updateCbboDflt(cbboDvo);
        }

        return rplyCd.equals("Z902")? "Z": rest;
    }
}
