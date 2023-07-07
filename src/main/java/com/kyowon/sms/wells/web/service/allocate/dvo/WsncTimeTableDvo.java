package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class WsncTimeTableDvo {

    // --------------------------------------------------
    // 1:설치
    // 2:B/S
    // 3:A/S
    // 4:홈케어
    @NotEmpty
    String svDvCd; // dataGb
    // --------------------------------------------------

    // --------------------------------------------------
    //W      : 웰스
    //K      : KSS
    //C      : CubicCC
    //P      : K-MEMBERS
    //I || E : 엔지니어
    //M      : 매니저
    @NotEmpty
    String chnlDvCd; // gbCd
    // --------------------------------------------------

    String sellDate;
    String newAdrZip;
    String cntrNo;
    String cntrSn;
    String inflwChnl; // inflwChnl

    // --------------------------------------------------
    //판매인 경우 '1110:신규설치' fix
    String svBizDclsfCd; // wrkTypDtl
    // --------------------------------------------------

    String basePdCd;
    String pdctPdCd;
    String prtnrNo;
    String prtnrNo01;
    String prtnrNoBS01;
    String prtnrNoOwr01;
    String empId;

    // --------------------------------------------------
    // 1: 설치
    // 2: B/S
    // 3: A/S
    // 4: 홈케어
    // 6: 반품처리
    // 7: 정보변경
    // 9: 삭제
    String hcrYn; // addGb
    String svBizHclsfCd; // addGb
    // --------------------------------------------------
    // 접수구분
    // 1: 신규
    // 2: 수정
    // 3: 삭제
    String mtrStatCd; // dataStus
    // --------------------------------------------------

    String basePdCdList;
    String exYn;
    String contDt;
    String wrkDt; // 접수일자(현재)
    String ogTpCd;
    String rcpOgTpCd;
    String userId;

    // --------------------------------------------------

    String returnUrl;
    String baseYm;
    String seq;
    String localGb;
    String mkCo;
    String vstDowValCd;
    String basePdNm;
    String pdctPdNm;
    String pdGrpCd;
    String pdGrpNm;
    String cntrDt;
    // --------------------------------------------------
    //법인격구분
    //1:개인
    //2:법인
    String copnDvCd; //lcwgub
    // --------------------------------------------------
    // 판매할인구분코드
    // 1: 일시불할인구분코드
    // 2: 렌탈할인구분코드
    // 3: 멤버십할인구분코드
    String sellDscDbCd; // lcetc3
    // --------------------------------------------------

    String lcst09; // ???
    String vstGb; // ???
    String cstSvAsnNo;
    String sdingCombin;
    String sidingYn;
    String spayYn;
    String sowDay; // PAJONG_DAY
    String rpbLocaraCd;
    boolean isHcr = false;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    List<String> offDays;
    List<WsncTimeTableDto.SidingDays> sidingDays; // list2 abledays
    List<WsncTimeTableDto.DisableDays> disableDays;
    WsncTimeTableDto.Psic psic; // left_info
    List<WsncTimeTableDto.AssignTime> assignTimes;

    List<WsncTimeTableDto.Days> days;
    List<WsncTimeTableDto.MonthSchedule> monthSchedules;

    List<WsncTimeTableDto.SmPmNt> smTimes = new ArrayList<>();
    List<WsncTimeTableDto.SmPmNt> amTimes = new ArrayList<>();
    List<WsncTimeTableDto.SmPmNt> pmTimes1 = new ArrayList<>();
    List<WsncTimeTableDto.SmPmNt> pmTimes2 = new ArrayList<>();
    List<WsncTimeTableDto.SmPmNt> ntTimes = new ArrayList<>();
    ///////////////////////////////////////////////////////////////////////////////////////////////

}
