package com.futureCorp.service;

import com.futureCorp.model.OTP;

public interface UpdationServiceInterface {

    default Boolean updatingPassword(OTP otp,String password)
    {
        return null;
    }

}
