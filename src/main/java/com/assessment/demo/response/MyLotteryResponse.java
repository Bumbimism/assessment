package com.assessment.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"tickets","count","cost"})
public class MyLotteryResponse {
    @JsonProperty("tickets")
    private String ticketid;
    private int count;
    private int cost;

    public MyLotteryResponse(String ticketid, int count, int cost) {
        this.ticketid = ticketid;
        this.count = count;
        this.cost = cost;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
