package com.assessment.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdResponse {

    private String id;

    public IdResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

