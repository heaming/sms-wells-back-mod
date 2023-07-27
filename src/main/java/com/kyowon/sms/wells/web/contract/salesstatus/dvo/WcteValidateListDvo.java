package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * 서버 validation 시 사용 할 DVO
 * </pre>
 *
 * @author 박주형
 * @since 2023-05-23
 */
@Getter
@Setter
@Builder
public class WcteValidateListDvo<T> {
    private boolean valid;
    private List<T> cleansingList;
    private List<ExcelUploadErrorDvo> errList;
}
