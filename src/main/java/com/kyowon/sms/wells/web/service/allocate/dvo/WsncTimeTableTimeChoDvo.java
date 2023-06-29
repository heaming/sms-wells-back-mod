package com.kyowon.sms.wells.web.service.allocate.dvo;

import java.util.ArrayList;
import java.util.List;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableTimeChoDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableTimeChoDvo {

    WsncTimeTablePsicDataDvo psicDatas; // left_info
    List<WsncTimeTableTimeChoDto.AssignTime> assignTimes; // list1

    List<WsncTimeTableTimeChoDto.SmPmNt> arrSm = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
    List<WsncTimeTableTimeChoDto.SmPmNt> arrAm = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
    List<WsncTimeTableTimeChoDto.SmPmNt> arrPm1 = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
    List<WsncTimeTableTimeChoDto.SmPmNt> arrPm2 = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
    List<WsncTimeTableTimeChoDto.SmPmNt> arrNt = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
}
