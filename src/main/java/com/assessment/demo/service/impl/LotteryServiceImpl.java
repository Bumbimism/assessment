package com.assessment.demo.service.impl;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.repository.UserTicketRepository;
import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.response.TransactionIdResponse;
import com.assessment.demo.response.UserLotteryResponse;
import com.assessment.demo.service.LotteryApiService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LotteryServiceImpl implements LotteryApiService {
    private final LotteryRepository lotteryRepository;
    private final UserTicketRepository userTicketRepository;

    public LotteryServiceImpl(LotteryRepository lotteryRepository, UserTicketRepository userTicketRepository) {
        this.lotteryRepository = lotteryRepository;
        this.userTicketRepository = userTicketRepository;
    }

    @Override
    @ResponseBody
    public Object showAllLotteries() {
        Map<String, List<String>> object = new HashMap<>();
        object.put("tickets", lotteryRepository.findAllLotteries());
        return object;
    }

    @Override
    public LotteryResponse createLottery(LotteryRequest lotteryRequest) {
        Lottery lottery = new Lottery();
        lottery.setTicketid(lotteryRequest.getTicketid());
        lottery.setPrice(lottery.getPrice());
        lottery.setAmount(lottery.getAmount());
        lotteryRepository.save(lottery);
        return new LotteryResponse(lottery.getTicketid());
    }

    @Override
    public UserLotteryResponse showUserLotteries(String userid) {

        List<String> listLotteries = userTicketRepository.findTicketsByUserid(userid);
        int count = listLotteries.size();
        int cost = count * 80;

        Map<String, Object> object = new HashMap<>();
        object.put("tickets", listLotteries);
        object.put("count", count);
        object.put("cost", cost);

        List<String> tickets = (List<String>) object.get("tickets");
        int countTickets = (int) object.get("count");
        int costTickets = (int) object.get("cost");

//        UserLotteryResponse userLotteryResponse = new UserLotteryResponse(tickets, countTickets, costTickets);

        return new UserLotteryResponse(tickets, countTickets, costTickets);
    }

    @Override
    @Transactional
    public TransactionIdResponse purchaseLottery(String userid, String ticketid) {
        if (lotteryRepository.existsById(ticketid)) {
            UserTicket userTicket = new UserTicket();
            userTicket.setUserid(userid);
            userTicket.setTicketid(ticketid);
            userTicketRepository.save(userTicket);
            return new TransactionIdResponse(userTicket.getId().toString());
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400),"Ticket Not Available");
        }

    }

    @Override
    @Transactional
    public Object refundLottery(String userid, String ticketid) {
        userTicketRepository.RefundLottery(userid, ticketid);
        Map<String, String> ticketId= new HashMap<>();
        ticketId.put("tickets", ticketid);
        return ticketId;
    }
}
