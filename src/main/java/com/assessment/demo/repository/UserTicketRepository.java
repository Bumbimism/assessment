package com.assessment.demo.repository;

import com.assessment.demo.entity.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTicketRepository extends JpaRepository<UserTicket, String> {

    List<UserTicket> findAllByUserId(String UserId);

    @Query(value = "SELECT COUNT(scen) > 0 FROM user_ticket scen WHERE scen.ticket_id = :ticket_id AND scen.user_id = :user_id ", nativeQuery = true)
    boolean existsByUserIdAndTicketId(@Param("user_id") String userId, @Param("ticket_id") String ticketId);

    @Modifying
    @Query(value = "DELETE FROM user_ticket WHERE user_id = :user_id and ticket_id = :ticket_id", nativeQuery = true)
    void refundLottery(@Param("user_id") String userId, @Param("ticket_id") String ticketId);

}
