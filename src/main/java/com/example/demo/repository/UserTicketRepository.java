package com.example.demo.repository;

import com.example.demo.entity.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
}
