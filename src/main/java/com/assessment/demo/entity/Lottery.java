package com.assessment.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "lottery")
public class Lottery {

    @Id
    @Column(name = "ticket_id")
    @JsonProperty("ticket")
    @Pattern(regexp = "[\\d]{6}")
    private String ticketId;

    private int price;

    private int amount;

    public Lottery() {

    }

    public Lottery(String ticketId, int price, int amount) {
        this.ticketId = ticketId;
        this.price = price;
        this.amount = amount;
    }


}
