package com.assessment.demo.repository;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.entity.UserTicket;
import com.assessment.demo.response.UserTicketResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LotteryRepository extends JpaRepository<Lottery, String> {
    @Query(value = "SELECT ticket_id as tickets FROM lottery", nativeQuery = true)
    String[] findAllLotteries();
    @Query(value = "SELECT price FROM lottery WHERE ticket_id = :ticket_id", nativeQuery = true)
    Integer findPriceByTicketId(@Param("ticket_id") String ticketId);

    @Query(value = "SELECT COUNT(scen) > 0 FROM lottery scen WHERE scen.ticket_id = :ticket_id", nativeQuery = true)
    boolean existsByTicketId(@Param("ticket_id") String ticketId);
}
