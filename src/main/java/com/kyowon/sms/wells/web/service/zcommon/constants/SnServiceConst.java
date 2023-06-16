package com.kyowon.sms.wells.web.service.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class SnServiceConst {
    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/service";
    public static final String INTERFACE_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/interfaces/";
    public static final String REST_INTERFACE_DOC_V1 = "[WSNI] Wells 서비스 인터페이스 REST API";
    public static final String REST_INTERFACE_URL_V1 = CommConst.REST_URL_V1 + "/interface/sms/wells/service";

    //입고유형코드 관련 상수 설정
    //구매입고(110)
    public static final String PRCHS_STR = "110";
    //기타입고(117)
    public static final String ETC_STR = "117";
    //정상입고(121)
    public static final String NOM_STR = "121";
    //물량배정(122)
    public static final String QOM_ASN = "122";
    //물량이동(123)
    public static final String QOM_MMT = "123";
    //반품입고(161)
    public static final String RTNGD_STR = "161";
    //반품외부입고(162)
    public static final String RTNGD_OTSD_STR = "162";
    //재고조정입고(181)
    public static final String STOC_CTR_STR = "181";
    //출고유형코드 관련 상수 설정
    // 정상출고(221)
    public static final String NOM_OSTR = "221";
    // 물량배정(222)
    public static final String QOM_ASN_OSTR = "222";
    // 물량이동(223)
    public static final String QOM_MMT_OSTR = "223";
    // 반품출고(내부)(261)
    public static final String RTNGD_OSTR_INSI = "261";
    // 반품출고(외부)(262)
    public static final String RTNGD_OSTR_OTSD = "262";
    // 외부출고(판매)(211)
    public static final String OTSD_OSTR_SELL = "211";
    // 외부출고(폐기)(212)
    public static final String OTSD_OSTR_DSU = "212";
    // 외부출고(작업)(213)
    public static final String OTSD_OSTR_WK = "213";
    // 외부출고(기타)(217)
    public static final String OTSD_OSTR_ETC = "217";
    // 외부출고(리퍼완료)(218)
    public static final String OTSD_OSTR_REFR = "218";
    // 재고조정출고(281)
    public static final String STOC_CTR_OSTR = "281";
    // 외부출고(기타)(292)
    public static final String STOC_ACINSP_OSTR = "292";
    // 이동입고(991)
    public static final String MMT_STR = "991";

    /**
     * OSTR_AK_TP_CD (출고요청유형코드)
     */
    // 정상출고(310)
    public static final String OSTR_AK_TP_CD_NOM_OSTR = "310";
    // 물량이동(320)
    public static final String OSTR_AK_TP_CD_QOM_MMT = "320";
    // 자동출고(330)
    public static final String OSTR_AK_TP_CD_AUTO_OSTR = "330";
    // 물량배정(360)
    public static final String OSTR_AK_TP_CD_QOM_ASN = "360";

    /**
     * SAP 관련 코드
     */
    // (주)교원프라퍼티
    public static final String SAP_CO_CD = "2000";
    // (주)교원프라퍼티파주물류(Wells)
    public static final String SAP_PLNT_CD = "2108";
    // 프라파주창고(Wells)
    public static final String SAP_SAVE_LCT_CD = "21082082";

    //자료상태코드(dtaStatCd) 값 세팅 (1:신규, 2: 수정, 3: 삭제)
    public static final String DTA_STAT_CD_NEW = "1";
    public static final String DTA_STAT_CD_MOD = "2";
    public static final String DTA_STAT_CD_DEL = "3";
    //입력채널구분코드(IN_CHNL_DV_CD) 값 세팅
    //CubigCC
    public static final String IN_CHNL_DV_CD_CST = "1";
    //KIWI-M
    public static final String IN_CHNL_DV_CD_SERVICE = "2";
    //KSS
    public static final String IN_CHNL_DV_CD_SALES = "3";
    //WEB
    public static final String IN_CHNL_DV_CD_WEB = "4";
    //K-MEMBERS
    public static final String IN_CHNL_DV_CD_KMEMBERS = "5";
    //자동생성
    public static final String IN_CHNL_DV_CD_AUTO = "9";

    // 서비스업무대분류코드 SV_BIZ_HCLSF_CD "4" (홈케어)
    public static final String SV_BIZ_HCLSF_CD_HOME_CARE = "4";

    // 서비스업무대분류코드 SV_BIZ_HCLSF_CD "9" (삭제)
    public static final String SV_BIZ_HCLSF_CD_DEL = "9";

    // 서비스업무중분류코드 SV_BIZ_MCLSF_CD "11" (설치)
    public static final String SV_BIZ_MCLSF_CD_IST = "11";

    // 서비스업무중분류코드 SV_BIZ_MCLSF_CD "41" (신규)
    public static final String SV_BIZ_MCLSF_CD_NEW = "41";

    // 상품그룹코드(PG_GRP_CD) "11" (웰스팜모종)
    public static final String PG_GRP_CD_WELLS_SEEDING = "11";

    // 상품그룹코드(PG_GRP_CD) "92" (웰스팜)
    public static final String PG_GRP_CD_WELLS_FARM = "92";

}
