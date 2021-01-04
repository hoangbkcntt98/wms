package com.wms.demo.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.wms.demo.helper.CSVHelper;
import com.wms.demo.model.Abc;
import com.wms.demo.model.Inventory;
import com.wms.demo.repository.AbcRepository;
import com.wms.demo.repository.InventoryRepository;
import com.wms.demo.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService{
    public static int FILE_ABC = 1;
    public static int FILE_INVENTORY =2;
    @Autowired
    AbcRepository abcRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    public void store(MultipartFile file,int type){
        try {
            if(type == FILE_ABC){
                List<Abc> abcList = CSVHelper.csvToAbc(file);
                abcRepository.saveAll(abcList);
            }else if(type == FILE_INVENTORY){
                List<Inventory> inventoryList = CSVHelper.csvToInventory(file);
                inventoryRepository.saveAll(inventoryList);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    // Load Data to Excel File
    public ByteArrayInputStream loadFile() {
        List<Abc> customers = (List<Abc>) abcRepository.findAll();

        try {
            ByteArrayInputStream in = ExcelUtils.customersToExcel(customers);
            return in;
        } catch (IOException e) {}

        return null;
    }
}

