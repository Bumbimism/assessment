package com.assessment.demo.service;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;

import java.util.List;
import java.util.Map;

public interface LotteryApiService {
    public LotteryResponse createLottery(Lottery lottery);

    public String updateLottery(Lottery lottery);

    public List<LotteryResponse> getAllLotteries();

    public UserTicket getLotteries(String userid);
    public UserTicket buyLotteries(String userid, String ticketid);

}
