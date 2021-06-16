package com.jvo.biglossalertsmonitoring.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(builderMethodName = "of", buildMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "individual_big_loss_alerts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BigLossAlert {

    @Id
    private String id;

    @Indexed(name = "individual_tax_number_index")
    private String individualTaxNumber;

    @Indexed(name = "from_date_index")
    private String fromDateTime;

    @Indexed(name = "to_date_index")
    private String toDateTime;

    @Indexed(name = "amount_index")
    private BigDecimal amount;

    @Indexed(name = "first_name_index")
    private String firstName;

    @Indexed(name = "last_name_index")
    private String lastName;

    private String phone;

    private Integer age;

    @Indexed(name = "email_index")
    private String email;

    private String gender;

}
