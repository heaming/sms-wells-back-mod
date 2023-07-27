package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractStatusDto.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sflex.common.message.dvo.KworkSendReqDvo;
import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.KworkMessageService;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sflex.common.system.service.QueryStringEncService;
import com.kyowon.sflex.common.system.service.UrlShortenerService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractStatusDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractStatusService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Api(tags = "[WCTA] 계약현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1+"/contracts/contract-lists")
public class WctaContractStatusController {

    private final WctaContractStatusService service;
    private final QueryStringEncService encService;
    private final UrlShortenerService urlService;
    private final SmsMessageService smsMessageService;
    private final KworkMessageService kWorkService;


    @ApiOperation(value = "계약현황 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public PagingResult<SearchRes> getContractStatusPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getContractStatusPages(dto, pageInfo);
    }

    @ApiOperation(value = "계약현황 간략 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/summary")
    public WctaContractStatusDto.FindSummaryRes getContractStatusSummary(
        @Valid
        SearchReq dto
    ) {
        return service.getContractStatusSummary(dto);
    }

    @ApiOperation(value = "계약현황 - 설치오더 대상 계약 일련번호 목록 조회", notes = "계약번호로 현재 계약진행상태코드를 조회한다.")
    @GetMapping("/{cntrNo}/installation-order-targets")
    public List<Integer> getInstallationOrderTargets(
        @NotEmpty
        @PathVariable("cntrNo")
        String cntrNo
    ) {
        return service.getInstallationOrderTargets(cntrNo);
    }

    @ApiOperation(value = "계약현황 - 계약진행상태코드", notes = "계약번호로 현재 계약진행상태코드를 조회한다.")
    @GetMapping("/{cntrNo}")
    public String getContractStatusPages(
        @NotEmpty
        @PathVariable("cntrNo")
        String cntrNo
    ) {
        return service.getContractPrgsStatCd(cntrNo);
    }

    @ApiOperation(value = "계약현황 - 삭제", notes = "")
    @DeleteMapping
    public SaveResponse removeContract(
        @NotEmpty
        @RequestParam
        String cntrNo
    ) {
        return SaveResponse.builder().processCount(service.removeContract(cntrNo)).build();
    }

    @ApiOperation(value = "url 암호화 및 단축화")
    @PostMapping("/make-url")
    public String sendMessage(
        @RequestBody
        String url
    ) throws Exception {

        return urlService.getShortedUrl(encService.getEncParamUrl(url));
    }

    @ApiOperation(value = "비대면결제 URL 전송")
    @PostMapping("/send-messages")
    public String sendMessage(
        @RequestBody
        MessageReq req
    ) throws Exception {

        if(StringUtils.isNotEmpty(req.url())){
            String shortedUrl = urlService.getShortedUrl(encService.getEncParamUrl(req.url()));
            System.out.println(shortedUrl);
        }

        if("SMS".equals(req.type())){
            // receiver : cstNm+"^"+ StringUtils.replace(telNo, "-", "")

            SmsSendReqDvo dvo = SmsSendReqDvo.withContents()
                .subject("SUBJECT")  // TODO : MESSAGE TEMPLATE 미정
                .msgContent("content")
                .destInfo(req.receiver())
                .build();

            smsMessageService.sendMessage(dvo);

                System.out.println(dvo.toString());
        }else if("KWork".equals(req.type())){

            KworkSendReqDvo dvo = KworkSendReqDvo.withContents()
                .title("SUBJECT")   // TODO : MESSAGE TEMPLATE 미정
                .content("content")
                .receiverId(req.receiver())
                .build();

            kWorkService.sendMessage(dvo);
        }

        return "";
    }
}
