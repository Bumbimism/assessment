package com.assessment.demo.controller;

import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.response.TransactionIdResponse;
import com.assessment.demo.service.LotteryApiService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/users/{userId}/lotteries")
    public Object showUserLotteries(@PathVariable("userId") String userId) {
        return lotteryApiService.showUserLotteries(userId);
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionIdResponse purchaseLottery(@PathVariable("userId") String userId,
                                                 @PathVariable("ticketId") String ticketId) {
        return lotteryApiService.purchaseLottery(userId, ticketId);
    }


    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public LotteryResponse refundLottery(@PathVariable("userId") String userId,
                                         @PathVariable("ticketId") String ticketId) {
        return lotteryApiService.refundLottery(userId, ticketId);
    }
}
