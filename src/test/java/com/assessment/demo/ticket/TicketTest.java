package com.assessment.demo.ticket;


import com.assessment.demo.entity.Lottery;
import com.assessment.demo.repository.LotteryRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TicketTest {
    private final MockMvc mockMvc;
    private final LotteryRepository lotteryRepository;

    public TicketTest(MockMvc mockMvc, LotteryRepository lotteryRepository) {
        this.mockMvc = mockMvc;
        this.lotteryRepository = lotteryRepository;
    }

    //    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PetRepository petRepository;
    @WithMockUser(username="admin",roles={"ADMIN"})
    @Test
    void createLottery() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """

                                                {
                                            "ticket": "888888",
                                            "price": "80",
                                            "amount": "1"
                                        }
                                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticket").value("888888"))
                .andReturn();

        // Extract JSON response body
        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Use JsonPath to extract data
        Integer ticket = JsonPath.read(jsonResponse, "$.ticket");

        Optional<Lottery> Ticket = lotteryRepository.findById(String.valueOf(ticket));
        assert Ticket.isPresent();
        assert Ticket.get().getTicketId().equals("888888");
    }
}
