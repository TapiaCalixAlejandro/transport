package com.test.transport.transport.repositories;

import com.test.transport.transport.models.dtos.OrderStatus;
import com.test.transport.transport.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByOriginContainingIgnoreCase(String origin);
    List<Order> findByDestinationContainingIgnoreCase(String destination);
}
