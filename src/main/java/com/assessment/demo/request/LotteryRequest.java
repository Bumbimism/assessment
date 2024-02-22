package com.assessment.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LotteryRequest {
    @NotNull
    @Size(min = 6, max = 6, message = "Ticket id must consist of 6 characters")
    @JsonProperty("ticket")
    private String ticketid;
    private int price;
    private int amount;
}
