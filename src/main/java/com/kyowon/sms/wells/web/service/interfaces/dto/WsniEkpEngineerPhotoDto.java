package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-I-0015 EKP 엔지니어 사진 파일 다운로드
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.05.03
 */
public class WsniEkpEngineerPhotoDto {
    @ApiModel(value = "WsniEkpEngineerPhotoDto-SearchReq")
    public record SearchReq(
        @JsonProperty("ATTH_DOC_ID")
        String atthDocId
    ) {}

    @ApiModel(value = "WsniEkpEngineerPhotoDto-SearchRes")
    public record SearchRes(
        @JsonProperty("IMG")
        String img
    ) {}
}
