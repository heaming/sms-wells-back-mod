package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbInstallationConfirmationConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationConfirmationDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationConfirmationDto.FindRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallationConfirmationDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallationConfirmationMapper;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-MP-U-0048P01 전자설치확인서
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.07.07
 */
@Service
@RequiredArgsConstructor
public class WsnbInstallationConfirmationService {

    private final WsnbInstallationConfirmationMapper mapper;
    private final WsnbInstallationConfirmationConverter converter;

    /**
      * 전자설치확인서 조회
      *
      * @param {String wprs, String cstSvAsnNo}
      */
    public Map<String, Object> getInstallationConfirmation(String cstSvAsnNo, String wprs) {

        WsnbInstallationConfirmationDvo dvo = mapper.selectInstallationConfirmation(cstSvAsnNo, wprs);

        String cstSignCn = "";
        String tno = "";
        String mpno = "";
        String tnoIst = "";
        String mpnoIst = "";

        // TB_SVPD_ELC_IST_CONF_IZ 테이블 사인값 없으면
        if (dvo.getCstSignByte().length > 0 && dvo.getCstSignByte() != null && dvo.getCstSignCn() == null) {
            cstSignCn = Base64Utils.encodeToString(dvo.getCstSignByte());
            dvo.setCstSignCn(cstSignCn);
        }
        // 전화번호 세팅
        if (StringUtil.isNotBlank(dvo.getLocaraTno()) && StringUtil.isNotBlank(dvo.getExnoEncr())
            && StringUtil.isNotBlank(dvo.getIdvTno())) {
            tno = dvo.getLocaraTno() + "-" + dvo.getExnoEncr() + "-" + dvo.getIdvTno();
        } else if (StringUtil.isNotBlank(dvo.getLocaraTno()) && StringUtil.isNotBlank(dvo.getIdvTno())) {
            tno = dvo.getLocaraTno() + "-" + dvo.getIdvTno();
        }
        mpno = dvo.getCralLocaraTno() + "-" + dvo.getMexnoEncr() + "-" + dvo.getCralIdvTno();

        if (StringUtil.isNotBlank(dvo.getLocaraTnoIst()) && StringUtil.isNotBlank(dvo.getExnoEncrIst())
            && StringUtil.isNotBlank(dvo.getIdvTnoIst())) {
            tnoIst = dvo.getLocaraTnoIst() + "-" + dvo.getExnoEncrIst() + "-" + dvo.getIdvTnoIst();
        } else if (StringUtil.isNotBlank(dvo.getLocaraTnoIst()) && StringUtil.isNotBlank(dvo.getIdvTnoIst())) {
            tnoIst = dvo.getLocaraTnoIst() + "-" + dvo.getIdvTnoIst();
        }
        mpnoIst = dvo.getCralLocaraTnoIst() + "-" + dvo.getMexnoEncrIst() + "-" + dvo.getCralLocaraTnoIst();
        dvo.setTno(tno);
        dvo.setMpno(mpno);
        dvo.setTnoIst(tnoIst);
        dvo.setMpnoIst(mpnoIst);

        Map<String, Object> map = new HashMap<>();
        FindRes res = converter.mapWsnbInstallationConfirmationDvoToFindRes(dvo);
        map.put("jsondata", res);
        return map;
    }

    /**
      * 전자설치확인서 저장
      *
      * @param dto
      */
    public int createInstallationConfirmation(CreateReq dto) throws Exception {
        int processCount = 0;
        WsnbInstallationConfirmationDvo dvo = converter.mapCreateReqToWsnbSafetyAccidentDvo(dto);
        dvo.setCstSignCn(dvo.getCstSignCn().replaceAll("data:image/png;base64,", ""));
        processCount += mapper.createInstallationConfirmation(dvo);

        return processCount;
    }

}
