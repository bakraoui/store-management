package com.storemanagement.orderservice.mapper;

import com.storemanagement.orderservice.dtos.OrderLineItemsRequest;
import com.storemanagement.orderservice.dtos.OrderRequest;
import com.storemanagement.orderservice.models.Order;
import com.storemanagement.orderservice.models.OrderLineItems;

import java.util.UUID;

public class OrderMapper {

    public static Order maptoOrder(OrderRequest orderRequest) {
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(
                        orderRequest.getOrderLineItems()
                                .stream()
                                .map(OrderMapper::mapToOrLineItems)
                                .toList()
                )
                .build();
    }

    public static OrderLineItems mapToOrLineItems(OrderLineItemsRequest orderLIneItemsRequest) {
        return OrderLineItems.builder()
                .price(orderLIneItemsRequest.getPrice())
                .skuCode(orderLIneItemsRequest.getSkuCode())
                .quantity(orderLIneItemsRequest.getQuantity())
                .build();
    }
}
