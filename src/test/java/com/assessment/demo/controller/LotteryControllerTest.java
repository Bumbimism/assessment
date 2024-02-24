package com.assessment.demo.controller;

import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.response.UserTicketResponse;
import com.assessment.demo.service.LotteryApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class LotteryControllerTest {

    MockMvc mockMvc;
    @Mock
    private LotteryApiService lotteryApiService;

    @BeforeEach
    public void SetUp() {
        LotteryController lotteryController = new LotteryController(lotteryApiService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when get /lotteries then return 200")
    public void showLotteries() throws Exception {
        mockMvc.perform(get("/lotteries"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when get /lotteries then return ticket list")
    void showLotteriesSuccess() throws Exception {
        List<String> tickets = List.of("123123", "246824", "888888");

        when(lotteryApiService.showAllLotteries()).thenReturn(new LotteryResponse(tickets.toString()));

        mockMvc.perform(get("/lotteries"))
                .andExpect(status().isOk());

        verify(lotteryApiService, times(1)).showAllLotteries();
    }

    @Test
    @DisplayName("when get /users/{userId}/lotteries then return 200")
    void showUserLotteries() throws Exception {
        mockMvc.perform(get("/lotteries", "2602202488"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when get /users/{userId}/lotteries then return user's ticket list")
    void showUserLotteriesSuccess() throws Exception {
        String userId = "2602202488";
        List<String> tickets = List.of("123123", "246824", "888888");
        int count = 3;
        int cost = 240;

        when(lotteryApiService.showUserLotteries(userId)).thenReturn(new UserTicketResponse(tickets, count, cost));

        mockMvc.perform(get("/users/{userId}/lotteries", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets", is(List.of("123123", "246824", "888888"))))
                .andExpect(jsonPath("$.cost", is(240)))
                .andExpect(jsonPath("$.count", is(3)));
    }

    @Test
    @DisplayName("when post /users/{userId}/lotteries/{ticketId} then return 201")
    void purchaseLottery() throws Exception {

        mockMvc.perform(post("/users/{userId}/lotteries/{ticketId}", "2602202488","888888"))
                .andExpect(status().isCreated());
    }

//    @Test
//    @DisplayName("when get /users/{userId}/lotteries then return user's ticket list")
//    void purchaseLotterySuccess() throws Exception {
//        String userId = "2602202488";
//        List<String> tickets = List.of("123123", "246824", "888888");
//        int count = 3;
//        int cost = 240;
//
//        when(lotteryApiService.showUserLotteries(userId)).thenReturn(new UserTicketResponse(tickets, count, cost));
//
//        mockMvc.perform(get("/users/{userId}/lotteries", userId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.tickets", is(List.of("123123", "246824", "888888"))))
//                .andExpect(jsonPath("$.cost", is(240)))
//                .andExpect(jsonPath("$.count", is(3)));
//    }

}
