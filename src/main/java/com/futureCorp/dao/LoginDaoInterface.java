package com.futureCorp.dao;

import com.futureCorp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public interface LoginDaoInterface {



    default User validateUserViaEmail(String credentials, String password)
    {
        return null;
    }
    default User validateUserViaUsername(String credentials,String password)
    {
        return null;
    }

}
