package com.futureCorp.holder;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public interface SizeFinder extends SessionInteractor {


    default Long fetchMaxSizeTopicResource(String topicName)
    {   Session session;
        session = sessionFactory.openSession();
        String topic=topicName.substring(0,topicName.indexOf("By"));
        String username=topicName.substring(topicName.indexOf("By")+2,topicName.length());

        String queryString = " select count(*) from Resource where topic.name = :name and  topic.createdBy.username = :username";
        Query query = session.createQuery(queryString);
        query.setString("name",topic);
        query.setString("username",username);

        Long max = (Long)query.uniqueResult();

        return max;
    }

    default Long fetchMaxSizeInbox(String username)
    {   Session session;
        session = sessionFactory.openSession();


        String queryString = " select count(*) from ReadingItem r where r.user.username = :username and  isRead = false";
        Query query = session.createQuery(queryString);

        query.setString("username",username);

        Long max = (Long)query.uniqueResult();

        return max;
    }

}
