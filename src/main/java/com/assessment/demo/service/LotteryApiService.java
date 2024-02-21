package com.assessment.demo.service;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.response.IdResponse;
import com.assessment.demo.response.LotteryResponse;

import java.util.List;

public interface LotteryApiService {
    public LotteryResponse createLottery(Lottery lottery);

    public String updateLottery(Lottery lottery);

    public Object getAllLotteries();

    public Object getLotteries(String userid);

    public IdResponse buyLotteries(String userid, String ticketid);

    public Object refundLottery(String userid, String ticketid);

}
