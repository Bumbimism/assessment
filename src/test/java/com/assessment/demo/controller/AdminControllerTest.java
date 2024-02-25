package com.assessment.demo.controller;

import com.assessment.demo.request.LotteryRequest;
import com.assessment.demo.response.LotteryResponse;
import com.assessment.demo.service.LotteryApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    MockMvc mockMvc;
    @Mock
    LotteryApiService lotteryApiService;


    @BeforeEach
    void SetUp() {
        AdminController adminController = new AdminController(lotteryApiService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when post /lotteries then return ticket list")
    @WithMockUser("admin")
    void createLottery() throws Exception {

        LotteryRequest lotteryRequest = new LotteryRequest("888888", 80, 1);

        when(lotteryApiService.createLottery(lotteryRequest)).thenReturn(new LotteryResponse("888888"));

        mockMvc.perform(post("/admin/lotteries").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticket", is("888888")));

        verify(lotteryApiService).createLottery(lotteryRequest);
    }
}
