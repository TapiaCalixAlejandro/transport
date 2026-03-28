package com.test.transport.transport.mappers;

import com.test.transport.transport.models.dtos.DriverRequest;
import com.test.transport.transport.models.dtos.DriverResponse;
import com.test.transport.transport.models.entities.Driver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DriverMapper {
    public Driver toEntity(DriverRequest request) {
        Driver driver = new Driver();
        driver.setName(request.getName());
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setActive(request.isActive());
        return driver;
    }

    public DriverResponse toResponse(Driver driver) {
        DriverResponse response = new DriverResponse();
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setLicenseNumber(driver.getLicenseNumber());
        response.setActive(driver.getActive());
        return response;
    }
}
