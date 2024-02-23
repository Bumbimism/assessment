package com.assessment.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
@Data
@JsonPropertyOrder({"tickets", "count", "cost"})
public class UserTicketResponse {
    @JsonProperty("tickets")
    private List<String> ticketid;
    private int count;
    private int cost;

    public UserTicketResponse(List<String> ticketid, int count, int cost) {
        this.ticketid = ticketid;
        this.count = count;
        this.cost = cost;
    }

}
