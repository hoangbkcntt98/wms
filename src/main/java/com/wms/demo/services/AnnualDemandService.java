package com.wms.demo.services;

import com.wms.demo.model.AnnualDemand;
import com.wms.demo.repository.AnnualDemandRepository;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnualDemandService {
    @Autowired
    AnnualDemandRepository annualDemandRepository;
    public List<AnnualDemand> getAll(){
        return annualDemandRepository.findAll();
    }
    public int createAnnualDemand(AnnualDemand newItem){
        try{
            annualDemandRepository.save(newItem);
        }catch(Exception e){
            return Status.ERROR;
        }
        return Status.SUCCESS;
    }
    public int updateAnnualDemand(Integer id,AnnualDemand data){
        try{
            AnnualDemand updateItem = annualDemandRepository.findById(id).orElse(null);
            updateItem.setInventory(data.getInventory());
            updateItem.setAnnualDemand(data.getAnnualDemand());
            updateItem.setUsageValProportion(data.getUsageValProportion());
            updateItem.setAnnualUsageValue(data.getAnnualUsageValue());
        }catch (Exception e){
            return Status.ERROR;
        }
        return Status.SUCCESS;
    }
    public int deleteAnnualDemand(Integer id){
        try{
            annualDemandRepository.deleteById(id);
        }catch (Exception e){
            return Status.ERROR;
        }
        return Status.SUCCESS;
    }
}
