package com.assessment.demo.controller;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.response.ResponseHandler;
import com.assessment.demo.service.LotteryApiService;
import com.assessment.demo.service.LotteryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping()
public class LotteryController {

    @Autowired
    private LotteryApiService lotteryApiService;

    @GetMapping("/lotteries")
    public List<LotteryResponse> getAllLotteries() {
        return lotteryApiService.getAllLotteries();
    }

    @PostMapping("/admin/lotteries")
    public LotteryResponse createLottery(@RequestBody Lottery lottery) {
        return lotteryApiService.createLottery(lottery);
    }

    @GetMapping("/users/{userId}/lotteries")
    public ResponseEntity<Object> getLotteries(@PathVariable("userId") String userId) {
        UserTicket objectToReturn = lotteryApiService.getLotteries(userId);

        return ResponseHandler.responseBuilder("your ticket",
                HttpStatus.OK,
                objectToReturn);

    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public UserTicket buyLotteries(@PathVariable("userId") String userid,@PathVariable("ticketId") String ticketid) {
        return lotteryApiService.buyLotteries(userid,ticketid);
    }





}
