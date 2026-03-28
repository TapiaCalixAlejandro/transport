package com.test.transport.transport.services;

import com.test.transport.transport.mappers.OrderMapper;
import com.test.transport.transport.models.dtos.OrderRequest;
import com.test.transport.transport.models.dtos.OrderResponse;
import com.test.transport.transport.models.entities.Order;
import com.test.transport.transport.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void shouldCreateOrder() {
        // GIVEN
        OrderRequest request = new OrderRequest();
        request.setOrigin("CDMX");
        request.setDestination("Acapulco");

        Order order = new Order();
        order.setId(UUID.randomUUID());

        OrderResponse response = new OrderResponse();
        response.setOrigin("CDMX");
        response.setDestination("Acapulco");

        // Configuración de Mocks
        when(orderMapper.toEntity(request)).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(orderMapper.toResponse(any(Order.class))).thenReturn(response);

        // WHEN
        OrderResponse result = orderService.createOrder(request);

        // THEN
        assertNotNull(result);
        assertEquals("CDMX", result.getOrigin());
        assertEquals("Acapulco", result.getDestination());

        // VERIFY
        verify(orderMapper).toEntity(request);
        verify(orderRepository).save(any(Order.class));
        verify(orderMapper).toResponse(any(Order.class));
    }
}
