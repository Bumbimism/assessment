package com.assessment.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="lottery")
public class Lottery {
    @Id
    private String ticketId;
    private int price;
    private int amount;
}
