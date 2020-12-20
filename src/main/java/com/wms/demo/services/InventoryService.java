package com.wms.demo.services;

import com.wms.demo.dto.request.InventoryUpdateDTO;
import com.wms.demo.model.AnnualDemand;
import com.wms.demo.model.Inventory;
import com.wms.demo.repository.AnnualDemandRepository;
import com.wms.demo.repository.InventoryRepository;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    AnnualDemandRepository annualDemandRepository;
    public Inventory findInventory(Integer id){
        return inventoryRepository.findById(id).orElse(null);
    }
    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }
    public int createInventory(Inventory inventory){
        try {
            inventoryRepository.save(inventory);
        }catch (Exception e){
            return Status.NOT_FOUND;
        }
        return Status.SUCCESS;
    }
    public int updateInventory(Integer id,Inventory data){
        try{
            Inventory updateItem = inventoryRepository.findById(data.getId()).orElse(null);
            if(updateItem==null){
                return Status.NOT_FOUND;
            }else{
                updateItem.setName(data.getName());
                updateItem.setQuantities(data.getQuantities());
                updateItem.setExp(data.getExp());
                updateItem.setSku(data.getSku());
                updateItem.setStatus(data.getStatus());
                updateItem.setType(data.getType());
                updateItem.setPrice(data.getPrice());
                updateItem.setInventoryClass(data.getInventoryClass());
                inventoryRepository.save(updateItem);
            }
        }catch(Exception e){
            return Status.NOT_FOUND;
        }
        return Status.SUCCESS;
    }
    public int deleteInventory(Integer id){
        try {
            inventoryRepository.deleteById(id);
            return Status.SUCCESS;
        }catch (Exception e){
            return Status.ERROR;
        }
    }
    public int ABCAnalysis(){
        double totalUsageValue=0.0;
        double cumulativeProportion =0.0;
        double aClass= 81;
        double bClass= 96;
        double cClass = 101;
        try{
            List<AnnualDemand> annualDemandList = annualDemandRepository.findAll();
            for(AnnualDemand item:annualDemandList){
                Inventory inventory = item.getInventory();
                totalUsageValue+= item.getAnnualDemand()*inventory.getPrice();
            }
            for(AnnualDemand item : annualDemandList){
                Inventory inventory = item.getInventory();
                double annualUsageValue = item.getAnnualDemand()*inventory.getPrice();
                double usageValueProportion = annualUsageValue/totalUsageValue*100;
                cumulativeProportion+=usageValueProportion;
                item.setCumulativeProportion(cumulativeProportion);
                if(cumulativeProportion<81){
                    inventory.setInventoryClass("A");
                }
                else if(cumulativeProportion<96&&cumulativeProportion>=81){
                    inventory.setInventoryClass("B");
                }else{
                    inventory.setInventoryClass("C");
                }
                annualDemandRepository.save(item);
            }


        }catch (Exception e){
            System.out.println(e);
            return Status.ERROR;
        }

        return Status.SUCCESS;
    }
    public void classificateInventory(AnnualDemand item,Double totalAmount,Double totalUsageValue){
        Inventory inventory = item.getInventory();
        double annualUsageValue = item.getAnnualDemand()*inventory.getPrice();
        double amount = item.getAnnualDemand();
        double amountProportion = (amount/totalAmount)*100;
        double usageValueProportion = annualUsageValue/totalUsageValue*100;
        String className="NULL";
//        amountProportion = Math.round((amountProportion*100)/100);
//        usageValueProportion = Math.round((usageValueProportion*100)/100);
        if((amountProportion<20)&&(usageValueProportion>=70)) {

            className= "A";
        }
        else if((amountProportion>50)&&(usageValueProportion<=10)){
            className= "C";
        }
        else if(((amountProportion>=20)&&(amountProportion<=50))&&((usageValueProportion<70)&&(usageValueProportion>10))){
            className= "B";
        }
        inventory.setInventoryClass(className);
        item.setUsageValProportion(usageValueProportion);
        item.setAnnualUsageValue(annualUsageValue);
        annualDemandRepository.save(item);
    }


}
