package com.storemanagment.inventoryservice.mapper;

import com.storemanagment.inventoryservice.dtos.InventoryRequest;
import com.storemanagment.inventoryservice.models.Inventory;

public class InventoryMapper {
    public static Inventory mapToInventory(InventoryRequest inventoryRequest) {

        return Inventory.builder().build();
    }
}
