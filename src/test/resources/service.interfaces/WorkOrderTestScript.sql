

-- 일반 설치 테스트
    SELECT C5.OJ_DTL_CNTR_NO
         , C5.OJ_DTL_CNTR_SN
         , S1.*
         , S2.*
      FROM TB_SVPD_CST_SVAS_IST_ASN_IZ S1
INNER JOIN TB_SVPD_CST_SVAS_IST_OJ_IZ S2
        ON S1.CST_SV_ASN_NO = S2.CST_SV_ASN_NO
INNER JOIN TB_SSCT_CNTR_DTL C1
        ON S2.CNTR_NO = C1.CNTR_NO
       AND S2.CNTR_SN = C1.CNTR_SN
 INNER JOIN TB_SSCT_CNTR_ADR_REL C2
        ON C1.CNTR_NO = C2.DTL_CNTR_NO
       AND C1.CNTR_SN = C2.DTL_CNTR_SN
       AND C2.ADRPC_TP_CD IN ('2','3') /* TODO: 확인 필요 */
       AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN C2.VL_STRT_DTM AND C2.VL_END_DTM
 INNER JOIN TB_SSCT_CNTR_ADRPC_BAS C3
        ON C2.CNTR_ADRPC_ID = C3.CNTR_ADRPC_ID
 INNER JOIN TB_GBCO_ADR_BAS A1 /* 주소기본 */
        ON A1.ADR_ID = C3.ADR_ID
 INNER JOIN TB_SSCT_CNTR_WELLS_DTL C4
        ON C1.CNTR_NO = C4.CNTR_NO
       AND C1.CNTR_SN = C4.CNTR_SN
 INNER JOIN TB_SVPD_CST_SV_EXCN_IZ S3
        ON S2.CNTR_NO = S3.CNTR_NO
       AND S2.CNTR_SN = S3.CNTR_SN
 LEFT JOIN TB_SSCT_CNTR_REL C5
        ON S1.CNTR_NO = C5.OJ_DTL_CNTR_NO
       AND S1.CNTR_SN = C5.OJ_DTL_CNTR_SN
       AND C5.DTA_DL_YN = 'N'
       AND C5.CNTR_REL_DTL_CD = '215'
       AND TO_CHAR(SYSDATE, 'YYYYMMDD') BETWEEN C5.VL_STRT_DTM AND C5.VL_END_DTM
     WHERE 1 = 1
       AND S1.SV_BIZ_DCLSF_CD = '1121'
--       AND S1.WK_PRGS_STAT_CD = '00'
       AND S1.VST_CNFMDT LIKE '2023%'
--       AND S2.IN_CHNL_DV_CD = '4'
--       AND S2.MTR_STAT_CD = '1'
        ;



-- 웰스팜설치 테스트
    SELECT C5.OJ_DTL_CNTR_NO
         , C5.OJ_DTL_CNTR_SN
         , S1.*
         , S2.*
      FROM TB_SVPD_CST_SVAS_IST_ASN_IZ S1
INNER JOIN TB_SVPD_CST_SVAS_IST_OJ_IZ S2
        ON S1.CST_SV_ASN_NO = S2.CST_SV_ASN_NO
INNER JOIN TB_SSCT_CNTR_DTL C1
        ON S2.CNTR_NO = C1.CNTR_NO
       AND S2.CNTR_SN = C1.CNTR_SN
 INNER JOIN TB_SSCT_CNTR_ADR_REL C2
        ON C1.CNTR_NO = C2.DTL_CNTR_NO
       AND C1.CNTR_SN = C2.DTL_CNTR_SN
       AND C2.ADRPC_TP_CD IN ('2','3') /* TODO: 확인 필요 */
       AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN C2.VL_STRT_DTM AND C2.VL_END_DTM
 INNER JOIN TB_SSCT_CNTR_ADRPC_BAS C3
        ON C2.CNTR_ADRPC_ID = C3.CNTR_ADRPC_ID
 INNER JOIN TB_GBCO_ADR_BAS A1 /* 주소기본 */
        ON A1.ADR_ID = C3.ADR_ID
 INNER JOIN TB_SSCT_CNTR_WELLS_DTL C4
        ON C1.CNTR_NO = C4.CNTR_NO
       AND C1.CNTR_SN = C4.CNTR_SN
 INNER JOIN TB_SVPD_CST_SV_EXCN_IZ S3
        ON S2.CNTR_NO = S3.CNTR_NO
       AND S2.CNTR_SN = S3.CNTR_SN
 INNER JOIN TB_PDBS_PD_ECOM_PRP_DTL P1
        ON S3.PDCT_PD_CD = P1.PD_CD
       AND P1.PD_EXTS_PRP_GRP_CD = 'PART'
INNER JOIN TB_SSCT_CNTR_REL C5
        ON S1.CNTR_NO = C5.BASE_DTL_CNTR_NO
       AND S1.CNTR_SN = C5.BASE_DTL_CNTR_SN
       AND C5.CNTR_REL_DTL_CD = '216' -- 216 모종결합
     WHERE 1 = 1
       AND S1.SV_BIZ_DCLSF_CD = '1110'
--       AND S1.WK_PRGS_STAT_CD = '00'
       AND S1.VST_CNFMDT LIKE '2023%'
--       AND S2.IN_CHNL_DV_CD = '4'
--       AND S2.MTR_STAT_CD = '1'
       AND P1.PD_PRP_VAL20 = '92'
       AND S2.CNTR_NO IN ('W20230536953');




DECLARE
    vCntrNo VARCHAR2(200);
    vCntrSn VARCHAR2(200);
BEGIN
    vCntrNo := 'W20230536953';
    vCntrSn := '1';

    DELETE
      FROM TB_SVPD_CST_SVAS_IST_OJ_IZ T1
     WHERE T1.CNTR_NO = vCntrNo
       AND T1.CNTR_SN = vCntrSn;

    DELETE
      FROM TB_SVPD_CST_SVAS_IST_ASN_IZ T1
     WHERE T1.CNTR_NO = vCntrNo
       AND T1.CNTR_SN = vCntrSn;
END;
