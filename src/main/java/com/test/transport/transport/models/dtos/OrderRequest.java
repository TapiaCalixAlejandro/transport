package com.test.transport.transport.models.dtos;

import jakarta.validation.constraints.NotBlank;

public class OrderRequest {
    @NotBlank
    private String origin;
    @NotBlank
    private String destination;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
