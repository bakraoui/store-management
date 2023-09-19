package com.storemanagment.inventoryservice.service;

import com.storemanagment.inventoryservice.dtos.InventoryRequest;
import com.storemanagment.inventoryservice.mapper.InventoryMapper;
import com.storemanagment.inventoryservice.models.Inventory;
import com.storemanagment.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void createInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = InventoryMapper.mapToInventory(inventoryRequest);
        inventoryRepository.save(inventory);
    }
}
