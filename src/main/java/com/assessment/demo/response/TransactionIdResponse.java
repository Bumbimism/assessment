package com.assessment.demo.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionIdResponse {

    private String id;

    public TransactionIdResponse(String id) {
        this.id = id;
    }

}

