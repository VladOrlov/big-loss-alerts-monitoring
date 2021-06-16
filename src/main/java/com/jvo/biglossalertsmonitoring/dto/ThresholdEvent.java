package com.jvo.biglossalertsmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThresholdEvent {

    private String id;

    private String individualTaxNumber;

    private BigDecimal amount;

    private String fromDateTime;

    private String toDateTime;

}