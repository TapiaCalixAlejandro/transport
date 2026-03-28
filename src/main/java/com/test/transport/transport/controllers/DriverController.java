package com.test.transport.transport.controllers;

import com.test.transport.transport.models.dtos.DriverRequest;
import com.test.transport.transport.models.dtos.DriverResponse;
import com.test.transport.transport.models.entities.Driver;
import com.test.transport.transport.services.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/active")
    public ResponseEntity<List<DriverResponse>> list() {
        return ResponseEntity.ok(driverService.listActiveDriver());
    }

    @PostMapping
    public ResponseEntity<DriverResponse> create(@Valid @RequestBody DriverRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.createDriver(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> find(@PathVariable UUID id) {
        return ResponseEntity.ok(driverService.findDriver(id));
    }
}
