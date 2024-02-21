package com.assessment.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LotteryResponse {
    @JsonProperty("ticket")
    private String ticketid;

    public LotteryResponse(String ticketid) {
        this.ticketid = ticketid;
    }

}
