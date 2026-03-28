package com.test.transport.transport.mappers;

import com.test.transport.transport.models.dtos.OrderRequest;
import com.test.transport.transport.models.dtos.OrderResponse;
import com.test.transport.transport.models.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequest request) {
        Order order = new Order();
        request.setOrigin(order.getOrigin());
        request.setDestination(order.getDestination());
        return order;
    }

    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus().name());
        response.setOrigin(order.getOrigin());
        response.setDestination(order.getDestination());
        return response;
    }
}
