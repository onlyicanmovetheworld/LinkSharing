package com.futureCorp.controller;

import com.futureCorp.model.Topic;
import com.futureCorp.service.OTPServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class EmailServiceController {

    @Autowired
    OTPServiceInterface otpServiceInterface;

    @RequestMapping(value = "/sendOTP",method = RequestMethod.POST)
    public  @ResponseBody
    String sendOTP(@RequestParam("email")String email) throws UnsupportedEncodingException {
        if(otpServiceInterface.sendingOTP(email))
        {
            return true+"";
        }
        else
        {
            return false+"";
        }

    }


}


