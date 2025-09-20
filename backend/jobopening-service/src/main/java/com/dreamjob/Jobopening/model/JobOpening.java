package com.dreamjob.Jobopening.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobopening")
public class JobOpening {
    @Id
    private Long id;
    private String companyName;
    private String email;
    private String industry;
}
