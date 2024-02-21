package com.assessment.demo.service;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.response.TransactionIdResponse;
import com.assessment.demo.response.LotteryResponse;

public interface LotteryApiService {
    public Object showAllLotteries();
    public LotteryResponse createLottery(Lottery lottery);
    public Object showUserLotteries(String userid);
    public TransactionIdResponse purchaseLottery(String userid, String ticketid);
    public Object refundLottery(String userid, String ticketid);

}
