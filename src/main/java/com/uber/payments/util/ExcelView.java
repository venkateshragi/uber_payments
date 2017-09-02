package com.uber.payments.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.uber.payments.repositories.PartnerDebt;
import com.uber.payments.repositories.PartnerWithoutUberId;

/**
 * Created by ragiv on 30/07/17.
 */
public class ExcelView extends AbstractXlsxView {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        String document = (String) model.get("document");
        switch (document) {
            case "partners_without_uber_id" :
                buildPartnersToFetchUberUserId(model, workbook, request, response);
                break;
            case "collectibles" :
                buildCollectiblesDocumentg(model, workbook, request, response);
                break;
        }
    }

    private void buildPartnersToFetchUberUserId(Map<String, Object> model, Workbook workbook,
                                                HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=\"partners_without_uber_id" + dateFormatter.format(new Date()) + ".xlsx");
        String[] columns = {"Name", "Mobile Numbers", "Vehicle Numbers", "Partner Id"};
        Sheet sheet = createSheetAndHeaderRow(workbook, "Partners", columns);
        int rowCount = 1;

        List<PartnerWithoutUberId> partners = (List<PartnerWithoutUberId>) model.get("partners");
        for(PartnerWithoutUberId partner : partners) {
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(partner.getName());
            userRow.createCell(1).setCellValue(partner.getTelephoneNumbers());
            userRow.createCell(2).setCellValue(partner.getVehicleNumber());
            userRow.createCell(3).setCellValue(partner.getPartnerId());
        }
    }

    private void buildCollectiblesDocumentg(Map<String, Object> model, Workbook workbook,
                                            HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=\"collectibles" + dateFormatter.format(new Date()) + ".xlsx");
        String[] columns = {"Uber User ID", "Lender Contract ID", "Amount to be Paid"};
        Sheet sheet = createSheetAndHeaderRow(workbook, "Collectibles", columns);
        int rowCount = 1;

        List<PartnerDebt> collectibles = (List<PartnerDebt>) model.get("collectibles");
        for(PartnerDebt collectible : collectibles) {
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(collectible.getUberUserId());
            userRow.createCell(1).setCellValue(collectible.getPartnerId());
            userRow.createCell(2).setCellValue(collectible.getAmountToBeCollected());
        }
    }

    private Sheet createSheetAndHeaderRow(Workbook workbook, String partners, String[] columns) {
        // create excel xls sheet
        Sheet sheet = workbook.createSheet(partners);
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = getHeaderStyle(workbook);

        // create header row
        Row header = sheet.createRow(0);
        setNameAndStyle(header, style, columns);
        return sheet;
    }

    private CellStyle getHeaderStyle(Workbook workbook) {
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        style.setFont(font);
        return style;
    }

    private void setNameAndStyle(Row header, CellStyle style, String[] columns) {
        for(int i = 0; i < columns.length; i++) {
            String column = columns[i];
            header.createCell(i).setCellValue(column);
            header.getCell(i).setCellStyle(style);
        }
    }
}
