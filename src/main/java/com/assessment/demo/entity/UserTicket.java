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
    private Long Id;

    @Pattern(regexp = "[\\d]{10}")
    @Column(name = "user_id")
    private String userId;

    @Pattern(regexp = "[\\d]{6}")
    @Column(name = "ticket_id")
    private String ticketId;

    private int amount;

}
