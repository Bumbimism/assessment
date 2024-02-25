package com.assessment.demo.service.impl;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.repository.UserTicketRepository;
import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.response.TransactionIdResponse;
import com.assessment.demo.response.UserTicketResponse;
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
        Map<String, String[]> lotteryList = new HashMap<>();
        lotteryList.put("tickets", lotteryRepository.findAllLotteries());
        return lotteryList;
    }

    @Override
    public LotteryResponse createLottery(LotteryRequest lotteryRequest) {
        String ticketId = lotteryRequest.getTicketId();
        int price = lotteryRequest.getPrice();
        int amount = lotteryRequest.getAmount();

        Lottery lottery = new Lottery(ticketId,price,amount);

        lotteryRepository.save(lottery);

        return new LotteryResponse(lottery.getTicketId());
    }

    @Override
    public UserTicketResponse showUserLotteries(String userId) {

        List<UserTicket> userTickets = userTicketRepository.findAllByUserId(userId);
        List<String> tickets = userTickets.stream()
                .map(UserTicket::getTicketId)
                .toList();
        int count = tickets.size();
        int cost = userTickets.stream()
                .mapToInt(UserTicket::getPrice)
                .sum();

        return new UserTicketResponse(tickets, count, cost);
    }

    @Override
    public TransactionIdResponse purchaseLottery(String userId, String ticketId) {

        if (!lotteryRepository.existsByTicketId(ticketId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket Not Available.");

        } else {
            Integer ticketPrice = lotteryRepository.findPriceByTicketId(ticketId);

            UserTicket userTicket = new UserTicket();
            userTicket.setUserId(userId);
            userTicket.setTicketId(ticketId);
            userTicket.setPrice(ticketPrice);
            userTicket.setAmount(1);

            userTicketRepository.save(userTicket);
            return new TransactionIdResponse(userTicket.getId().toString());

        }
    }

    @Override
    @Transactional
    public LotteryResponse refundLottery(String userId, String ticketId) {

        if (!userTicketRepository.existsByUseridAndTicketId(userId, ticketId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found Ticket");

        } else {
            userTicketRepository.RefundLottery(userId, ticketId);
            return new LotteryResponse(ticketId);

        }
    }
}
