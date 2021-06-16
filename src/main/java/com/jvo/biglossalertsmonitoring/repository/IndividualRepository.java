package com.jvo.biglossalertsmonitoring.repository;


import com.jvo.biglossalertsmonitoring.domain.Individual;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualRepository extends MongoRepository<Individual, String> {

    Optional<Individual> findPersonByIndividualTaxNumber(String individualTaxNumber);

}
