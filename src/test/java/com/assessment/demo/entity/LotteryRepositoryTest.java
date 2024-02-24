package com.assessment.demo.entity;

import com.assessment.demo.repository.LotteryRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LotteryRepositoryTest {
    @Mock
    LotteryRepository lotteryRepository;

    @BeforeEach
    void SetUp() {
    }

    @Test
    @DisplayName("JUnit test for get all lottery")
    void findAllLotteries() {
        List<String> lotteryList1 = Arrays.asList("123123","246824") ;
        Lottery lottery1 = new Lottery("123123", 80, 1);
        Lottery lottery2 = new Lottery("246824", 80, 1);

        lotteryRepository.save(lottery1);
        lotteryRepository.save(lottery2);
        String[] lotteryList2 = lotteryRepository.findAllLotteries();

        assertNotNull(lottery2);

    }
}
