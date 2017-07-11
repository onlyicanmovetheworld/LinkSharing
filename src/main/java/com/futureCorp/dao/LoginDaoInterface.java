package com.futureCorp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public interface LoginDaoInterface {

    final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    default boolean validateUserViaEmail(String credentials,String password)
    {
        return false;
    }
    default boolean validateUserViaUsername(String credentials,String password)
    {
        return false;
    }

}
