package com.futureCorp.service;

import java.io.UnsupportedEncodingException;

public interface OTPServiceInterface {

    default Boolean sendingOTP(String email) throws UnsupportedEncodingException {
        return false;
    }

}
