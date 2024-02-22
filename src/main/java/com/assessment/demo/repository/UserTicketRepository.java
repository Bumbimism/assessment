package com.assessment.demo.repository;

import com.assessment.demo.entity.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTicketRepository extends JpaRepository<UserTicket, String> {
    @Query(value = "SELECT user_ticket.ticketid FROM user_ticket where userid = :userid", nativeQuery = true)
    List<String> findTicketsByUserid(String userid);
    @Query(value = "SELECT COUNT(scen) > 0 FROM user_ticket scen WHERE scen.ticketid = :ticketid AND scen.userid = :userid ", nativeQuery = true)
    boolean existsByUseridAndTicketid(@Param("userid")String userid, @Param("ticketid") String ticketid);

    @Modifying
    @Query(value = "DELETE FROM user_ticket WHERE userid= :userid and ticketid = :ticketid", nativeQuery = true)
    void RefundLottery(@Param("userid") String userid, @Param("ticketid") String ticketid);
}
