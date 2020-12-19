package com.wms.demo.services;

import com.wms.demo.dto.request.InventoryUpdateDTO;
import com.wms.demo.model.Inventory;
import com.wms.demo.repository.InventoryRepository;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    public Inventory findInventory(Integer id){
        return inventoryRepository.findById(id).orElse(null);
    }
    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }
    public int storeInventory(Inventory inventory){
        try {
            inventoryRepository.save(inventory);
        }catch (Exception e){
            return Status.NOT_FOUND;
        }
        return Status.SUCCESS;
    }
    public int updateInventory(InventoryUpdateDTO data){
        try{
            Inventory updateItem = inventoryRepository.findById(data.getId()).orElse(null);
            Inventory newData = data.getData();
            if(updateItem==null){
                return Status.NOT_FOUND;
            }else{
                updateItem.setName(newData.getName());
                updateItem.setQuantities(newData.getQuantities());
                updateItem.setExp(newData.getExp());
                updateItem.setSku(newData.getSku());
                updateItem.setStatus(newData.getStatus());
                updateItem.setType(newData.getType());
                updateItem.setPrice(newData.getPrice());
                updateItem.setClassName(newData.getClassName());
                inventoryRepository.save(updateItem);
            }
        }catch(Exception e){
            return Status.NOT_FOUND;
        }
        return Status.SUCCESS;
    }
    public int delete(Integer id){
        try {
            inventoryRepository.deleteById(id);
            return Status.SUCCESS;
        }catch (Exception e){
            return Status.ERROR;
        }
    }


}
