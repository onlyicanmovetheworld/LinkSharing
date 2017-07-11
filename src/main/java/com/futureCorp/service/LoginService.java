package com.futureCorp.service;

import com.futureCorp.dao.LoginDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginService implements LoginServiceInterface{

    @Autowired
    LoginDaoInterface loginDaoInterface;

    @Override
    public String loginUser(String credentials,String password) {
        Boolean fetched;
        if(credentials.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
        {
            fetched=loginDaoInterface.validateUserViaEmail(credentials,password);
        }
        else
        {
            fetched=loginDaoInterface.validateUserViaUsername(credentials,password);
        }
        if(fetched)
        {
            return "log";
        }
        else
        {
            return "notlog";
        }

    }
}
