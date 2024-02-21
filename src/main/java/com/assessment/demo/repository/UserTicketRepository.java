package com.assessment.demo.repository;

import com.assessment.demo.entity.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
    UserTicket findByUserid(String userid);
}
