package com.test.transport.transport.services;

import com.test.transport.transport.exceptions.ResourceNotFoundException;
import com.test.transport.transport.mappers.DriverMapper;
import com.test.transport.transport.models.dtos.DriverRequest;
import com.test.transport.transport.models.dtos.DriverResponse;
import com.test.transport.transport.models.entities.Driver;
import com.test.transport.transport.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public DriverServiceImpl(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    @Override
    public List<DriverResponse> listActiveDriver() {
        return driverRepository.findByActiveTrue()
                .stream()
                .map(driverMapper::toResponse)
                .toList();
    }

    @Override
    public DriverResponse createDriver(DriverRequest request) {
        Driver driver = driverMapper.toEntity(request);
        return driverMapper.toResponse(driverRepository.save(driver));
    }

    @Override
    public DriverResponse findDriver(UUID id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor no encontrado"));

        return driverMapper.toResponse(driver);
    }
}
