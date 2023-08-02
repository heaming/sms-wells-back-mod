package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-I-0009 타시스템(교원웰스, 고객센터, KMEMBERS)에서 A/S, 분리, 재설치 및 설치정보 변경 등록 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.26
 */
@Setter
@Getter
public class WsnbWorkOrderInterfaceDvo {

    String inChnlDvCd; // 입력채널구분코드
    String svBizHclsfCd; // 서비스업무대분류코드
    String rcpdt; // 접수일자
    String asIstOjNo; // AS설치대상번호
    String mtrStatCd; // 자료상태코드
    String svBizDclsfCd; // 서비스업무세분류코드
    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String vstRqdt; // 방문요청일자
    String vstAkHh; // 방문요청시간
    String apyDtm; // 적용일시
    String urgtDvCd; // 긴급구분코드
    String smsFwYn; // SMS발송여부
    int svEtAmt; // 서비스예상금액
    String dpDvCd; // 입금구분코드
    String cnslTpHclsfCd; // 상담유형대분류코드
    String cnslTpMclsfCd; // 상담유형중분류코드
    String cnslTpLclsfCd; // 상담유형소분류코드
    String cnslDtlpTpCd; // 상담세부유형코드
    String cnslMoCn; // 상담메모내용
    String asRefriDvCd; // AS유무상구분코드
    String cralLocaraTno; // 휴대지역전화번호
    String cphonIdvTno1; // 휴대폰개별전화번호1
    String cphonIdvTno2; // 휴대폰개별전화번호2
    String locaraTno; // 지역전화번호
    String idvTno1; // 개별전화번호1
    String idvTno2; // 개별전화번호2
    String istZip1; // 이전주소 우편번호1
    String istZip2; // 이전주소 우편번호2
    String istAdr; // 이전주소
    String istDtlAdr; // 이전주소상세
    String refAdr; // 참조주소
    String prchsMatIz; // 구매자재내역
    String etcCn1; // 기타내용1 (Y: 분리+재설치 시 이전 주소에서 서비스 받음)
    String ogTpCd; // 조직유형코드
    String rgstUsrId; // 등록사용자ID

}
