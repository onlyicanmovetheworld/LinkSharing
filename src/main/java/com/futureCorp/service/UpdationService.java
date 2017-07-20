package com.futureCorp.service;

import com.futureCorp.dao.UpdateDataDaoInterface;
import com.futureCorp.model.OTP;
import com.futureCorp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class UpdationService implements UpdationServiceInterface {

    @Autowired
    UpdateDataDaoInterface updateDataDaoInterface;
    @Override
    public Boolean updatingPassword(OTP otp, String password) {
        return updateDataDaoInterface.updatePassword(otp,password);
    }

    @Override
    public Boolean updatingPassword(String oldPassword, String newPassword,String email) {
        return updateDataDaoInterface.updatePassword(oldPassword,newPassword,email);
    }

    @Override
    public Boolean updatingDetails(User user) {
        return updateDataDaoInterface.updateDetails(user);
    }

    @Override
    public Boolean updatingPhoto(MultipartFile file, String email) {
        return updateDataDaoInterface.updatePhoto(file,email);
    }
}
