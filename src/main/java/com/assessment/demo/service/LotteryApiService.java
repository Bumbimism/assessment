package com.assessment.demo.service;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;

import java.util.List;

public interface LotteryApiService {
    public String createLottery(Lottery lottery);

    public String updateLottery(Lottery lottery);

    public List<Lottery> getAllLotteries();

    public UserTicket getLotteries(String userid);

}
