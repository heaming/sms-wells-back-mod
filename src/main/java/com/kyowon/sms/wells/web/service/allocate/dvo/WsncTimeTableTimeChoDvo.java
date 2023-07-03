package com.kyowon.sms.wells.web.service.allocate.dvo;

import java.util.ArrayList;
import java.util.List;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableTimeChoDvo {

    WsncTimeTableDto.Psics psics;
    List<WsncTimeTableDto.AssignTime> assignTimes;

    List<WsncTimeTableDto.SmPmNt> smTimes = new ArrayList<WsncTimeTableDto.SmPmNt>();
    List<WsncTimeTableDto.SmPmNt> amTimes = new ArrayList<WsncTimeTableDto.SmPmNt>();
    List<WsncTimeTableDto.SmPmNt> pmTimes1 = new ArrayList<WsncTimeTableDto.SmPmNt>();
    List<WsncTimeTableDto.SmPmNt> pmTimes2 = new ArrayList<WsncTimeTableDto.SmPmNt>();
    List<WsncTimeTableDto.SmPmNt> ntTimes = new ArrayList<WsncTimeTableDto.SmPmNt>();
}
