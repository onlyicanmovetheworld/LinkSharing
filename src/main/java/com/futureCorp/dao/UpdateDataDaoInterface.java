package com.futureCorp.dao;

import com.futureCorp.model.OTP;

public interface UpdateDataDaoInterface {

    default Boolean updatePassword(OTP otp, String password)
    {
        return null;
    }

}
