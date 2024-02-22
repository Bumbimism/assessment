package com.assessment.demo.controller;

import com.assessment.demo.exception.BaseException;
import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.response.TransactionIdResponse;
import com.assessment.demo.service.LotteryApiService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class LotteryController {
    private final LotteryApiService lotteryApiService;

    public LotteryController(LotteryApiService lotteryApiService) {
        this.lotteryApiService = lotteryApiService;
    }

    @GetMapping("/lotteries")
    public Object getAllLotteries() {
        return lotteryApiService.showAllLotteries();
    }

    @PostMapping("/admin/lotteries")
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryResponse createLottery(@Validated @RequestBody LotteryRequest lotteryRequest) {
        return lotteryApiService.createLottery(lotteryRequest);
    }

    @GetMapping("/users/{userId}/lotteries")
    public Object showUserLotteries(@PathVariable("userId") String userId) {
        return lotteryApiService.showUserLotteries(userId);
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionIdResponse purchaseLottery(@PathVariable("userId") String userId,
                                                 @PathVariable("ticketId") String ticketId) throws BaseException {
        return lotteryApiService.purchaseLottery(userId, ticketId);
    }


    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public Object refundLottery(@PathVariable("userId") String userId,
                                @PathVariable("ticketId") String ticketId) {
        return lotteryApiService.refundLottery(userId, ticketId);
    }
}
