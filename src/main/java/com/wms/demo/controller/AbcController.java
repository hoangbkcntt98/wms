package com.wms.demo.controller;

import com.wms.demo.common.ResourceResponse;
import com.wms.demo.dto.request.AnnualDemandUpdateDTO;
import com.wms.demo.model.Abc;
import com.wms.demo.model.AnnualDemand;
import com.wms.demo.model.Inventory;
import com.wms.demo.services.AbcService;
import com.wms.demo.services.AnnualDemandService;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abc/")
public class AbcController {
    @Autowired
    AbcService abcService;
    @GetMapping("all")
    public ResourceResponse getAll(){
        List<Abc> abcList  = abcService.getAll();
        return new ResourceResponse(Status.SUCCESS,"Successfully!",abcList);
    }
    @PostMapping("create")
    public ResourceResponse store(@RequestBody Abc abc){
        Integer status = abcService.createAbc(abc);
        return new ResourceResponse(status,null);
    }

//    @PutMapping("update")
//    public ResourceResponse update(AnnualDemandUpdateDTO annualDemandUpdateDTO){
//        int status = annualDemandService.updateAnnualDemand(annualDemandUpdateDTO.getId(),annualDemandUpdateDTO.getData());
//        return new ResourceResponse(status,"update");
//    }
//    @DeleteMapping("delete")
//    public ResourceResponse delete(Integer id){
//        int status = annualDemandService.deleteAnnualDemand(id);
//        return new ResourceResponse(status,"delete");
//    }

}
