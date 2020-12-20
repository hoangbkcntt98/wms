package com.wms.demo.repository;

import com.wms.demo.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    @Query("select a FROM Inventory a ORDER BY a.inventoryClass")
    List<Inventory> findAll();
}
