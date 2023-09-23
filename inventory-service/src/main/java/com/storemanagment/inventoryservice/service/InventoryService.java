package com.storemanagment.inventoryservice.service;

import com.storemanagment.inventoryservice.dtos.InventoryRequest;
import com.storemanagment.inventoryservice.dtos.InventoryResponse;
import com.storemanagment.inventoryservice.mapper.InventoryMapper;
import com.storemanagment.inventoryservice.models.Inventory;
import com.storemanagment.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .inStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
