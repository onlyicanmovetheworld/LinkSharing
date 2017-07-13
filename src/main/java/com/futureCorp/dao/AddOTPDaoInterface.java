package com.futureCorp.dao;

import com.futureCorp.model.OTP;

public interface AddOTPDaoInterface {

    default Boolean addVerficationCode(OTP otp)
    {
        return false;
    }

}
