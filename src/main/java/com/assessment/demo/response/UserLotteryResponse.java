package com.assessment.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"tickets", "count", "cost"})
public class UserLotteryResponse {
    @JsonProperty("tickets")
    private List<String> ticketid;
    private int count;
    private int cost;

    public UserLotteryResponse(List<String> ticketid, int count, int cost) {
        this.ticketid = ticketid;
        this.count = count;
        this.cost = cost;
    }

}
