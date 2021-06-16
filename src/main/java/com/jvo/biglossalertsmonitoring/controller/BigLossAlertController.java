package com.jvo.biglossalertsmonitoring.controller;

import com.jvo.biglossalertsmonitoring.domain.BigLossAlert;
import com.jvo.biglossalertsmonitoring.repository.BigLossAlertsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/big-loss-alerts")
public class BigLossAlertController {

    private final BigLossAlertsRepository bigLossAlertsRepository;

    public BigLossAlertController(BigLossAlertsRepository bigLossAlertsRepository) {
        this.bigLossAlertsRepository = bigLossAlertsRepository;
    }

    @GetMapping
    public ResponseEntity<List<BigLossAlert>> getBigLossAlerts() {
        List<BigLossAlert> bigLossAlerts = bigLossAlertsRepository.findAll();

        return ResponseEntity.ok(bigLossAlerts);
    }
}
