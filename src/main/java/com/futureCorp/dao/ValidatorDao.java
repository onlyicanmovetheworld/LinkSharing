package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.holder.NullChecker;
import com.futureCorp.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class ValidatorDao implements ValidatorDaoInterface,SessionInteractor,NullChecker {
    Session session;
    User user;
    @Override
    public boolean validateUsernameExistance(String username) {
        session= sessionFactory.openSession();
        startSession(session);
        String queryString = "from User where username = :username";
        Query query = session.createQuery(queryString);
        query.setString("username", username);
        Object queryResult = query.uniqueResult();
        user = (User)queryResult;
        stopSession(session);
        return nullCheck(user);
    }

    @Override
    public boolean validateEmailExistance(String email) {

        session= sessionFactory.openSession();
        startSession(session);
        String queryString = "from User where emailId = :email";
        Query query = session.createQuery(queryString);
        query.setString("email", email);
        Object queryResult = query.uniqueResult();
        user = (User)queryResult;
        stopSession(session);

        return nullCheck(user);
    }
}
