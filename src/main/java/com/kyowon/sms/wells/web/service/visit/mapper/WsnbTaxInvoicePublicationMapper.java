package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTaxInvoicePublicationDvo;

/**
 * <pre>
 * W-SV-U-0299PM01 (법인계약공통) 세금계산서발행요청
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.05.30
 */
@Mapper
public interface WsnbTaxInvoicePublicationMapper {

    List<WsnbTaxInvoicePublicationDvo> selectTaxInvoices(SearchReq dto);

    int selectBusinessNumberCount(String bzrno);

    int updateTaxInvoice(WsnbTaxInvoicePublicationDvo dvo);

}
