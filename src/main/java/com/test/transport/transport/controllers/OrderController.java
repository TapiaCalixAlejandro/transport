package com.test.transport.transport.controllers;

import com.test.transport.transport.models.dtos.OrderRequest;
import com.test.transport.transport.models.dtos.OrderResponse;
import com.test.transport.transport.models.dtos.OrderStatus;
import com.test.transport.transport.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @GetMapping
//    public ResponseEntity<List<OrderResponse>> list() {
//        return ResponseEntity.ok(orderService.listOrder());
//    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> find(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.findOrder(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable UUID id, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }

    @PostMapping("/{orderId}/assign")
    public ResponseEntity<OrderResponse> assignDriver(
            @PathVariable UUID orderId,
            @RequestParam UUID driverId,
            @RequestParam(required = false) MultipartFile pdf,
            @RequestParam(required = false) MultipartFile image
    ) throws IOException {
        return ResponseEntity.ok(orderService.assignDriver(orderId, driverId, pdf, image));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> filter(
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String destination
    ) {
        return ResponseEntity.ok(orderService.filter(status, origin, destination));
    }
}
