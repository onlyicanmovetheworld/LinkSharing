package com.futureCorp.controller;

import com.futureCorp.model.OTP;
import com.futureCorp.service.UpdationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class UpdationController {

    @Autowired
    UpdationServiceInterface updationServiceInterface;

    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public  @ResponseBody
    String updateForgottenPassword(@ModelAttribute OTP otp, @RequestParam("password")String password) throws UnsupportedEncodingException {
        if(updationServiceInterface.updatingPassword(otp,password))
        {
            return true+"";
        }
        else
        {
            return false+"";
        }

    }
}
