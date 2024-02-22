package com.assessment.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LotteryRequest {
    @JsonProperty("ticket")
    @NotNull
    @Pattern(regexp = "[\\d]{6}", message = "TicketId must be contains 6 digits.")
    private String ticketid;
    private int price;
    private int amount;
}
