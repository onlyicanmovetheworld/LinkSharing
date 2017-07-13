package com.futureCorp.service;

import com.futureCorp.dao.AddOTPDao;
import com.futureCorp.dao.AddOTPDaoInterface;
import com.futureCorp.holder.SendEmail;
import com.futureCorp.model.OTP;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class OTPService implements OTPServiceInterface,SendEmail {

    @Autowired
    AddOTPDaoInterface otpDaoInterface;

    @Override
    public Boolean sendingOTP(String email) throws UnsupportedEncodingException {
        Random rand = new Random();

        int  n = rand.nextInt(899999) + 100001;
        OTP otp = new OTP();
        otp.setEmail(email);
        otp.setOtp(n);
        String OTP = "OTP to reset your password is "+n+"\n\n\n\nRegards LinkSharing";
        if(SendEmail.send(email,OTP))
        {
            return otpDaoInterface.addVerficationCode(otp);
        }
        else
        {
            return false;
        }
    }
}
