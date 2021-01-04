package com.wms.demo.util;

import com.wms.demo.model.Abc;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    public static ByteArrayInputStream customersToExcel(List<Abc> abcList) throws IOException {
        String[] COLUMNs = {"id", "name", "range_abc"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Customers");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (Abc abc : abcList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(abc.getId());
                row.createCell(1).setCellValue(abc.getName());
                row.createCell(2).setCellValue(abc.getRange());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static List<Abc> parseExcelFile(InputStream is) {

        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet("ABC");
            Iterator<Row> rows = sheet.iterator();

            List<Abc> abcList = new ArrayList<Abc>();
            int rowNumber = 0;
            while (rows.hasNext()) {

                Row currentRow = rows.next();

                // skip header
                if(rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Abc abc = new Abc();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if(cellIndex==0) { // ID
                        int id = (int) currentCell.getNumericCellValue();
                        abc.setId(id);
                        System.out.println(id);
                    } else if(cellIndex==1) { // Name
                        abc.setName(currentCell.getStringCellValue());
                    } else if(cellIndex==2) { // Address
                        abc.setRange((int) currentCell.getNumericCellValue());
                    }
                    cellIndex++;
                }

                abcList.add(abc);
            }

            // Close WorkBook
            workbook.close();

            return abcList;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }
}
