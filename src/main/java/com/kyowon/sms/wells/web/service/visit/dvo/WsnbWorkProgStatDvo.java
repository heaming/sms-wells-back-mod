package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0001 타시스템용(Wells) 설치 오더 생성 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.16
 * @see [AS-IS] LC_ASREGN_API_000
 */
@Setter
@Getter
public class WsnbWorkProgStatDvo {

    int acpteCt; // 수락된 오더 건수
    int vstCnfmCt; // 당일방문예정 오더 건수
    int fshCt; // 완료 건수

}
