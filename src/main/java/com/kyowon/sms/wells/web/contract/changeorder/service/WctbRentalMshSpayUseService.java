package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbRentalMshSpayUseConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalMshSpayUseDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMshSpayUseDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbRentalMshSpayUseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbRentalMshSpayUseService {
    private final WctbRentalMshSpayUseMapper mapper;
    private final WctbRentalMshSpayUseConverter converter;

    public WctbRentalMshSpayUseDto.SearchRes getRentalMshSpayUses(WctbRentalMshSpayUseDto.SearchReq dto) {
        //DTO
        WctbRentalMshSpayUseDvo res = null;
        if (StringUtils.isEmpty(dto.paramInqrDv())) {
            return null;
        } else if (dto.paramInqrDv().equals("01")) { /* ※ 교원키, 고객번호, 등록일자, 등록시간, 시작일자, 종료일자 모두 필수 */
            if (StringUtils.isAnyEmpty(
                dto.paramCstNo(), dto.paramCntrNo(), dto.paramRgstDt(), dto.paramRgstHh(), dto.paramStrtDt(),
                dto.paramEndDt()
            )) {
                return null;
            }
        } else if (StringUtils.equalsAny(dto.paramInqrDv(), "02", "05", "06")) { /* ※ 등록일자, 교원키 필수 */
            if (StringUtils.isAnyEmpty(
                dto.paramCstNo(), dto.paramRgstDt()
            )) {
                return null;
            }
        } else if (StringUtils.equalsAny(dto.paramInqrDv(), "03", "04")) { /* ※ 판매코드, 등록일자 필수 */
            if (StringUtils.isAnyEmpty(
                dto.paramSellerCd(), dto.paramRgstDt()
            )) {
                return null;
            }
        } else {
            return null;
        }
        if (dto.paramInqrDv().equals("01")) { // 공기청정기 할인 프로모션 (당월 렌탈 또는 레인지, 안마 일시불 1건이상 접수고객) 조회
            res = mapper.selectPdMclsfCnt(dto);
        } else if (dto.paramInqrDv().equals("02")) { // VIP여부 점검
            res = mapper.selectCstGdCdCheck(dto);
        } else if (dto.paramInqrDv().equals("03")) { // 직원가 프로모션 대상체크
            res = mapper.selectEmplPmotCheck(dto);
        } else if (dto.paramInqrDv().equals("04")) { //지인추천가 프로모션 대상체크
            res = mapper.selectRcmdPmotCheck(dto);
        } else if (dto.paramInqrDv().equals("05")) { //재유입할인 프로모션 대상체크
            res = mapper.selectReentryPmotCheck(dto);
        } else if (dto.paramInqrDv().equals("06")) { //순신규 프로모션 대상체크
            res = mapper.selectNewPmotCheck(dto);
        }
        if (res == null) {
            res = new WctbRentalMshSpayUseDvo();
            res.setRsCt1("0");
            res.setRsCt2("0");
            res.setRsCt3("0");
            res.setRsCt4("0");
            res.setRsCt5("0");
            res.setRsCt6("0");
            res.setRsCt7("0");
            res.setRsCt8("0");
            res.setRsCt9("0");
            res.setRsCt10("0");
            res.setRsCt11("0");
            res.setRsCt12("0");
            res.setRsCt13("0");
            res.setRsCt14("0");
            res.setRsCt15("0");
            res.setRsCheckYn(" ");
        }
        return converter.mapWctbRentalMshSpayUseDvoToSearchRes(res);
    }
}
