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
        return lotteryApiService.getAllLotteries();
    }

    @PostMapping("/admin/lotteries")
    public LotteryResponse createLottery(@RequestBody Lottery lottery) {
        return lotteryApiService.createLottery(lottery);
    }

    @GetMapping("/users/{userId}/lotteries")
    public Object getLotteries(@PathVariable("userId") String userId) {
        List<String> myLotteries = lotteryApiService.getLotteries(userId);

        return myLotteries;


    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public IdResponse buyLotteries(@PathVariable("userId") String userid, @PathVariable("ticketId") String ticketid) {
        return lotteryApiService.buyLotteries(userid, ticketid);
    }


}
