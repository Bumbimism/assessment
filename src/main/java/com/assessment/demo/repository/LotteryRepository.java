package com.assessment.demo.repository;

import com.assessment.demo.entity.Lottery;
import com.assessment.demo.response.TicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LotteryRepository extends CrudRepository<Lottery,String> {
List<Lottery> findAll();
}
