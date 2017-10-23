package com.uber.payments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uber.payments.repositories.vo.PartnerDebt;
import com.uber.payments.repositories.vo.PartnerWithoutUberId;
import com.uber.payments.service.PartnerService;
import com.uber.payments.service.PaymentsService;

@Controller
@RequestMapping("/download")
public class ExportController {

    @Autowired
    PaymentsService paymentsService;

    @Autowired
    PartnerService partnerService;

    /**
     * Exports a file that contains the partners next installment along with previous dues.
     * @param model
     * @return
     */
    @RequestMapping(value = "/partnerLedger", method = RequestMethod.GET,
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public String downloadPartnerLedger(Model model) {
        List<PartnerDebt> partnerCollectibles = paymentsService.getPartnerCollectibles();
        model.addAttribute("collectibles", partnerCollectibles);
        model.addAttribute("document", "collectibles");
        return "";
    }

    /**
     * Exports a file containing new registrations which are not yet assigned uberUberids.
     * @param model
     * @return
     */
    @RequestMapping(value = "/partnersWithoutUberId", method = RequestMethod.GET,
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public String downloadPartnersWithoutUberId(Model model) {
        List<PartnerWithoutUberId> partnersWithoutUberId = partnerService.findPartnersWithoutUberId();
        model.addAttribute("partners", partnersWithoutUberId);
        model.addAttribute("document", "partners_without_uber_id");
        return "";
    }
}
