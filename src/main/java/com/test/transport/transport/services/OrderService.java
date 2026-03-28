package com.test.transport.transport.services;

import com.test.transport.transport.models.dtos.OrderRequest;
import com.test.transport.transport.models.dtos.OrderResponse;
import com.test.transport.transport.models.dtos.OrderStatus;
import com.test.transport.transport.models.entities.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderResponse> listOrder();
    OrderResponse createOrder(OrderRequest request);
    OrderResponse findOrder(UUID id);

    OrderResponse updateStatus(UUID id, OrderStatus status);
    OrderResponse assignDriver(UUID orderId, UUID driverId, MultipartFile pdf, MultipartFile image) throws IOException;
    List<OrderResponse> filter(OrderStatus status, String origin, String destination);
}
