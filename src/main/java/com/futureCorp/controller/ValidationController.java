package com.futureCorp.controller;

import com.futureCorp.model.User;
import com.futureCorp.service.ValidatorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ValidationController {

     @Autowired
    ValidatorServiceInterface validatorServiceInterface;

    @RequestMapping("/validateUsername")

    public  @ResponseBody String showHome(@RequestParam("credential")String credential)
    {
        if(validatorServiceInterface.validation(credential))
        {
            return "true";
        }
        else
        {
            return "false";
        }
    }

}
