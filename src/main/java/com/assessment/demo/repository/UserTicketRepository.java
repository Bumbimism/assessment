package com.assessment.demo.repository;

import com.assessment.demo.entity.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserTicketRepository extends JpaRepository<UserTicket, String> {
@Query(value = "SELECT user_ticket.ticketid FROM user_ticket where userid = :userid", nativeQuery = true)
    List<String> findTicketsByUserid(String userid);
}
