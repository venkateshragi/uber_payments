package com.uber.payments.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uber.payments.dto.PartnerRegistrationDto;

/**
 * Created by ragiv on 25/06/17.
 */
@RestController
@RequestMapping("/partner")
public class PartnerController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, ?> registerPartner(@RequestBody PartnerRegistrationDto partnerDto) {
        return Collections.singletonMap("success", true);
    }
}
