package com.assessment.demo.repository;

import com.assessment.demo.entity.Lottery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LotteryRepository extends CrudRepository<Lottery, String> {
    @Query(value = "SELECT lottery.ticketid FROM lottery", nativeQuery = true)
    List<String> findAllLotteries();

    @Override
    boolean existsById(String ticketid);
}
