package com.futureCorp.dao;

import com.futureCorp.model.OTP;
import com.futureCorp.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateDataDaoInterface {

    default Boolean updatePassword(OTP otp, String password)
    {
        return null;
    }

    default Boolean updatePassword(String oldPassword,String newPassword,String email)
    {
        return null;
    }

    default Boolean updateDetails(User user)
    {
        return null;
    }

    default Boolean updatePhoto(MultipartFile file,String email)
    {
        return null;
    }
}
