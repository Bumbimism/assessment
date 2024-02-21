package com.assessment.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="lottery")
public class Lottery {
    @Id
    @Column(name="ticketid")
    @JsonProperty("ticket")
    private String ticketid;
    private int price;
    private int amount;

}
