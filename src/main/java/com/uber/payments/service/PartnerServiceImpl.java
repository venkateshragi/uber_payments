package com.uber.payments.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uber.payments.dto.PartnerRegistrationDto;
import com.uber.payments.entity.Partner;
import com.uber.payments.repositories.PartnerRepository;
import com.uber.payments.repositories.vo.PartnerWithoutUberId;

@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    private static Logger LOG = LoggerFactory.getLogger(PartnerServiceImpl.class);

    @Override
    public Partner createPartner(PartnerRegistrationDto partnerDto) {
        Partner partner = new Partner();
        partner.setName(partnerDto.getName());
        partner.setPhoneNumber(partnerDto.getPhoneNumber());
        partner.setAlternatePhoneNumber(partnerDto.getAlternatePhoneNumber());
        partner.setAddress(partnerDto.getAddress());
        partner.setIdType(partnerDto.getIdType());
        partner.setIdNumber(partnerDto.getIdNumber());
        partner.setVehicleNumber(partnerDto.getVehicleNumber());

        partner.setAssetName(partnerDto.getAssetName());
        partner.setAssetType(partnerDto.getAssetType());
        partner.setPurchaseAmount(partnerDto.getPurchaseAmount());
        partner.setNoOfEWI(partnerDto.getNoOfEWI());
        double remainingAmount = partnerDto.getPurchaseAmount() - partnerDto.getDownPayment();
        partner.setEwi(remainingAmount / partnerDto.getNoOfEWI());
        partner.setDownPayment(partnerDto.getDownPayment());
        partner.setAmountDue(partnerDto.getPurchaseAmount() - partnerDto.getDownPayment());

        if (partnerDto.getDatePurchased() == 0)
            partner.setDatePurchased(new Date());
        else
            partner.setDatePurchased(new Date(partnerDto.getDatePurchased()));

        return partnerRepository.save(partner);
    }

    @Override
    public List<PartnerWithoutUberId> findPartnersWithoutUberId() {
        return partnerRepository.findByUberUserIdIsNull();
    }

    @Override
    public void updateUberIds(String filePath) {
        File file = new File(filePath);
        XSSFWorkbook xssfWorkbook;
        try {
            xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            LOG.error("Exception while opening file to record payments", e);
            return;
        }
        int activeSheetIndex = xssfWorkbook.getActiveSheetIndex();
        XSSFSheet sheet = xssfWorkbook.getSheetAt(activeSheetIndex);
        Iterator<Row> rowIterator = sheet.rowIterator();

        //first row contains headings
        rowIterator.next();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String partnerId = row.getCell(3).getStringCellValue();
            String uberId = row.getCell(6).getStringCellValue();
            if(uberId != null && !uberId.trim().isEmpty()) {
                try {
                    partnerRepository.setUberIdForPartner(partnerId, uberId);
                } catch (Exception e) {
                    LOG.error("Exception while importing uber ids :", e);
                }
            }
        }
    }
}
