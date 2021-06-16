package com.jvo.biglossalertsmonitoring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvo.biglossalertsmonitoring.domain.BigLossAlert;
import com.jvo.biglossalertsmonitoring.domain.BlockGameEvent;
import com.jvo.biglossalertsmonitoring.domain.Individual;
import com.jvo.biglossalertsmonitoring.dto.ThresholdEvent;
import com.jvo.biglossalertsmonitoring.repository.BigLossAlertsRepository;
import com.jvo.biglossalertsmonitoring.repository.CustomerRepository;
import com.jvo.biglossalertsmonitoring.repository.IndividualRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
@Service
public class BigLossEventsConsumer {

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;
    private final IndividualRepository individualRepository;
    private final BigLossAlertsRepository bigLossAlertsRepository;
    private final BlockingEventsPublisher blockingEventsPublisher;

    public BigLossEventsConsumer(ObjectMapper objectMapper,
                                 CustomerRepository customerRepository,
                                 IndividualRepository individualRepository,
                                 BigLossAlertsRepository bigLossAlertsRepository, BlockingEventsPublisher blockingEventsPublisher) {
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
        this.individualRepository = individualRepository;
        this.bigLossAlertsRepository = bigLossAlertsRepository;
        this.blockingEventsPublisher = blockingEventsPublisher;
    }

    @KafkaListener(topics = "threshold-events")
    public void consumeMessage(String eventJson) {
        try {
            ThresholdEvent thresholdEvent = objectMapper.readValue(eventJson, ThresholdEvent.class);

            individualRepository.findPersonByIndividualTaxNumber(thresholdEvent.getIndividualTaxNumber())
                    .flatMap(individual -> customerRepository.findCustomerByIndividualTaxNumber(thresholdEvent.getIndividualTaxNumber())
                                    .map(customer -> getBigLossAlert(thresholdEvent, individual, customer)))
                    .ifPresent(this::saveAlertAndPublishBlockingEvent);

        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
        }
    }

    private void saveAlertAndPublishBlockingEvent(BigLossAlert bigLossAlert){
        bigLossAlertsRepository.save(bigLossAlert);

        BlockGameEvent event = new BlockGameEvent();
        event.setIndividualTaxNumber(bigLossAlert.getIndividualTaxNumber());
        event.setEmail(bigLossAlert.getEmail());
        event.setBlockedTill(LocalDateTime.now().plusHours(24));
        blockingEventsPublisher.sendBlockingEvents(event);
    }

    private BigLossAlert getBigLossAlert(ThresholdEvent thresholdEvent,
                                         Individual individual,
                                         com.jvo.biglossalertsmonitoring.domain.Customer customer) {

        return BigLossAlert.of()
                .id(thresholdEvent.getId())
                .fromDateTime(thresholdEvent.getFromDateTime())
                .toDateTime(thresholdEvent.getToDateTime())
                .amount(thresholdEvent.getAmount().abs())
                .individualTaxNumber(thresholdEvent.getIndividualTaxNumber())
                .firstName(individual.getFirstName())
                .lastName(individual.getLastName())
                .phone(customer.getPhone())
                .gender(individual.getGender())
                .age(individual.getAge())
                .email(customer.getEmail())
                .create();
    }

}
