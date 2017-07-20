package com.futureCorp.service;

import com.futureCorp.model.OTP;
import com.futureCorp.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UpdationServiceInterface {

    default Boolean updatingPassword(OTP otp,String password)
    {
        return null;
    }

    default Boolean updatingPassword(String oldPassword,String newPassword,String email)
    {
        return null;
    }
    default Boolean updatingDetails(User user)
    {
        return null;
    }
    default Boolean updatingPhoto(MultipartFile file , String email)
    {
        return null;
    }


}
