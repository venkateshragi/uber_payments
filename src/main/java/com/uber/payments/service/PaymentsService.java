package com.uber.payments.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by ragiv on 25/06/17.
 */
public class PaymentsService {

    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
        int activeSheetIndex = xssfWorkbook.getActiveSheetIndex();
        XSSFSheet sheet = xssfWorkbook.getSheetAt(activeSheetIndex);
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            String userUUID = row.getCell(0).getStringCellValue();
            String lenderContractID = row.getCell(1).getStringCellValue();
            double amountToBeCharged = row.getCell(2).getNumericCellValue();
            double amountCharged = row.getCell(3).getNumericCellValue();
            double shortfall = row.getCell(4).getNumericCellValue();

            System.out.println(userUUID + " " + lenderContractID + " " + amountToBeCharged + " " + amountCharged + " " + shortfall);

            /*while(cellIterator.hasNext()) {

            }*/
        }


    }
}
