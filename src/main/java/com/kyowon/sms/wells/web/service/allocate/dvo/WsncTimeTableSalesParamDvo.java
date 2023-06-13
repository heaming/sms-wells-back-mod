package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableSalesParamDvo {

    // --------------------------------------------------
    // 1:설치
    // 2:B/S
    // 3:A/S
    // 4:홈케어
    String svDvCd; // dataGb
    // --------------------------------------------------

    // --------------------------------------------------
    //W      : 웰스
    //K      : KSS
    //C      : CubicCC
    //P      : K-MEMBERS
    //I || E : 엔지니어
    //M      : 매니저
    String chnlDvCd; // gbCd
    // --------------------------------------------------

    String sellDate;
    String newAdrZip;
    String cntrNo;
    String cntrSn;
    String inGb;

    // --------------------------------------------------
    //판매인 경우 '1110:신규설치' fix
    String svBizDclsfCd; // wrkTypDtl
    // --------------------------------------------------

    String pdctPdCd;
    String prtnrNo01;
    String prtnrNoBS01;
    String prtnrNoOwr01;

    // --------------------------------------------------
    // 1: 설치
    // 2: B/S
    // 3: A/S
    // 4: 홈케어
    // 6: 반품처리
    // 7: 정보변경
    // 9: 삭제
    String addGb; // svBizHclsfCd
    // --------------------------------------------------

    String basePdCdList;
    String basePdCd;
    String exYn;
    String contDt;
    String wrkDt;
    String prtnrNo;
    String userId;

    // --------------------------------------------------
    //1: 신규
    //2: 수정
    //3: 삭제
    String dataStatCd; // dataStus
    // --------------------------------------------------

    String returnUrl;
    String localGb;
    String mkCo;
    String vstDowValCd;
    String copnDvCd; //lcwgub 법인격구분 1:개인, 2법인
    String sellDscDbCd; // lcetc3 판매할인구분코드 1: 일시불할인구분코드, 2: 렌탈할인구분코드, 3: 멤버십할인구분코드

    String lcst09; // ???
    String vstGb; // ???
}
