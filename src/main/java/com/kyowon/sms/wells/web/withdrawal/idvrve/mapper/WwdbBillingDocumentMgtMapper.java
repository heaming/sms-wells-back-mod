package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchDtlsReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchDtlsRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchFwRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillingDocumentDetailDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillingDocumentDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillingDocumentForwardingDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillingDocumentMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbBillingDocumentMgtMapper {

    /* 청구서 관리 목록 조회 */
    PagingResult<SearchRes> selectBillingDocuments(SearchReq dto, PageInfo pageInfo);

    /* 청구서 관리 엑셀 다운로드 */
    List<SearchRes> selectBillingDocuments(SearchReq dto);

    /* 청구서 관리 목록 삭제 */
    int deleteBillingDocuments(WwdbBillingDocumentMgtDvo dto) throws Exception;

    /* 청구서 관리 상세 목록 삭제 */
    int deleteBillingDocumentDtails(WwdbBillingDocumentMgtDvo dto) throws Exception;

    /* 청구서 관리 저장 */
    int insertBillingDocument(WwdbBillingDocumentDvo dvo) throws Exception;

    /* 청구서 관리 저장 */
    int updateBillingDocument(WwdbBillingDocumentDvo dvo) throws Exception;

    /* 청구서 관리 이력 저장 */
    int insertBillingDocumentHistory(WwdbBillingDocumentDvo dvo) throws Exception;

    /* 청구서 관리 상세 등록 */
    int insertBillingDocumentDtails(WwdbBillingDocumentDetailDvo dvo) throws Exception;

    /* 청구서 관리 상세 이력 등록 */
    int insertBillingDocumentDtailsHistory(WwdbBillingDocumentDetailDvo dvo) throws Exception;

    /* 청구서 관리 상세 수정 */
    int updateBillingDocumentDtails(WwdbBillingDocumentDetailDvo dvo) throws Exception;

    /* 청구서 관리 상세 삭제 */
    int deleteBillingDocumentDtails(WwdbBillingDocumentDetailDvo dvo) throws Exception;

    /* 청구서 관리 채번 */
    String selectBillingDocumentPk();

    /* 청구서 관리 상세 조회 */
    PagingResult<SearchDtlsRes> selectBillingDocumentDetails(SearchDtlsReq dto, PageInfo pageInfo);

    //    /* 청구서 발송 목록 조회 */
    //    public List<WwwdbBillingDocumentForwardingDvo> selectBillingDocuments(SaveFwReq dto);

    /* 청구서 발송 내력 조회 */
    List<SearchFwRes> selectBillingDocumentForwardings(SearchFwReq dto);

    int insertBillingDocumentForwarding(WwdbBillingDocumentForwardingDvo dvo);

    /* 청구서 발송 카톡 채번 - 이건 나중에 수정해야함 */
    String selectMmtSeq();

}
