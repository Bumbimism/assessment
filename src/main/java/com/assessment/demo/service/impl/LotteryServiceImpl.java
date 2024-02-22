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
import org.springframework.http.HttpStatus;
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
        lottery.setTicketId(lotteryRequest.getTicketid());
        lottery.setPrice(lottery.getPrice());
        lottery.setAmount(lottery.getAmount());
        lotteryRepository.save(lottery);
        return new LotteryResponse(lottery.getTicketId());
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

        return new UserLotteryResponse(tickets, countTickets, costTickets);
    }

    @Override
    @Transactional
    public TransactionIdResponse purchaseLottery(String userId, String ticketId) {
        if (lotteryRepository.existsById(ticketId)) {
            UserTicket userTicket = new UserTicket();
            userTicket.setUserId(userId);
            userTicket.setTicketId(ticketId);
            userTicketRepository.save(userTicket);
            return new TransactionIdResponse(userTicket.getId().toString());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket Not Available.");
        }

    }

    @Override
    @Transactional
    public Object refundLottery(String userId, String ticketId) {
        if (!userTicketRepository.existsByUseridAndTicketId(userId, ticketId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found Ticket");

        } else {
            userTicketRepository.RefundLottery(userId, ticketId);
            Map<String, String> ticket = new HashMap<>();
            ticket.put("tickets", ticketId);
            return ticket;

        }
    }
}
