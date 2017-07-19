package com.futureCorp.dao;

import com.futureCorp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public interface LoginDaoInterface {



    default User validateUser(String credentials, String password)
    {
        return null;
    }


}
