package com.assessment.demo.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TransactionIdResponse {

    private String id;

    public TransactionIdResponse(String id) {
        this.id = id;
    }

}

