package com.wms.demo.controller;

import com.wms.demo.common.ResourceResponse;
import com.wms.demo.dto.request.AnnualDemandUpdateDTO;
import com.wms.demo.model.AnnualDemand;
import com.wms.demo.services.AnnualDemandService;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annual_demand/")
public class AnnualDemandController {
    @Autowired
    AnnualDemandService annualDemandService;
    @GetMapping("all")
    public ResourceResponse getAll(){
        List<AnnualDemand> annualDemandList = annualDemandService.getAll();
        return new ResourceResponse(Status.SUCCESS,"Successfully!",annualDemandList);
    }
    @PostMapping("create")
    public ResourceResponse create(AnnualDemand annualDemand){
        int status = annualDemandService.createAnnualDemand(annualDemand);
        return new ResourceResponse(status,"create");
    }
    @PutMapping("update")
    public ResourceResponse update(AnnualDemandUpdateDTO annualDemandUpdateDTO){
        int status = annualDemandService.updateAnnualDemand(annualDemandUpdateDTO.getId(),annualDemandUpdateDTO.getData());
        return new ResourceResponse(status,"update");
    }
    @DeleteMapping("delete")
    public ResourceResponse delete(Integer id){
        int status = annualDemandService.deleteAnnualDemand(id);
        return new ResourceResponse(status,"delete");
    }

}
