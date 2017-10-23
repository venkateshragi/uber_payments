package com.uber.payments.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uber.payments.entity.Partner;
import com.uber.payments.entity.Payment;
import com.uber.payments.repositories.PartnerRepository;
import com.uber.payments.repositories.PaymentsRepository;
import com.uber.payments.repositories.vo.PartnerDebt;
import com.uber.payments.repositories.vo.PartnerPaymentInfo;

/**
 * Created by ragiv on 16/07/17.
 */
@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    PaymentsRepository paymentsRepository;

    private static Logger LOG = LoggerFactory.getLogger(PaymentsServiceImpl.class);

    @Override
    public List<PartnerDebt> getPartnerCollectibles() {

        List<PartnerDebt> partnerDebts = partnerRepository.findByAmountDueGreaterThan(0);
        List<String> partnerIds = partnerDebts.parallelStream().map(PartnerDebt::getPartnerId).collect(Collectors.toList());

        List<PartnerPaymentInfo> previousShortfalls = paymentsRepository.findPreviousShortfall(partnerIds);
        Map<String, PartnerPaymentInfo> partnerPaymentInfoMap = previousShortfalls.parallelStream()
                .collect(Collectors.toMap(PartnerPaymentInfo::getPartnerId, Function.identity()));

        for(PartnerDebt partnerDebt : partnerDebts) {
            PartnerPaymentInfo partnerPaymentInfo = partnerPaymentInfoMap.get(partnerDebt.getPartnerId());
            if(partnerPaymentInfo == null) {
                //We are generating the collectibles first time for this partner. Sending for first EWI
                continue;
            }
            if(partnerPaymentInfo.getTotalPayments() < partnerDebt.noOfEWI) {
                partnerDebt.amountToBeCollected = partnerDebt.amountToBeCollected + partnerPaymentInfo.getShortfall();
            } else {
                partnerDebt.isNPA = true;
                partnerDebt.amountToBeCollected = partnerPaymentInfo.getShortfall();
            }
        }

        return partnerDebts;
    }

    @Override
    public void recordPayments(String filePath) {
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

        List<Payment> payments = new ArrayList<>();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String uberUserId = row.getCell(0).getStringCellValue();
            String partnerId = row.getCell(1).getStringCellValue();
            double amountToBeCharged = row.getCell(2).getNumericCellValue();
            double amountCharged = row.getCell(3).getNumericCellValue();
            double shortfall = row.getCell(4).getNumericCellValue();

            System.out.println(uberUserId + " " + partnerId + " " + amountToBeCharged + " " + amountCharged + " " + shortfall);
            Payment payment = new Payment();
            payment.setPartner(partnerRepository.findOne(partnerId));
            payment.setUberUserId(uberUserId);
            payment.setAmountToBeCharged(amountToBeCharged);
            payment.setAmountPaid(amountCharged);
            payment.setShortfall(shortfall);

            payments.add(payment);
        }
        Iterable<Payment> paymentsIterable = paymentsRepository.save(payments);
        Thread thread = new Thread(() -> updateAmountDue(paymentsIterable));
        thread.start();
    }

    private void updateAmountDue(Iterable<Payment> payments) {
        List<Partner> partners = new ArrayList<>(10);
        Iterator<Payment> iterator = payments.iterator();
        while (iterator.hasNext()) {
            Payment payment = iterator.next();
            Partner partner = payment.getPartner();
            double amountCharged = payment.getAmountPaid();
            double amountDue = partner.getAmountDue();
            partner.setAmountDue(amountDue - amountCharged);
            partners.add(partner);
            if(partners.size() == 10 || !iterator.hasNext()) {
                partnerRepository.save(partners);
                partners.clear();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PaymentsServiceImpl().recordPayments(args[0]);
    }

}
