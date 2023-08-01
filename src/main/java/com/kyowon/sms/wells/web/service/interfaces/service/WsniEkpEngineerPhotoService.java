package com.kyowon.sms.wells.web.service.interfaces.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniEkpEngineerPhotoDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniEkpEngineerPhotoDto.SearchRes;
import com.sds.sflex.common.docs.dvo.AttachFileDvo;
import com.sds.sflex.common.docs.service.AttachFileService;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-I-0015 EKP 엔지니어 사진 파일 다운로드
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.05.03
 */
@Service
@RequiredArgsConstructor
public class WsniEkpEngineerPhotoService {

    private final AttachFileService attachFileService;

    public SearchRes getEkpEngineerPhoto(SearchReq req) {
        AttachFileDvo attachFileDvo = attachFileService.getAttachFiles(req.atthDocId()).get(0);
        String imgBase64 = Base64.encodeBase64String(attachFileDvo.getRealityFilePath().getBytes());
        SearchRes res = new SearchRes(imgBase64);

        return res;
    }
}
