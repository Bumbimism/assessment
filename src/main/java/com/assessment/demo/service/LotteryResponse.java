package com.assessment.demo.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LotteryResponse {
    @JsonProperty("ticket")
    public String ticketid;

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
