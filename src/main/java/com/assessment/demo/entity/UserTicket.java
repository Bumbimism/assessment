package com.assessment.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[\\d]{10}")
    private String userid;

    @Pattern(regexp = "[\\d]{6}")
    private String ticketid;

    private int amount;

}
