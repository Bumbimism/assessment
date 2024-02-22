package com.assessment.demo.controller;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.TransactionIdResponse;
import com.assessment.demo.service.LotteryApiService;
import com.assessment.demo.response.LotteryResponse;
import jakarta.validation.Valid;
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
    public LotteryResponse createLottery(@Validated @RequestBody LotteryRequest lotteryRequest) {
        return lotteryApiService.createLottery(lotteryRequest);
    }

    @GetMapping("/users/{userId}/lotteries")
    public Object showUserLotteries(@PathVariable("userId") String userId) {
        return lotteryApiService.showUserLotteries(userId);
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public TransactionIdResponse purchaseLottery(@PathVariable("userId") String userid, @PathVariable("ticketId") String ticketid) {
        return lotteryApiService.purchaseLottery(userid, ticketid);
    }

    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public Object refundLottery(@PathVariable("userId")String userId,@PathVariable("ticketId")String ticketId) {
        return lotteryApiService.refundLottery(userId, ticketId);
    }
}
