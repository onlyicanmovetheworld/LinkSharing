package com.futureCorp.service;


import com.futureCorp.dao.RegisterDaoInterface;
import com.futureCorp.holder.HttpSessionSetter;
import com.futureCorp.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


public class RegistrationService implements RegistrationServiceInterface,HttpSessionSetter {

    @Autowired
    RegisterDaoInterface registerDaoInterface;

    @Override
    public String registering(User user, HttpServletRequest request) {


        if(registerDaoInterface.saveRegisteredUser(user))
        {
            setSessionAttribute("username",user.getUsername(),request.getSession());
            return "dashboard";
        }
        else
        {
            return "error";
        }



    }
}
