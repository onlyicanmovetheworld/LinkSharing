package com.futureCorp.service;

import com.futureCorp.dao.LoginDaoInterface;

import com.futureCorp.holder.HttpSessionSetter;
import com.futureCorp.holder.NullChecker;
import com.futureCorp.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class LoginService implements LoginServiceInterface,NullChecker,HttpSessionSetter{


    @Autowired
    LoginDaoInterface loginDaoInterface;

    @Override
    public String loginUser(String credentials, String password, HttpServletRequest request) {
        User fetchedUser;


            fetchedUser=loginDaoInterface.validateUser(credentials,password);

        if(!nullCheck(fetchedUser)&& fetchedUser.getActive())
        {
            request.getSession().setAttribute("username",fetchedUser.getUsername());
            request.getSession().setAttribute("user",fetchedUser);

                return "dashboard";

        }
        else
        {
            return "notlog";
        }

    }
}
