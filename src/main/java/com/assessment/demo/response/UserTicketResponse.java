package com.assessment.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
@Data
@JsonPropertyOrder({"tickets", "count", "cost"})
public class UserTicketResponse {
    @JsonProperty("tickets")
    private List<String> ticketId;
    private int count;
    private int cost;

    public UserTicketResponse(List<String> ticketId, int count, int cost) {
        this.ticketId = ticketId;
        this.count = count;
        this.cost = cost;
    }

}
