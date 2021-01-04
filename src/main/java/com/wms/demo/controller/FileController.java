package com.wms.demo.controller;

import com.wms.demo.common.ResourceResponse;
import com.wms.demo.services.FileService;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file/")
public class FileController {
    @Autowired
    FileService fileService;
    @PostMapping("upload/abc")
    public ResourceResponse importABC(@RequestParam("file") MultipartFile file) {
        try {
            fileService.store(file,1);
            return new ResourceResponse(Status.SUCCESS);
        } catch (Exception e) {
            return new ResourceResponse(Status.ERROR);
        }
    }
    @PostMapping("upload/inventorys")
    public ResourceResponse importInventory(@RequestParam("file") MultipartFile file) {
        try {
            fileService.store(file,2);
            return new ResourceResponse(Status.SUCCESS);
        } catch (Exception e) {
            return new ResourceResponse(Status.ERROR);
        }
    }
}
