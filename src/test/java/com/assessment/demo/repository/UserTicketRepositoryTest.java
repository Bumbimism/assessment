package com.assessment.demo.repository;

import com.assessment.demo.entity.Lottery;
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
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTicketRepositoryTest {

    @Autowired
    UserTicketRepository userTicketRepository;

    @Test
    @DisplayName("when query findAllByUserId then return List of User Ticket")
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

    @Test
    @DisplayName("when query existsByUserIdAndTicketId then return true")
    void existsByUserIdAndTicketId() {

        String userId = "2602202488";
        String ticketId = "123123";
        int price = 80;
        int amount = 1;
        UserTicket userTicket = new UserTicket(userId,ticketId,price,amount);

        userTicketRepository.save(userTicket);
        boolean exists = userTicketRepository.existsByUserIdAndTicketId(userId,ticketId);

        Assertions.assertTrue(exists);

    }

    @Test
    @DisplayName("when query refundLottery then return null from repository")
    void refundLottery() {

        String userId = "2602202488";
        String ticketId = "123123";
        int price = 80;
        int amount = 1;
        UserTicket userTicket = new UserTicket(userId,ticketId,price,amount);

        userTicketRepository.save(userTicket);
        userTicketRepository.refundLottery(userId,ticketId);
        boolean found = userTicketRepository.existsByUserIdAndTicketId(userId,ticketId);

        Assertions.assertFalse(found);

    }
}
