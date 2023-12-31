package com.storemanagement.orderservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNumber;

//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Transient
    private List<OrderLineItems> orderLineItems;


}
