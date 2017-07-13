package com.futureCorp.service;

import com.futureCorp.dao.UpdateDataDaoInterface;
import com.futureCorp.model.OTP;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdationService implements UpdationServiceInterface {

    @Autowired
    UpdateDataDaoInterface updateDataDaoInterface;
    @Override
    public Boolean updatingPassword(OTP otp, String password) {
        return updateDataDaoInterface.updatePassword(otp,password);
    }
}
