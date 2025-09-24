package com.dreamjob.employer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.apache.commons.lang.StringUtils;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String email;
    private String industry;
    private String status;

    public Employer() {}

    public Employer(String companyName, String email, String status) {
        this.companyName = companyName;
        this.email = email;
        this.status = status;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void copy(Employer employer) {
        setCompanyName(employer.getCompanyName());
        setIndustry(employer.getIndustry());
        setStatus(employer.getStatus());
        setEmail(employer.getEmail());
    }

    public void update(Employer employer) {
        if(StringUtils.isNotBlank(employer.getCompanyName())) {
            setCompanyName(employer.getCompanyName());
        }
        if(StringUtils.isNotBlank(employer.getIndustry())) {
            setIndustry(employer.getIndustry());
        }
        if(StringUtils.isNotBlank(employer.getStatus())) {
            setStatus(employer.getStatus());
        }
        if(StringUtils.isNotBlank(employer.getEmail())) {
            setEmail(employer.getEmail());
        }
    }


}
