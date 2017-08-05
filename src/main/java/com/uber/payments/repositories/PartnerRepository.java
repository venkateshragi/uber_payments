package com.uber.payments.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.uber.payments.entity.Partner;

/**
 * Created by ragiv on 16/07/17.
 */
@Component
public interface PartnerRepository extends CrudRepository<Partner, String> {

}
