package com.futureCorp.holder;

import com.futureCorp.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public interface UserFetcher extends SessionInteractor{

    default User fetchUser(String username)
    {   Session session = sessionFactory.openSession();
        startSession(session);
        String queryString = "from User where username = :username ";
        Query query = session.createQuery(queryString);
        query.setString("username", username);
        Object queryResult = query.uniqueResult();
        stopSession(session);
        return  (User)queryResult;
    }

}
