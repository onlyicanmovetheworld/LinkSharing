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
    default Topic fetchTopic(String topicName)
    {   Session session = sessionFactory.openSession();
        startSession(session);
        String topic=topicName.substring(0,topicName.indexOf("By"));
        String username=topicName.substring(topicName.indexOf("By")+2,topicName.length());
        String queryString = "from Topic  where name = :topicName and createdBy.username = :username";
        Query query = session.createQuery(queryString);
        query.setString("username", username);
        query.setString("topicName", topic);
        Object queryResult = query.uniqueResult();
        stopSession(session);

        return  (Topic)queryResult;
    }

    default Long fetchCountSubscriptionUser(String username)
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
    default Long fetchCountSubscriptionTopic(String topicName)
    {
        Session session = sessionFactory.openSession();
        startSession(session);
        String topic=topicName.substring(0,topicName.indexOf("By"));
        String username=topicName.substring(topicName.indexOf("By")+2,topicName.length());
        String queryString = "select count(*)from  Subscription s where s.topic.name = :topic AND s.topic.createdBy.username = :username";
        Query query = session.createQuery(queryString);
        query.setString("username", username);
        query.setString("topic", topic);
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
    default Long fetchCountPost(String topicName)
    {
        Session session = sessionFactory.openSession();
        startSession(session);
        String topic=topicName.substring(0,topicName.indexOf("By"));
        String username=topicName.substring(topicName.indexOf("By")+2,topicName.length());
        String queryString = "select count(*)from  Resource r where r.topic.name = :topic AND r.topic.createdBy.username = :username";
        Query query = session.createQuery(queryString);
        query.setString("username", username);
        query.setString("topic", topic);
        Long count =(Long) query.uniqueResult();
        stopSession(session);

        return  count;
    }
}
