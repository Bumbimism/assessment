package com.assessment.demo.repository;

import com.assessment.demo.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery, String> {
}
