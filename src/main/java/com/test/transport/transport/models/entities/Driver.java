package com.test.transport.transport.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(name = "license_number")
    private String licenseNumber;
    private Boolean active;

    public Driver() {
    }

    public Driver(UUID id, String name, String licenseNumber, Boolean active) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
