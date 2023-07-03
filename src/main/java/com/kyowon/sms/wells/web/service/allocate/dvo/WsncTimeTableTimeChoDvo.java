package com.kyowon.sms.wells.web.service.allocate.dvo;

import java.util.ArrayList;
import java.util.List;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableTimeChoDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableTimeChoDvo {

    WsncTimeTableTimeChoDto.Psics psics;
    List<WsncTimeTableTimeChoDto.AssignTime> assignTimes;

    List<WsncTimeTableTimeChoDto.SmPmNt> smTimes = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
    List<WsncTimeTableTimeChoDto.SmPmNt> amTimes = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
    List<WsncTimeTableTimeChoDto.SmPmNt> pmTimes1 = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
    List<WsncTimeTableTimeChoDto.SmPmNt> pmTimes2 = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
    List<WsncTimeTableTimeChoDto.SmPmNt> ntTimes = new ArrayList<WsncTimeTableTimeChoDto.SmPmNt>();
}
