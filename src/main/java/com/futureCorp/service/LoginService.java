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

        if(credentials.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
        {
            fetchedUser=loginDaoInterface.validateUserViaEmail(credentials,password);
        }
        else
        {
            fetchedUser=loginDaoInterface.validateUserViaUsername(credentials,password);
        }
        if(!nullCheck(fetchedUser)&& fetchedUser.getActive())
        {
            setSessionAttribute("username",fetchedUser.getUsername(),request.getSession());

            if (fetchedUser.getAdmin())
            {
                return "adminPage";
            }
            else {
                return "dashboard";
            }
        }
        else
        {
            return "notlog";
        }

    }
}
