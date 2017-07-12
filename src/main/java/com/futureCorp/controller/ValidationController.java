package com.futureCorp.controller;


import com.futureCorp.service.ValidatorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ValidationController {

     @Autowired
    ValidatorServiceInterface validatorServiceInterface;

    @RequestMapping("/validateUsername")

    public  @ResponseBody String validateCredentials(@RequestParam("credential")String credential)
    {
       if(validatorServiceInterface.validationOfUser(credential))
        {
            return "true";
        }
        else
        {
            return "false";
        }

    }

    @RequestMapping("/validateTopicName")

    public  @ResponseBody String validateTopicName(@RequestParam("topicName")String topicName, HttpServletRequest request)
    {
        if(validatorServiceInterface.validationOfTopic(request.getSession().getAttribute("username").toString(),topicName))
        {
            return "true";
        }
        else
        {
            return "false";
        }

    }


}
