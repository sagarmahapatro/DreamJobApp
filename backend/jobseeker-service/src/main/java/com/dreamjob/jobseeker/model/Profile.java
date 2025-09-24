package com.dreamjob.jobseeker.model;

import jakarta.persistence.Embeddable;
import org.apache.commons.lang.StringUtils;

@Embeddable
public class Profile {
    private String fullName;
    private String email;
    private String phone;
    private String address;

    public Profile() {}

    public Profile(String fullName, String email, String phone, String address) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters & Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void update(Profile profile) {
        if(StringUtils.isNotBlank(profile.getFullName())) {
            setFullName(profile.getFullName());
        }
        if(StringUtils.isNotBlank(profile.getEmail())) {
            setEmail(profile.getEmail());
        }
        if(StringUtils.isNotBlank(profile.getPhone())) {
            setPhone(profile.getPhone());
        }
        if(StringUtils.isNotBlank(profile.getAddress())) {
            setAddress(profile.getAddress());
        }
    }
}
