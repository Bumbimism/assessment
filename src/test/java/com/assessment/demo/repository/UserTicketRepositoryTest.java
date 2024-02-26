package com.assessment.demo.repository;

import com.assessment.demo.entity.UserTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTicketRepositoryTest {

    @Autowired
    UserTicketRepository userTicketRepository;

    @Test
    @DisplayName("when query findAllLotteries then return lotteryList")
    void findAllByUserId() {

        Long Id = 1L;
        String userId = "2602202488";
        String ticketId = "123123";
        int price = 80;
        int amount = 1;
        UserTicket userTicket = new UserTicket(userId,ticketId,price,amount);
        UserTicket expectedUserTicket = new UserTicket(Id,userId,ticketId,price,amount);
        List<UserTicket> expected = List.of(expectedUserTicket);

        userTicketRepository.save(userTicket);
        List<UserTicket> found = userTicketRepository.findAllByUserId(userId);

        Assertions.assertEquals(expected,found);

    }




}
