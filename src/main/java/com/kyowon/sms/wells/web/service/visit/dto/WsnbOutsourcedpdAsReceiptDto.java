package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbOutsourcedpdAsReceiptDto {

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SearchReq")
    public record SearchReq(
        String cnrNm, /* as센터명 */
        String pdNm /* 제품명 */
    ) {}

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SearchRes")
    public record SearchRes(
        String cnrNm, /* as센터명 */
        String pdNm, /* 제품명 */
        String cnrTno, /* as센터연락처 */
        String cnrAddr, /* as센터주소 */
        String psicNm, /* as센터담당자 */
        String cstKnm /* 고객명 */
    ) {}

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-BiztalkReq")
    public record BiztalkReq(
        String cnrNm, /* as센터명 */
        String cnrTno, /* as센터연락처 */
        String cstKnm, /* 고객명 */
        String cstTno /* 고객전화번호 */
    ) {}
}
