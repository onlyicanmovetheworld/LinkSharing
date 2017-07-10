package com.futureCorp.service;


import com.futureCorp.dao.RegisterUserInterface;
import com.futureCorp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class Registration implements RegistrationInterface {

    @Autowired
    RegisterUserInterface registerUserInterface;

    @Override
    public String registering(User user) {


        if(registerUserInterface.saveRegisteredUser(user))
        {
            return "home";
        }
        else
        {
            return "error";
        }



    }
}
