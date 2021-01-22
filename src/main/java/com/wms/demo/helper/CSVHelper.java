package com.wms.demo.helper;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.wms.demo.model.Abc;
import com.wms.demo.model.Inventory;
import com.wms.demo.model.Risk;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
//    public static int ABC_TYPE = 1;
//    static String [] ABC_HEADER = {"id","name","range_abc"};
    public boolean hasCSVFormat(MultipartFile file){
        if(TYPE.equals(file.getContentType())) return true;
        return false;
    }
    public static List<Abc> csvToAbc(MultipartFile file){
        try{
            Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();
            String [] buff;
            List<Abc> abcList = new ArrayList<Abc>();
            while((buff= csvReader.readNext())!=null){
                int id = Integer.parseInt(buff[0]);
                String name = buff[1];
                int range = Integer.parseInt(buff[2]);
                abcList.add(new Abc(id,name,range));
            }
            return abcList;
        }catch (Exception e){
            return null;
        }
    }
    public static List<Inventory> csvToInventory(MultipartFile file){
        try{
            Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();
            String [] buff;
            List<Inventory> inventoryList = new ArrayList<Inventory>();
            while((buff= csvReader.readNext())!=null){
                int id = Integer.parseInt(buff[0]);
                String name = buff[1];
                String sku = buff[2];
                LocalDateTime exp = LocalDateTime.parse(buff[3]);
                int type = Integer.parseInt(buff[4]);
                int status = Integer.parseInt(buff[5]);
                double quantities = Double.parseDouble(buff[6]);
                String className = buff[7];
                double price = Double.parseDouble(buff[8]);
                inventoryList.add(new Inventory(id,name,sku,exp,type,status,quantities,className,price));

            }
            return inventoryList;
        }catch (Exception e){
            return null;
        }
    }
    public static List<Risk> csvToRiskRelation(MultipartFile file){
        try {
            Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();
            String [] buff;
            List<Risk> riskRelationList= new ArrayList<Risk>();
            while((buff= csvReader.readNext())!=null){
                int riskId = Integer.parseInt(buff[0]);
                String parents = buff[1];
                Risk risk = new Risk();
                risk.setRiskId(riskId);
                risk.setParents(parents);
                riskRelationList.add(risk);

            }
            return riskRelationList;

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }



}
