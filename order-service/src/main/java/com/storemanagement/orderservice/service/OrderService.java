package com.storemanagement.orderservice.service;

import com.storemanagement.orderservice.dtos.InventoryResponse;
import com.storemanagement.orderservice.dtos.OrderLineItemsRequest;
import com.storemanagement.orderservice.dtos.OrderRequest;
import com.storemanagement.orderservice.mapper.OrderMapper;
import com.storemanagement.orderservice.models.Order;
import com.storemanagement.orderservice.models.OrderLineItems;
import com.storemanagement.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public OrderService(OrderRepository orderRepository, WebClient webClient) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
    }

    public void createOrder(OrderRequest orderRequest) {
        // validation
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems()
                .stream()
                .map(OrderMapper::mapToOrLineItems)
                .toList();
        // mapping
        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = orderRequest.getOrderLineItems().stream()
                        .map(OrderLineItemsRequest::getSkuCode)
                                .toList();
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventories/", uriBuilder -> uriBuilder.queryParam("skueCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        if (inventoryResponses == null) {
            throw new RuntimeException("No inventory found with the given IDs.");
        }
        boolean result = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (!result) {
            throw new RuntimeException("Some products quantity is not enough in stock");
        }
        // persist data
        orderRepository.save(order);
    }
}
