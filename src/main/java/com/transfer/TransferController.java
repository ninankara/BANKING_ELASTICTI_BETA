package com.transfer;

import com.transfer.model.BalCheckInput;
import com.transfer.model.CheckHisInput;
import com.transfer.model.TrscConfirmInput;
import com.transfer.model.TrscReqInput;
import com.transfer.module.CommonResponse;
import com.transfer.service.DepositService;

import com.transfer.service.SchedulerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trnf")
public class TransferController {
    private final DepositService depositService;
    private final SchedulerService schedulerService;

    @PostMapping(value="/BalCheck")
    @ApiOperation(value="Check Balance", notes="Check Balance")
    public ResponseEntity<?> balCheck(@RequestBody @Valid BalCheckInput balCheckInput) throws URISyntaxException {

        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(CommonResponse.ResponseStatus.OK)
                .output(depositService.balCheck(balCheckInput)).build();
        return ResponseEntity.ok(commonResponseDto);
    }

    @PostMapping(value="/TrscReq")
    @ApiOperation(value="Transaction Request", notes="Transaction Request")
    public ResponseEntity<?> trscReq(@RequestBody @Valid TrscReqInput trscReqInput) throws URISyntaxException {

        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(CommonResponse.ResponseStatus.OK)
                .output(depositService.trscReq(trscReqInput)).build();
        return ResponseEntity.ok(commonResponseDto);
    }

    @PostMapping(value="/TrscConfirm")
    @ApiOperation(value="Transaction Confirm", notes="Transaction Confirm")
    public ResponseEntity<?> trscConfirm(@RequestBody @Valid TrscConfirmInput trscConfirmInput) throws URISyntaxException {

        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(CommonResponse.ResponseStatus.OK)
                .output(depositService.trscConfirm(trscConfirmInput)).build();
        return ResponseEntity.ok(commonResponseDto);
    }

    @PostMapping(value="/CheckHis")
    @ApiOperation(value="Check History", notes="Check History")
    public ResponseEntity<?> checkHis(@RequestBody @Valid CheckHisInput checkHisInput) throws URISyntaxException {

        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(CommonResponse.ResponseStatus.OK)
                .output(depositService.checkHis(checkHisInput)).build();
        return ResponseEntity.ok(commonResponseDto);
    }

    @PostMapping(value="/FindUnfinishedTrsc")
    @ApiOperation(value="Find Unfinished Transaction", notes="Find Unfinished Transaction")
    public ResponseEntity<?> findUnfinishedTrsc() throws URISyntaxException {

        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(CommonResponse.ResponseStatus.OK)
                .output(schedulerService.findUnfinishedTrsc()).build();
        return ResponseEntity.ok(commonResponseDto);
    }
}
