package com.futureCorp.service;


import com.futureCorp.dao.RegisterDaoInterface;
import com.futureCorp.model.User;
import org.springframework.beans.factory.annotation.Autowired;


public class RegistrationService implements RegistrationServiceInterface {

    @Autowired
    RegisterDaoInterface registerDaoInterface;

    @Override
    public String registering(User user) {


        if(registerDaoInterface.saveRegisteredUser(user))
        {
            return "home";
        }
        else
        {
            return "error";
        }



    }
}
