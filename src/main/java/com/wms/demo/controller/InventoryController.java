package com.wms.demo.controller;

import com.wms.demo.common.ResourceResponse;
import com.wms.demo.dto.request.InventoryUpdateDTO;
import com.wms.demo.model.Inventory;
import com.wms.demo.services.InventoryService;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @GetMapping("all")
    public ResourceResponse getAll(){
        List<Inventory> inventories = inventoryService.getAllInventory();
        if(inventories.size()==0){
            return new ResourceResponse(Status.ERROR_EMPTY_STOCK,"Empty");
        }
        return new ResourceResponse(inventories);
    }
    @GetMapping("{id}")
    public ResourceResponse find(@PathVariable Integer id){
        Inventory inventory = inventoryService.findInventory(id);
        if(inventory==null){
            return new ResourceResponse(Status.NOT_FOUND,"Cannot found");
        }
        return new ResourceResponse(Status.SUCCESS,"result !",inventory);
    }
    @PostMapping("create")
    public ResourceResponse store(@RequestBody Inventory inventory){
        Integer status = inventoryService.createInventory(inventory);
        return new ResourceResponse(status,null);
    }
    @PostMapping("update")
    public ResourceResponse update(@RequestBody InventoryUpdateDTO updateInventory){
        Integer status = inventoryService.updateInventory(updateInventory.getId(),updateInventory.getData());
        return new ResourceResponse(status,null);
    }
    @DeleteMapping("delete/{id}")
    public ResourceResponse delete(@PathVariable Integer id){
        Integer status = inventoryService.deleteInventory(id);
        return new ResourceResponse(status,null);
    }
    @GetMapping("abcAnalysis")
    public ResourceResponse ABCAnalysis(){
        Integer status = inventoryService.ABCAnalysis();
        List<Inventory> inventoryList = inventoryService.getAllInventory();
        return new ResourceResponse(status,"ABCAnalysis",inventoryList);
    }


}
