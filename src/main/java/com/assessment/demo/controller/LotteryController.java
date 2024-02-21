package com.assessment.demo.controller;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.response.IdResponse;
import com.assessment.demo.service.LotteryApiService;
import com.assessment.demo.response.LotteryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class LotteryController {

    @Autowired
    private LotteryApiService lotteryApiService;

    @GetMapping("/lotteries")
    public Object getAllLotteries() {
        return lotteryApiService.showAllLotteries();
    }

    @PostMapping("/admin/lotteries")
    public LotteryResponse createLottery(@RequestBody Lottery lottery) {
        return lotteryApiService.createLottery(lottery);
    }

    @GetMapping("/users/{userId}/lotteries")
    public Object showUserLotteries(@PathVariable("userId") String userId) {
        return lotteryApiService.showUserLotteries(userId);
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public IdResponse purchaseLottery(@PathVariable("userId") String userid, @PathVariable("ticketId") String ticketid) {
        return lotteryApiService.purchaseLottery(userid, ticketid);
    }

    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public Object refundLottery(@PathVariable("userId")String userId,@PathVariable("ticketId")String ticketId) {
        return lotteryApiService.refundLottery(userId, ticketId);
    }
}
