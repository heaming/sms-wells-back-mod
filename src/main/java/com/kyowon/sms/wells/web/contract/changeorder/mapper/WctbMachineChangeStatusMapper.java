package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMachineChStatBasInfDvo;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMachineChStatInOutDvo;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMembershipWdwalDvo;

@Mapper
public interface WctbMachineChangeStatusMapper {
    WctbMachineChStatBasInfDvo selectContractBaseInfo(WctbMachineChStatInOutDvo dvo);

    String selectDlqExYn(String procsCd, String drmVal);
    int selectMachineChPsbYn(String pdCd);

    Optional<String> selectProductClsf(String pdCd);

    WctbMachineChStatBasInfDvo selectMachineInfo(WctbMachineChStatInOutDvo dvo);

    Optional<WctbMembershipWdwalDvo> selectMembershipWdwal(String cntrNo, String cntrSn);
}
