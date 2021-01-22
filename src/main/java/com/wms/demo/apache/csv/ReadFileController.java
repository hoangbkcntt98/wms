package com.wms.demo.apache.csv;

import com.wms.demo.common.ResourceResponse;
import com.wms.demo.services.FileService;
import com.wms.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/readcsv/")
public class ReadFileController {
    @Autowired
    FileService fileService;
    @PostMapping("")
    public ResourceResponse importABC(@RequestParam("file") MultipartFile file) {
        try {
            fileService.store(file,1);
            return new ResourceResponse(Status.SUCCESS);
        } catch (Exception e) {
            return new ResourceResponse(Status.ERROR);
        }
    }
}
