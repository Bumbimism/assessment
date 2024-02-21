package com.assessment.demo.service.impl;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.exception.UserTicketException;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.repository.UserTicketRepository;
import com.assessment.demo.service.LotteryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryServiceImpl implements LotteryApiService {
    @Autowired
    private LotteryRepository lotteryRepository;

    @Autowired
    private UserTicketRepository userTicketRepository;

    @Override
    public String createLottery(Lottery lottery){
        lotteryRepository.save(lottery);
        return "successfully CREATE";
    }

    @Override
    public String updateLottery(Lottery lottery){
        lotteryRepository.save(lottery);
        return "successfully UPDATED";
    }

    @Override
    public List<Lottery> getAllLotteries(){
        return lotteryRepository.findAll();
    }

    public UserTicket getLotteries(String userid) {
        if(userTicketRepository.findByUserid(userid) == null)
            throw new UserTicketException("this UserId is not available");

        return userTicketRepository.findByUserid(userid);
    }
}
