package com.assessment.demo.service;

import com.assessment.demo.entity.Lottery;

import java.util.List;

public interface LotteryApiService {
    public String createLottery(Lottery lottery);
    public String updateLottery(Lottery lottery);
    public List<Lottery> getAllLotteries();
}
