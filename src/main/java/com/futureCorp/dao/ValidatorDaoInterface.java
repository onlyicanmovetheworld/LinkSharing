package com.futureCorp.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public interface ValidatorDaoInterface {


    default boolean validateUsernameExistance(String username)
{
    return false;
}
    default boolean validateEmailExistance(String email)
    {
        return false;
    }
}
