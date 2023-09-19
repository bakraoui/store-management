package com.storemanagement.orderservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderRequest {
    private List<OrderLIneItemsRequest> orderLineItems = new ArrayList<OrderLIneItemsRequest>();
}
