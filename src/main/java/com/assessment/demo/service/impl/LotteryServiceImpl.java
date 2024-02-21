package com.assessment.demo.service.impl;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.repository.UserTicketRepository;
import com.assessment.demo.response.IdResponse;
import com.assessment.demo.service.LotteryApiService;
import com.assessment.demo.response.LotteryResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
    @ResponseBody
    public Object getAllLotteries() {
        Map<String, List<String>> object = new HashMap<>();
        object.put("tickets", lotteryRepository.findAllLotteries());
        return object;
    }

    @Override
    public List<String> getLotteries(String userid) {




        return userTicketRepository.findTicketsByUserid(userid);
    }

    @Override
    @Transactional
    public IdResponse buyLotteries(String userid, String ticketid) {
        UserTicket userTicket = new UserTicket();
        userTicket.setUserid(userid);
        userTicket.setTicketid(ticketid);

        userTicketRepository.save(userTicket);
        return new IdResponse(userTicket.getId().toString());


    }

//    public List<LotteryResponse> getListLotteries() {
//        Lotterylottery = lotteryRepository.findAll();
//        return new LotteryResponse(lottery.getTicketid());
//    }
}
