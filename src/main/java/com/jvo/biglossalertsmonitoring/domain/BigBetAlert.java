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

@Data
@Builder(builderMethodName = "of", buildMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "big_loss_alerts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BigBetAlert {

    @Id
    private String id;

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

    @Indexed(name = "customer_id_index")
    private String customerId;

}
