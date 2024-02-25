package com.assessment.demo.service;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.repository.LotteryRepository;
import com.assessment.demo.repository.UserTicketRepository;
import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.response.TransactionIdResponse;
import com.assessment.demo.response.UserTicketResponse;
import com.assessment.demo.service.impl.LotteryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LotteryServiceTest {

    @Mock
    LotteryRepository lotteryRepository;
    @Mock
    UserTicketRepository userTicketRepository;

    @InjectMocks
    LotteryServiceImpl lotteryService;

    @Test
    @DisplayName("when call show all lotteries return ticket list")
    void showAllLotteries() {

        String[] lotteryList = {"123123", "246824"};

        when(lotteryRepository.findAllLotteries())
                .thenReturn(lotteryList);
        Map<String, String[]> lotteryList1 = new HashMap<>();
        lotteryList1.put("tickets", lotteryList);
        Object lotteryList2 = lotteryService.showAllLotteries();

        Assertions.assertEquals(lotteryList1, lotteryList2);

        verify(lotteryRepository).findAllLotteries();
    }

    @Test
    @DisplayName("when create lottery return ticketId")
    void createLottery() {

        LotteryRequest lottery = new LotteryRequest("123123", 80, 1);

        when(lotteryRepository.save(any(Lottery.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        LotteryResponse found = lotteryService.createLottery(lottery);
        Assertions.assertEquals("123123", found.getTicketId());

        verify(lotteryRepository).save(any(Lottery.class));

    }

    @Test
    @DisplayName("when call show user lotteries return ticket list, count, cost")
    void showUserLotteries() {

        String userId = "2602202488";
        List<String> tickets = Arrays.asList("123123", "246824");
        UserTicket ticket1 = new UserTicket("123123", 80, 1);
        UserTicket ticket2 = new UserTicket("246824", 80, 1);
        int count = 2;
        int cost = 160;

        when(userTicketRepository.findAllByUserId(userId)).thenReturn(Arrays.asList(ticket1,ticket2));
        UserTicketResponse found = lotteryService.showUserLotteries(userId);

        Assertions.assertEquals(new UserTicketResponse(tickets,count,cost),found);

        verify(userTicketRepository).findAllByUserId(userId);

    }

    @Test
    @DisplayName("when call purchase lottery return transaction id")
    void purchaseLottery() {

        Long Id = 888L;
        String userId = "2602202488";
        String ticketId = "123123";
        int price = 80;
        int amount = 1;
        UserTicket userTicket = new UserTicket(userId,ticketId,price,amount);

        when(lotteryRepository.existsByTicketId(ticketId)).thenReturn(true);
        when(lotteryRepository.findPriceByTicketId(ticketId)).thenReturn(price);
        doAnswer(invocation -> {
            ReflectionTestUtils
                    .setField((UserTicket) invocation
                    .getArgument(0), "Id", Id);
            return null;
        }).when(userTicketRepository).save(userTicket);
        TransactionIdResponse id = lotteryService.purchaseLottery(userId,ticketId);

        Assertions.assertNotNull(id);
        Assertions.assertEquals("888", id.getId());

        verify(lotteryRepository).existsByTicketId(ticketId);
        verify(lotteryRepository).findPriceByTicketId(ticketId);
        verify(userTicketRepository).save(any(UserTicket.class));

    }

    @Test
    @DisplayName("when call purchase lottery then return 400 bad request")
    void purchaseLotteryBadRequest() {

        String userId = "2602202488";
        String ticketId = "123123";

        when(lotteryRepository.existsByTicketId(ticketId)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> lotteryService.purchaseLottery(userId, ticketId));

        verify(lotteryRepository).existsByTicketId(ticketId);
        verify(lotteryRepository, never()).findPriceByTicketId(ticketId);
        verify(lotteryRepository, never()).save(any());

    }

    @Test
    @DisplayName("when call purchase lottery return transaction id")
    void refundLottery() {

        String userId = "2602202488";
        String ticketId = "123123";
        LotteryResponse ticket = new LotteryResponse(ticketId);

        when(userTicketRepository.existsByUseridAndTicketId(userId, ticketId)).thenReturn(true);
        LotteryResponse found = lotteryService.refundLottery(userId, ticketId);

        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(ticket, found);

        verify(userTicketRepository).existsByUseridAndTicketId(userId, ticketId);

    }

    @Test
    @DisplayName("when call purchase lottery return 404")
    void refundLotteryNotFoundTicket() {

        String userId = "2602202488";
        String ticketId = "123123";

        when(userTicketRepository.existsByUseridAndTicketId(userId, ticketId)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> lotteryService.refundLottery(userId, ticketId));

        verify(userTicketRepository, never()).RefundLottery(userId, ticketId);

    }

}
