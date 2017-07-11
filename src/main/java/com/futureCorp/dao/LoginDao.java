package com.futureCorp.dao;

import com.futureCorp.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class LoginDao implements LoginDaoInterface{

    User user;
    Session session;
    @Override
    public boolean validateUserViaEmail(String credentials,String password) {
        session=sessionFactory.openSession();
        session.beginTransaction();
        String queryString = "from User where emailId = :email AND password = :password";
        Query query = session.createQuery(queryString);
        query.setString("email", credentials);
        query.setString("password",password);
        Object queryResult = query.uniqueResult();
        user = (User)queryResult;
        session.getTransaction().commit();
        session.close();
        if(user!=null)
            return true;
        else
            return false;
    }

    @Override
    public boolean validateUserViaUsername(String credentials,String password) {
        session= sessionFactory.openSession();
        session.beginTransaction();
        String queryString = "from User where username = :username AND password = :password";
        Query query = session.createQuery(queryString);
        query.setString("username", credentials);
        query.setString("password",password);
        Object queryResult = query.uniqueResult();
        user = (User)queryResult;
        session.getTransaction().commit();
        session.close();
        if(user!=null)
            return true;
        else
            return false;
    }
}
