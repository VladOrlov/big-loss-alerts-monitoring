package com.jvo.biglossalertsmonitoring.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "individuals")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Individual {

    @Id
    private String individualTaxNumber;

    @Indexed
    private String passport;

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    @Indexed
    private Integer age;

    @Indexed
    private String gender;

    @Indexed
    private String phone;

    @Indexed
    private String email;

    @Version
    private Long version;

    @CreatedDate
    private LocalDateTime dateCreated;

    @LastModifiedDate
    private LocalDateTime dateUpdated;

}
