package com.uber.payments.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uber.payments.dto.PartnerRegistrationDto;
import com.uber.payments.entity.Partner;
import com.uber.payments.service.PartnerService;
import com.uber.payments.service.PaymentsService;

/**
 * Created by ragiv on 25/06/17.
 */
@RestController
@RequestMapping("/partner")
public class PartnerController {

    private static String UPLOADED_FOLDER = "/Users/ragiv/Desktop/payments_files/";

    @Autowired
    PaymentsService paymentsService;

    @Autowired
    PartnerService partnerService;

    /**
     * Registers a new purchase. Here a new purchase is a new registration.
     * @param partnerDto partner details - excluding uber id.
     * @return saved partner object
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Partner registerPartner(@RequestBody PartnerRegistrationDto partnerDto) {
        return partnerService.createPartner(partnerDto);
    }

    /**
     * This is to import uber ids of the partners registered with us.
     * @param file containing uber ids.
     * @throws IOException
     */
    @RequestMapping(value = "/importUberId", method = RequestMethod.POST)
    public void importUberId(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = saveUploadedFile(file);
        partnerService.updateUberIds(filePath);
    }

    /**
     * To process payments made by the partner ie., EWIs
     * @param file
     * @throws IOException
     */
    @RequestMapping(value = "/processPayments", method = RequestMethod.POST)
    public void recordPayments(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = saveUploadedFile(file);
        paymentsService.recordPayments(filePath);
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is not properly uploaded");
        }
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename() + (new Date()).toString());
        Files.write(path, bytes);
        return path.toString();
    }
}
