package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.AssignTime;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.Days;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.DisableDays;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.MonthSchedule;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.Psic;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.SidingDays;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.SmPmNt;
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
    String svBizDclsfCd;
    List<String> svBizDclsfCds; // wrkTypDtl
    // --------------------------------------------------

    String basePdCd;
    String pdctPdCd;
    String prtnrNo;
    String prtnrNo01;
    String prtnrNoBS01;
    String prtnrNoOwr01;
    //String empId;

    // --------------------------------------------------
    // 1: 설치
    // 2: B/S
    // 3: A/S
    // 4: 홈케어
    // 6: 반품처리
    // 7: 정보변경
    // 9: 삭제
    String hcrYn;// addGb asis: 홈케어면 2 아니면 1, tobe: y/n 으로 변경
    String hcrGb;
    // --------------------------------------------------
    // 접수구분
    // 1: 신규
    // 2: 수정
    // 3: 삭제
    String mtrStatCd; // dataStus
    // --------------------------------------------------

    //String basePdCdList;
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

    String lcst09;
    String vstDvCd; // 방문구분코드 VST_DV_CD
    String cstSvAsnNo;
    String sdingCombin; // 모종패키지 분리여부
    String sidingYn; // 모종여부
    String spayYn; // 일시불 여부
    String sowDay; // PAJONG_DAY
    String rpbLocaraCd;
    //boolean isHcr = false;

    String workTypeDtl = "";
    List<String> cntrSns = new ArrayList<>();
    List<String> basePdCds = new ArrayList<>();
    List<String> pdctPdCds = new ArrayList<>();
    List<String> sdingCombins = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////////////////////////
    List<String> offDays;
    List<SidingDays> sidingDays; // list2 abledays
    List<DisableDays> disableDays;
    Psic psic; // left_info
    List<AssignTime> assignTimes;

    List<Days> days;
    List<MonthSchedule> monthSchedules;

    List<SmPmNt> smTimes = new ArrayList<>();
    List<SmPmNt> amTimes = new ArrayList<>();
    List<SmPmNt> pmTimes1 = new ArrayList<>();
    List<SmPmNt> pmTimes2 = new ArrayList<>();
    List<SmPmNt> ntTimes = new ArrayList<>();
    ///////////////////////////////////////////////////////////////////////////////////////////////

}
