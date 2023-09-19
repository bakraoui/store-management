package com.storemanagement.orderservice.service;

import com.storemanagement.orderservice.dtos.OrderRequest;
import com.storemanagement.orderservice.mapper.OrderMapper;
import com.storemanagement.orderservice.models.Order;
import com.storemanagement.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderRequest orderRequest) {
        // validation
        // mapping
        Order order = OrderMapper.maptoOrder(orderRequest);

        // persist data
        orderRepository.save(order);
    }
}
