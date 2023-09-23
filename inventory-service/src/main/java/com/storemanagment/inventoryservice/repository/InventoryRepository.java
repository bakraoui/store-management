package com.storemanagment.inventoryservice.repository;

import com.storemanagment.inventoryservice.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
