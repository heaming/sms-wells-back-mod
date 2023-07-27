package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractPmotIzDvo {
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String histStrtDtm; /* 이력시작일시 */
    private String histEndDtm; /* 이력종료일시 */
    private String lrnnGryCd; /* 학습학년코드 */
    private String lrnnStrtLvCd; /* 학습시작단계코드 */
    private String lrnnStrtRqdt; /* 학습시작요청일자 */
    private String lrnnDowCd; /* 학습요일코드 */
    private String lrnnHm; /* 학습시분 */
    private String lrnnCrseSerisCd; /* 학습과정시리즈코드 */
    private String lrnnTchmtUnitTpCd; /* 학습교재단위유형코드 */
    private Integer totLrnnCirn; /* 총학습부수 */
    private Long rglTchmtStrtIsn; /* 정규교재시작호수 */
    private Long rglTchmtEndIsn; /* 정규교재종료호수 */
    private Long rettTchmtStrtIsn; /* 소급교재시작호수 */
    private Long rettTchmtEndIsn; /* 소급교재종료호수 */
    private String lrnnTchmtBaseYm; /* 학습교재기준년월 */
    private String lrnnStrtdt; /* 학습시작일자 */
    private String lrnnEnddt; /* 학습종료일자 */
    private Long borrLrnnIsn; /* 차주학습호수 */
    private Integer resLrnnCirn; /* 잔여학습부수 */
    private String bilStrtdt; /* 청구시작일자 */
    private String bilEnddt; /* 청구종료일자 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
