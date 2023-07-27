package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaCollectingAmountContactDto {
    @Builder
    @ApiModel("WctaCollectingAmountContactDto-SearchRes")
    public record SearchRes(
        String rnum, /* [순번] */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cttDt, /* [컨택일자] */
        String cttTm, /* [컨택시간] 컨택시각/최초등록시간 */
        String expDt, /* [예정일자] */
        String clctamCttCd, /* 집금컨택코드 */
        String clctamCttNm, /* [컨택사항] 집금컨택명 */
        String dlqMcn, /* [차월] 연체개월수 */
        String cttMoCn, /* [컨택메모] 컨택메모내용 */
        String sellOgTpCd, /* 판매조직유형코드 */
        String fstRgstUsrId, /* [등록담당] 최초등록사용자ID */
        String usrNm, /* [등록담당] 최초등록사용자명 */
        String modYn /* 수정가능여부. TOBE에서는 삭제 컬럼이 삭제되어 사용안함. */
    ) {}
}
