package com.assessment.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LotteryResponse {
    @JsonProperty("ticket")
    private String ticketid;

    public LotteryResponse(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }
}
