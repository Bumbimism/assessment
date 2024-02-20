package com.assessment.demo.service.impl;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.service.LotteryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryServiceImpl implements LotteryApiService {
    @Autowired
    private LotteryRepository lotteryRepository;

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
}
