package com.assessment.demo.service;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;

import java.util.List;
import java.util.Map;

public interface LotteryApiService {
    public LotteryResponse createLottery(Lottery lottery);

    public String updateLottery(Lottery lottery);

    public Object getAllLotteries();

    public UserTicket getLotteries(String userid);
    public IdResponse buyLotteries(String userid, String ticketid);

}
