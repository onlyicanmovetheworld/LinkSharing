package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class LoginDao implements LoginDaoInterface,SessionInteractor{

    User user;
    Session session;


    @Override
    public User validateUser(String credentials,String password) {
        session= sessionFactory.openSession();
        startSession(session);
        String queryString = "from User where (username = :username OR emailId = :username) AND password = :password";
        Query query = session.createQuery(queryString);
        System.out.println(credentials);
        query.setString("username", credentials);
        query.setString("password",password);
        Object queryResult = query.uniqueResult();
        user = (User)queryResult;
        stopSession(session);

        return user;
    }
}
