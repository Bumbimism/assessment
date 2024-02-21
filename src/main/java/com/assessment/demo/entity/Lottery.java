package com.assessment.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="lottery")
public class Lottery {
    @Id
    @Column(name="ticketid")
    private Long ticketid;
    private int price;
    private int amount;

}
