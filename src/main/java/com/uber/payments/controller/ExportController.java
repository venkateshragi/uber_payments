package com.uber.payments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uber.payments.repositories.PartnerDebt;
import com.uber.payments.repositories.PartnerWithoutUberId;
import com.uber.payments.service.PartnerService;
import com.uber.payments.service.PaymentsService;

@Controller
public class ExportController {

    @Autowired
    PaymentsService paymentsService;

    @Autowired
    PartnerService partnerService;

    @RequestMapping(value = "/downloadPartnerLedger", method = RequestMethod.GET,
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public String downloadPartnerLedger(Model model) {
        List<PartnerDebt> partnerCollectibles = paymentsService.getPartnerCollectibles();
        model.addAttribute("collectibles", partnerCollectibles);
        model.addAttribute("document", "collectibles");
        return "";
    }

    @RequestMapping(value = "/downloadPartnersWithoutUberId", method = RequestMethod.GET,
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public String exportPartnersWithoutUberId(Model model) {
        List<PartnerWithoutUberId> partnersWithoutUberId = partnerService.findPartnersWithoutUberId();
        model.addAttribute("partners", partnersWithoutUberId);
        model.addAttribute("document", "partners_without_uber_id");
        return "";
    }
}
