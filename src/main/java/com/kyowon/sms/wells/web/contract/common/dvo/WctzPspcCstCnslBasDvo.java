package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * 가망고객상담기본 DVO
 *
 * @author joobro
 * @since 2023-07-07
 */
@Getter
@Setter
public class WctzPspcCstCnslBasDvo {
    private String pspcCstCnslId; /*가망고객상담ID*/
    private String pspcCstId; /*가망고객ID*/
    private String pspcCstCnslTpCd; /*가망고객상담유형코드*/
    private Integer aplcSn; /*신청일련번호*/
    private String cnslPsbStrtDtm; /*상담가능시작일시*/
    private String cnslPsbEndDtm; /*상담가능종료일시*/
    private String alncmpCd; /*제휴사코드*/
    private String inrtPdHclsfId; /*관심상품대분류ID*/
    private String inrtPdMclsfId; /*관심상품중분류ID*/
    private String inrtPdLclsfId; /*관심상품소분류ID*/
    private String inrtPdDclsfId; /*관심상품세분류ID*/
    private String inrtPdCd; /*관심상품코드*/
    private String pspcCstFtfYn; /*가망고객대면여부*/
    private String vstAgDtm; /*방문동의일시*/
    private String alncCnslCponId; /*제휴상담쿠폰ID*/
    private String cnslCponUseDtm; /*담당배정완료일시*/
    private String ichrAsnFshDtm; /*상담쿠폰사용일시*/
    private String ichrAsnnrNo; /*담당파트너번호*/
    private String cnslMoCn; /*상담메모내용*/
    private String cnslEvlDtm; /*상담평가일시*/
    private String cnslEvlPc; /*상담평가점수*/
    private String cnslEvlRlyCn; /*상담평가답변내용*/
    private String pspcCstCnslRsCd; /*가망고객상담결과코드*/
    private String ichrOgTpCd; /*담당조직유형코드*/
    private String cstNo; /*고객번호*/
    private String cnslDtm; /*상담일시*/
    private String sellInflwChnlDvCd; /*판매유입채널구분코드*/
    private String inrtPdDvCd; /*관심상품구분코드*/
    private String dtaDlYn; /*데이터삭제여부*/
}
