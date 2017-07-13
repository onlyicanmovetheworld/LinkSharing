package com.futureCorp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OTP {


    private String email;
    @Id
    private Integer otp;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
}
