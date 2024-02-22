package com.assessment.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "lottery")
public class Lottery {

    @Id
    @Pattern(regexp = "[\\d]{6}")
    @Column(name = "ticketid")
    @JsonProperty("ticket")
    private String ticketid;

    private int price;

    private int amount;

}
