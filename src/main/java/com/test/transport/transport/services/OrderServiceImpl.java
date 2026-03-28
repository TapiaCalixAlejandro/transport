package com.test.transport.transport.services;

import com.test.transport.transport.exceptions.ResourceNotFoundException;
import com.test.transport.transport.mappers.OrderMapper;
import com.test.transport.transport.models.dtos.OrderRequest;
import com.test.transport.transport.models.dtos.OrderResponse;
import com.test.transport.transport.models.dtos.OrderStatus;
import com.test.transport.transport.models.entities.Driver;
import com.test.transport.transport.models.entities.Order;
import com.test.transport.transport.repositories.DriverRepository;
import com.test.transport.transport.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            DriverRepository driverRepository,
            OrderMapper orderMapper
    ) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderResponse> listOrder() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toResponse).toList();
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = orderMapper.toEntity(request);
        //orderRepository.save(request);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse findOrder(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));
        return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponse updateStatus(UUID id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        validateTransition(order.getStatus(), status);
        order.setStatus(status);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    private void validateTransition(OrderStatus status, OrderStatus next) {
        if (status == OrderStatus.CREATED && next == OrderStatus.IN_TRANSIT) return;
        if (status == OrderStatus.IN_TRANSIT && next == OrderStatus.DELIVERED) return;
        if (next == OrderStatus.CANCELLED) return;

        throw new RuntimeException("Estado de transición invalido");
    }

    @Override
    public OrderResponse assignDriver(UUID orderId, UUID driverId, MultipartFile pdf, MultipartFile image) throws IOException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor no encontrado"));

        if (!driver.getActive()) throw new RuntimeException("Conductor no activo");

        if (order.getStatus() != OrderStatus.CREATED) throw new RuntimeException("El pedido de estar con estado CREATED");

        saveFile(pdf);
        saveFile(image);
        order.setDriver(driver);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    private void saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return;

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File destination = new File(directory, fileName);

        file.transferTo(destination);
    }

    @Override
    public List<OrderResponse> filter(
            OrderStatus status,
            String origin,
            String destination) {

        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .filter(o -> status == null || o.getStatus() == status)
                .filter(o -> origin == null || o.getOrigin().toLowerCase().contains(origin.toLowerCase()))
                .filter(o -> destination == null || o.getDestination().toLowerCase().contains(destination.toLowerCase()))
                .map(orderMapper::toResponse)
                .toList();
    }
}
