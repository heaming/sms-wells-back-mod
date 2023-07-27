package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctzPdBasDvo {
    private String pdCd; /*상품코드*/
    private String pdTpCd; /*상품유형코드*/
    private String pdTpDtlCd; /*상품유형상세코드*/
    private String bzHdqDvCd; /*사업본부구분코드*/
    private String pdClsfId; /*상품분류ID*/
    private String pdHclsfId; /*상품대분류ID*/
    private String pdMclsfId; /*상품중분류ID*/
    private String pdLclsfId; /*상품소분류ID*/
    private String pdDclsfId; /*상품세분류ID*/
    private String pdNm; /*상품명*/
    private String pdAbbrNm; /*상품약어명*/
    private String cstBasePdAbbrNm; /*고객기준상품약어명*/
    private String pdMnulId; /*상품설명서ID*/
    private String pdEplCn; /*상품설명내용*/
    private String pdPrvId; /*상품약관ID*/
    private String pmotPdYn; /*프로모션상품여부*/
    private String alncPdYn; /*제휴상품여부*/
    private String sellYn; /*판매여부*/
    private String sellStrtdt; /*판매시작일자*/
    private String sellEnddt; /*판매종료일자*/
    private String svStrtdt; /*서비스시작일자*/
    private String svEnddt; /*서비스종료일자*/
    private String ostrCnrCd; /*출고센터코드*/
    private String rvpyStrtdt; /*수불시작일자*/
    private String rvpyEnddt; /*수불종료일자*/
    private String mndtSvYn; /*필수서비스여부*/
    private String sapMatGrpVal; /*SAP자재그룹값*/
    private String sapPdctSclsrtStrcVal; /*SAP제품계층구조값*/
    private String sapMatEvlClssVal; /*SAP자재평가클래스값*/
    private String sapMatCd; /*SAP자재코드*/
    private String modelNo; /*모델번호*/
    private String imgApnFileId; /*이미지첨부파일ID*/
    private String mpacYn; /*합포장여부*/
    private String stocMngtOjYn; /*재고관리대상여부*/
    private String basePdChoYn; /*기준상품선택여부*/
    private String spaySellPsbYn; /*일시불판매가능여부*/
    private String stepMngtYn; /*스텝관리여부*/
    private String vatTpCd; /*부가가치세유형코드*/
    private String pdctUprcUseYn; /*제품단가사용여부*/
    private String rdsYn; /*RDS여부*/
    private String redfYn; /*되물림여부*/
    private String pvdaYn; /*현재가치할인차금여부*/
    private String hdCndtCd; /*보류조건코드*/
    private String prcTpCd; /*가격유형코드*/
    private String crncyDvCd; /*통화구분코드*/
    private String pymntTpCd; /*지불유형코드*/
    private String cntrClsfCd; /*계약분류코드*/
    private String dscTpCd; /*할인유형코드*/
    private Long feeAmt; /*수수료금액*/
    private Integer ackmtCt; /*인정건수*/
    private Float ackmtPerfRt; /*인정실적율 천퍼센트가 넘을리가..*/
    private String tempSaveYn; /*임시저장여부*/
    private String dtaDlYn; /*데이터삭제여부*/
    private String fstRgstDtm; /*최초등록일시*/
    private String fstRgstUsrId; /*최초등록사용자ID*/
    private String fstRgstPrgId; /*최초등록프로그램ID*/
    private String fstRgstDeptId; /*최초등록부서ID*/
    private String fnlMdfcDtm; /*최종수정일시*/
    private String fnlMdfcUsrId; /*최종수정사용자ID*/
    private String fnlMdfcPrgId; /*최종수정프로그램ID*/
    private String fnlMdfcDeptId; /*최종수정부서ID*/
    private String sellPdDvCd; /*판매상품구분코드*/
    private String hgrPdCd; /*상위상품코드*/
    private String sellTpDtlCd; /*판매유형상세코드*/
    private Long pdctUprc; /*제품단가*/
    private String slRcogClsfCd; /*매출인식6분류코드*/
    private String sapPlntCd; /*SAP플랜트코드*/
    private String fgptYn; /*사은품여부*/
    private String svPdTpCd; /*서비스상품유형코드*/
    private String sellTpCd; /*판매유형코드*/
    private String pdEplUrlAdr; /*상품설명URL주소*/
    private String sapMatTpVal; /*SAP자재유형값*/
}
