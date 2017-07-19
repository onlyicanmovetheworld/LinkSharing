package com.futureCorp.holder;

import com.futureCorp.model.Topic;
import com.futureCorp.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public interface Fetcher extends SessionInteractor{

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
    default Topic fetchTopic(String username,String topicName)
    {   Session session = sessionFactory.openSession();
        startSession(session);
        String queryString = "from Topic  where name = :topicName and createdBy in (select userId from User where username = :username)";
        Query query = session.createQuery(queryString);
        query.setString("username", username);
        query.setString("topicName", topicName);
        Object queryResult = query.uniqueResult();
        stopSession(session);

        return  (Topic)queryResult;
    }

    default Long fetchCountSubscription(String username)
    {
        Session session = sessionFactory.openSession();
        startSession(session);
        String queryString = "select count(*)from  Subscription s where s.user.username = :username";
        Query query = session.createQuery(queryString);
        query.setString("username", username);

        Long count =(Long) query.uniqueResult();
        stopSession(session);

        return  count;
    }

    default Long fetchCountTopic(String username)
    {
        Session session = sessionFactory.openSession();
        startSession(session);
        String queryString = "select count(*)from  Topic t where t.createdBy.username = :username";
        Query query = session.createQuery(queryString);
        query.setString("username", username);
        Long count =(Long) query.uniqueResult();
        stopSession(session);

        return  count;
    }
}
