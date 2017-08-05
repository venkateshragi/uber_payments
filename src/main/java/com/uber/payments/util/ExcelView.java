package com.uber.payments.util;

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
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.uber.payments.repositories.AssetPurchasedRepository;

/**
 * Created by ragiv on 30/07/17.
 */
public class ExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"payments.xls\"" + new Date().toString());

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Partner ID");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Uber User ID");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Number of Assets");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Amount to be Paid");
        header.getCell(3).setCellStyle(style);

        List<AssetPurchasedRepository.PartnerCollectibles> collectibles = (List<AssetPurchasedRepository.PartnerCollectibles>) model.get("collectibles");

        int rowCount = 1;
        for(AssetPurchasedRepository.PartnerCollectibles collectible : collectibles) {
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(collectible.getPartnerId());
            userRow.createCell(1).setCellValue(collectible.getUberUserId());
            userRow.createCell(2).setCellValue(collectible.getNumberOfAssets());
            userRow.createCell(3).setCellValue(collectible.getAmountToBeCollected());
        }

    }
}
