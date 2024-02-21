package com.assessment.demo.service.impl;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.exception.UserTicketException;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.repository.UserTicketRepository;
import com.assessment.demo.service.LotteryApiService;
import com.assessment.demo.service.LotteryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LotteryServiceImpl implements LotteryApiService {
    @Autowired
    private LotteryRepository lotteryRepository;

    @Autowired
    private UserTicketRepository userTicketRepository;

    @Override
    public LotteryResponse createLottery(Lottery lottery) {
        lotteryRepository.save(lottery);
        return new LotteryResponse(lottery.getTicketid());

    }

    @Override
    public String updateLottery(Lottery lottery) {
        lotteryRepository.save(lottery);
        return "successfully UPDATED";
    }

    @Override
    public List<Object> getAllLotteries() {
        return lotteryRepository.findAllLotteries();
    }

    @Override
    public UserTicket getLotteries(String userid) {
        if (userTicketRepository.findByUserid(userid) == null)
            throw new UserTicketException("this UserId is not available");

        return userTicketRepository.findByUserid(userid);
    }

    @Override
    public UserTicket buyLotteries(String userid, String ticketid){
        UserTicket userTicket = new UserTicket();
        userTicket.setUserid(userid);
        userTicket.setTicketid(ticketid);

        userTicketRepository.save(userTicket);
        return userTicketRepository.findByUserid(userid);


    }

//    public List<LotteryResponse> getListLotteries() {
//        Lotterylottery = lotteryRepository.findAll();
//        return new LotteryResponse(lottery.getTicketid());
//    }
}
