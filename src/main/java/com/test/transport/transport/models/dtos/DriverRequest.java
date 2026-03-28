package com.test.transport.transport.models.dtos;

import jakarta.validation.constraints.NotBlank;

public class DriverRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String licenseNumber;
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
