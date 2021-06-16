package com.jvo.biglossalertsmonitoring.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "provider_customers")
@CompoundIndex(def = "{'gameProvider':1, 'email':1}", name = "unique_provider_customer_index")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    @Id
    private String id;

    @Indexed(name = "game_provider_index")
    private String gameProvider;

    @Indexed(name = "customer_game_provider_id_index")
    private String externalId;

    @Indexed(name = "individual_tax_number_index")
    private String individualTaxNumber;

    @Indexed(name = "customer_email_index")
    private String email;

    @Indexed(name = "customer_phone_index")
    private String phone;

    @Version
    private Long version;

    @CreatedDate
    private LocalDateTime dateCreated;

    @LastModifiedDate
    private LocalDateTime dateUpdated;

}

