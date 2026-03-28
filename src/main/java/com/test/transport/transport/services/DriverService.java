package com.test.transport.transport.services;

import com.test.transport.transport.models.dtos.DriverRequest;
import com.test.transport.transport.models.dtos.DriverResponse;
import com.test.transport.transport.models.entities.Driver;

import java.util.List;
import java.util.UUID;

public interface DriverService {
    List<DriverResponse> listActiveDriver();
    DriverResponse createDriver(DriverRequest request);
    DriverResponse findDriver(UUID id);
}
