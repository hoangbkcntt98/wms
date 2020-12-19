package com.wms.demo.controller;

import com.wms.demo.common.ResourceResponse;
import com.wms.demo.model.Inventory;
import com.wms.demo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/inventory/")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;
    @GetMapping("")
    public ResourceResponse getAll(){
        List<Inventory> inventories = inventoryRepository.findAll();
        return new ResourceResponse(inventories);
    }


}
