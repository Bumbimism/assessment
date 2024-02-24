package com.assessment.demo.service;


import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.TransactionIdResponse;
import com.assessment.demo.response.LotteryResponse;

public interface LotteryApiService {
    public Object showAllLotteries();
    public LotteryResponse createLottery(LotteryRequest lotteryRequest);
    public Object showUserLotteries(String userid);
    public TransactionIdResponse purchaseLottery(String userid, String ticketId);
    public LotteryResponse refundLottery(String userid, String ticketId);

}
