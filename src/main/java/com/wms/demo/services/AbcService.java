package com.wms.demo.services;


import com.wms.demo.model.Abc;
import com.wms.demo.model.AnnualDemand;
import com.wms.demo.repository.AbcRepository;
import com.wms.demo.repository.AnnualDemandRepository;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbcService {
    @Autowired
    AbcRepository abcRepository;
    public List<Abc> getAll(){
        return abcRepository.findAll();
    }
    public int createAbc(Abc newItem){
        try{
            abcRepository.save(newItem);
        }catch(Exception e){
            System.out.println(e);
            return Status.ERROR;
        }
        return Status.SUCCESS;
    }
//    public int updateAbc(Integer id,Abc data){
//        try{
//            Abc updateItem = abcRepository.findById(id).orElse(null);
//            updateItem.setName(data.getName());
//            updateItem.setRange(data.getRange());
//            abcRepository.save(updateItem);
//        }catch (Exception e){
//            return Status.ERROR;
//        }
//        return Status.SUCCESS;
//    }
    public int deleteAbc(Integer id){
        try{
            abcRepository.deleteById(id);
        }catch (Exception e){
            return Status.ERROR;
        }
        return Status.SUCCESS;
    }
}
