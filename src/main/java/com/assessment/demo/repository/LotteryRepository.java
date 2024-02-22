package com.assessment.demo.repository;

import com.assessment.demo.entity.Lottery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotteryRepository extends CrudRepository<Lottery, String> {
    @Query(value = "SELECT lottery.ticket_id FROM lottery", nativeQuery = true)
    List<String> findAllLotteries();

    @Query(value = "SELECT COUNT(scen) > 0 FROM lottery scen WHERE scen.ticket_id = :ticket_id", nativeQuery = true)
    boolean existsByTicketId(@Param("ticket_id") String ticketId);
}
