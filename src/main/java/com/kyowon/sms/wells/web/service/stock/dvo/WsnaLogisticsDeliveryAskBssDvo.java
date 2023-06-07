package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0091 물류배송요청 서비스 (HQ 생성로직) (TB_IFIN_SPP_BAS_SEND_ETXT-배송기본송신전문)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-31
 */

@Getter
@Setter
public class WsnaLogisticsDeliveryAskBssDvo {

    // SAP플랜트코드 (SAP_PLNT_CD)
    private String sapPlntCd;

    // 출고요청번호 (OSTR_AK_NO)
    private String ostrAkNo;

    // 주문번호 (LLORNO)
    private String llorno;

    // 업무구분 (LLPRTP)
    private String llprtp;

    // 신청유형 (LLAPTP)
    private String llaptp;

    // 신청조직구분 (LLAPOC)
    private String llapoc;

    // 신청조직명 (LLAPOCNM)
    private String llapocnm;

    // 신청소속 (LLAPOR)
    private String llapor;

    // 신청소속명 (LLAPORNM)
    private String llapornm;

    // 신청자코드 (LLAPPC)
    private String llappc;

    // 신청자성명 (LLAPPN)
    private String llappn;

    // 주문완료일 (LLORDT)
    private String llordt;

    // 배송코드 (LLSHCS)
    private String llshcs;

    // 배송지코드 (LLRCOR)
    private String llrcor;

    // 배송지명 (LLRCORNM)
    private String llrcornm;

    // I/F전송번호 (LLIFNO)
    private String llifno;

    // 등록일 (LLINDT)
    private String llindt;

    // KSS수신완료 (LLSKTR)
    private String llsktr;

    // 송신자용필드 (LLSDFL)
    private String llsdfl;

    // 전송대상여부 (LLKSSU)
    private String llkssu;

    // 전송여부 (TRS_YN)
    private String trsYn = "N";

    // 전송일시 (TRS_DTM)
    private String trsDtm;

    // 데이터삭제여부 (DTA_DL_YN)
    private String dtaDlYn = "N";

}
