package com.test.transport.transport.repositories;

import com.test.transport.transport.models.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
    List<Driver> findByActiveTrue();
}
