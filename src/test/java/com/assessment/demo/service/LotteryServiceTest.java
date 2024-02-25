package com.assessment.demo.service;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.repository.UserTicketRepository;
import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.response.UserTicketResponse;
import com.assessment.demo.service.impl.LotteryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LotteryServiceTest {
    @Mock
    LotteryRepository lotteryRepository;
    @Mock
    UserTicketRepository userTicketRepository;

    @InjectMocks
    LotteryServiceImpl lotteryService;

    @Test
    void showAllLotteries() {

        String[] lotteryList = {"123123", "246824"};

        when(lotteryRepository.findAllLotteries()).thenReturn(lotteryList);
        Map<String, String[]> lotteryList1 = new HashMap<>();
        lotteryList1.put("tickets", lotteryList);
        Object lotteryList2 = lotteryService.showAllLotteries();

        Assertions.assertEquals(lotteryList1, lotteryList2);
    }

    @Test
    void createLottery() {
        LotteryRequest lottery = new LotteryRequest("123123", 80, 1);

        when(lotteryRepository.save(Mockito.any(Lottery.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        LotteryResponse found = lotteryService.createLottery(lottery);

        Assertions.assertEquals("123123",found.getTicketId());
    }

    @Test
    void showUserLotteries() {
        String userId = "2602202488";
        List<String> tickets = Arrays.asList("123123","246824");
        int count = 2;
        int cost = 160;

        when(userTicketRepository.findAllByUserId(userId))
                .thenReturn(Arrays.asList(
                        new UserTicket("123123",80,1),
                        new UserTicket("246824", 80,1)));
        UserTicketResponse found = lotteryService.showUserLotteries(userId);

        Assertions.assertEquals(new UserTicketResponse(tickets,count,cost),found);

    }


}
