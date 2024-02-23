package com.assessment.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class LotteryResponse {
    @JsonProperty("ticket")
    private String ticketid;

    public LotteryResponse(String ticketid) {
        this.ticketid = ticketid;
    }

}
