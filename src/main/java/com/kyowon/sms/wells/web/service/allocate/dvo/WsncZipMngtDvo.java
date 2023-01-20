package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsncZipMngtDvo {
    String newAdrZip; /* 신주소우편번호 */
    String ctpvNm; /* 시도명 */
    String ctctyNm; /* 시군구명 */
    String emdNm; /* 읍면동명 */
    String ltnAdr; /* 지번주소 */
    String lgldCd; /* 법정동코드 */
    String lgldNm; /* 법정동명 */
    String locaraTno; /* 지역전화번호 */
    String islndLocaraYn; /* 도서지역여부 */
    String oldAdrZip; /* 구주소우편번호 */
    String dtaDlYn; /* 데이터삭제여부 */
    String chSn; /* 변경일련번호 */
    String mngerRglvlDvCd; /* 매니저급지구분코드 */
    String mngrDvCd; /* 관리자구분코드 */
    String brchOgId; /* 지점조직ID */
    String useYn; /* 사용여부 */
    String fstRgstDtm; /* 최초등록일시 */
    String fstRgstUsrId; /* 최초등록사용자ID */
    String fstRgstPrgId; /* 최초등록프로그램ID */
    String fstRgstDeptId; /* 최초등록부서ID */
    String fnlMdfcDtm; /* 최종수정일시 */
    String fnlMdfcUsrId; /* 최종수정사용자ID */
    String fnlMdfcPrgId; /* 최종수정프로그램ID */
    String fnlMdfcDeptId; /* 최종수정부서ID */
}
