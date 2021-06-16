package com.jvo.biglossalertsmonitoring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockGameEvent implements BlockingEvent {

    private String individualTaxNumber;

    private String email;

    private LocalDateTime blockedTill;

    @Override
    public String getPartitionKey() {
        return individualTaxNumber;
    }
}
