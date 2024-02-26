package com.assessment.demo.controller;

import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.service.LotteryApiService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final LotteryApiService lotteryApiService;

    public AdminController(LotteryApiService lotteryApiService) {
        this.lotteryApiService = lotteryApiService;
    }

    @PostMapping("/lotteries")
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryResponse createLottery(@Validated @RequestBody LotteryRequest lotteryRequest) {

        return lotteryApiService.createLottery(lotteryRequest);

    }
}
