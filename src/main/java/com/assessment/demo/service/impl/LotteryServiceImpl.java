package com.assessment.demo.service.impl;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.repository.UserTicketRepository;
import com.assessment.demo.response.IdResponse;
import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.service.LotteryApiService;
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
    @ResponseBody
    public Object showAllLotteries() {
        Map<String, List<String>> object = new HashMap<>();
        object.put("tickets", lotteryRepository.findAllLotteries());
        return object;
    }

    @Override
    public LotteryResponse createLottery(Lottery lottery) {
        lotteryRepository.save(lottery);
        return new LotteryResponse(lottery.getTicketid());
    }

    @Override
    public Object showUserLotteries(String userid) {
        Map<String, Object> object = new HashMap<>();
        List<String> listLotteries = userTicketRepository.findTicketsByUserid(userid);
        int count = listLotteries.size();
        int cost = count * 80;
        object.put("tickets", listLotteries);
        object.put("count", count);
        object.put("cost", cost);
        return object;
    }

    @Override
    @Transactional
    public IdResponse purchaseLottery(String userid, String ticketid) {
        UserTicket userTicket = new UserTicket();
        userTicket.setUserid(userid);
        userTicket.setTicketid(ticketid);
        userTicketRepository.save(userTicket);
        return new IdResponse(userTicket.getId().toString());
    }

    @Override
    @Transactional
    public Object refundLottery(String userid, String ticketid) {
        userTicketRepository.RefundLottery(userid, ticketid);
        Map<String, String> object = new HashMap<>();
        object.put("tickets", ticketid);
        return object;
    }
}
