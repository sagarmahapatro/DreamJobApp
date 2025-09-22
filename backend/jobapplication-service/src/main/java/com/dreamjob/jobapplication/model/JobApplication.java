package com.dreamjob.jobapplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class JobApplication {
    @Id
    private Long id;
    private String companyName;
    private String email;
    private String industry;

    public void setId(Long applicationId) {
    }

    public void setStatus(String status) {
    }
}
