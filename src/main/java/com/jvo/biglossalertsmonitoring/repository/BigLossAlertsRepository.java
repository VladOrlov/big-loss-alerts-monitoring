package com.jvo.biglossalertsmonitoring.repository;


import com.jvo.biglossalertsmonitoring.domain.BigLossAlert;
import com.jvo.biglossalertsmonitoring.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigLossAlertsRepository extends MongoRepository<BigLossAlert, String> {


}
