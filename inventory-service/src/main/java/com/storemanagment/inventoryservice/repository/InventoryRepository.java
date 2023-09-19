package com.storemanagment.inventoryservice.repository;

import com.storemanagment.inventoryservice.models.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
}
