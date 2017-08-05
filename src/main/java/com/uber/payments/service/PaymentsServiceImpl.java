package com.uber.payments.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uber.payments.dto.AssetPurchasedDto;
import com.uber.payments.dto.PartnerRegistrationDto;
import com.uber.payments.entity.AssetPurchased;
import com.uber.payments.entity.Partner;
import com.uber.payments.entity.Payment;
import com.uber.payments.repositories.AssetPurchasedRepository;
import com.uber.payments.repositories.PartnerRepository;
import com.uber.payments.repositories.PaymentsRepository;

/**
 * Created by ragiv on 16/07/17.
 */
@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    AssetPurchasedRepository assetPurchasedRepository;

    @Autowired
    PaymentsRepository paymentsRepository;

    private static Logger LOG = LoggerFactory.getLogger(PaymentsServiceImpl.class);

    @Override
    public Partner createPartner(PartnerRegistrationDto partnerDto) {
        Partner partner  = new Partner();
        partner.setName(partnerDto.getName());
        partner.setUberUserId(partnerDto.getUberUserId());
        partner.setPhoneNumber(partnerDto.getPhoneNumber());
        partner.setAlternatePhoneNumber(partnerDto.getAlternatePhoneNumber());
        partner.setAddress(partnerDto.getAddress());
        partner.setIdType(partnerDto.getIdType());
        partner.setIdNumber(partnerDto.getIdNumber());
        if(partnerDto.getDateCreated() == 0)
            partner.setDateCreated(new Date());
        else
            partner.setDateCreated(new Date(partnerDto.getDateCreated()));
        return partnerRepository.save(partner);
    }

    @Override
    public AssetPurchased createAssetPurchased(AssetPurchasedDto assetPurchasedDto) {
        AssetPurchased assetPurchased = new AssetPurchased();

        Partner partner = partnerRepository.findOne(assetPurchasedDto.getPartnerId());
        assetPurchased.setPartner(partner);

        assetPurchased.setAssetName(assetPurchasedDto.getAssetName());
        assetPurchased.setAssetType(assetPurchasedDto.getAssetType());
        if(assetPurchasedDto.getDateCreated() == 0)
            assetPurchased.setDateCreated(new Date(assetPurchasedDto.getDateCreated()));
        else
            assetPurchased.setDateCreated(new Date());
        assetPurchased.setNoOfEWI(assetPurchasedDto.getNoOfEWI());
        assetPurchased.setEwi(assetPurchasedDto.getEwi());
        assetPurchased.setAmountDue(assetPurchasedDto.getPurchaseAmount() - assetPurchasedDto.getDownPayment());
        assetPurchased.setPurchaseAmount(assetPurchasedDto.getPurchaseAmount());
        return assetPurchasedRepository.save(assetPurchased);
    }

    @Override
    public List<AssetPurchasedRepository.PartnerCollectibles> getPartnerCollectibles() {
        List<AssetPurchasedRepository.PartnerCollectibles> partnerCollectibles = assetPurchasedRepository.getPartnerCollectibles();
        return partnerCollectibles;
    }

    @Override
    public void recordPayments(String filePath) {
        File file = new File(filePath);
        XSSFWorkbook xssfWorkbook;
        try {
            xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            LOG.error("Exception while recording payments", e);
            return;
        }
        int activeSheetIndex = xssfWorkbook.getActiveSheetIndex();
        XSSFSheet sheet = xssfWorkbook.getSheetAt(activeSheetIndex);
        Iterator<Row> rowIterator = sheet.rowIterator();

        //first row contains headings
        rowIterator.next();

        List<Payment> payments = new ArrayList<>();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String uberUserId = row.getCell(0).getStringCellValue();
            String partnerId = row.getCell(1).getStringCellValue();
            int numberOfAssets = ((Double)row.getCell(2).getNumericCellValue()).intValue();
            double amountToBeCharged = row.getCell(3).getNumericCellValue();
            double amountCharged = row.getCell(4).getNumericCellValue();
            double shortfall = row.getCell(5).getNumericCellValue();

            System.out.println(uberUserId + " " + partnerId + " " + amountToBeCharged + " " + amountCharged + " " + shortfall);
            Payment payment = new Payment();
            payment.setPartner(partnerRepository.findOne(partnerId));
            payment.setUberUserId(uberUserId);
            payment.setNumberOfAssets(numberOfAssets);
            payment.setAmountToBeCharged(amountToBeCharged);
            payment.setAmountPaid(amountCharged);
            payment.setShortfall(shortfall);

            payments.add(payment);
        }
        paymentsRepository.save(payments);
        Thread thread = new Thread(() -> updateAmountDue(payments));
        thread.start();
    }

    private void updateAmountDue(List<Payment> payments) {
        for(Payment payment : payments) {
            String partnerId = payment.getPartner().getId();
            List<AssetPurchased> assetPurchasedList = assetPurchasedRepository.findByPartner_IdOrderByDateCreatedAsc(partnerId);
            double amountCharged = payment.getAmountPaid();
            for(AssetPurchased assetPurchased : assetPurchasedList) {
                double amountDue = assetPurchased.getAmountDue();
                double ewi = assetPurchased.getEwi();
                if(amountCharged >= ewi) {
                    assetPurchased.setAmountDue(amountDue - ewi);
                    amountCharged = amountCharged - ewi;
                } else {
                    assetPurchased.setAmountDue(amountDue - amountCharged);
                    amountCharged = 0;
                }
            }
            try {
                assetPurchasedRepository.save(assetPurchasedList);
            } catch (Exception e) {
                LOG.error("Error while updating payments for partner : " + partnerId, e);
                //TODO send a mail about this failure
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new PaymentsServiceImpl().recordPayments(args[0]);
    }


}
