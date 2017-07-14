package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;

import com.futureCorp.model.Resource;
import com.futureCorp.model.Topic;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

public class FetchingDao implements FetchingDaoInterface,SessionInteractor{

Session session;
    @Override
    public List<Topic> fetchTopic(String nameLike) {
        session=sessionFactory.openSession();
        startSession(session);
        String queryString = " select name,createdBy.username from Topic where name like :name AND visibility = :visibility";
        Query query = session.createQuery(queryString);
        query.setString("name", nameLike+"%");
        query.setString("visibility", "Public");
        List<Topic> names = query.list();
        stopSession(session);
        return names;
    }

    @Override
    public List<Resource> fetchTopic(String topicName, Integer index) {

        session=sessionFactory.openSession();

        String queryString = "from Resource where topic.name = :name AND topic.visibility = :visibility";
        Query query = session.createQuery(queryString);
        query.setString("name", topicName);
        query.setString("visibility", "Public");
        query.setFirstResult(index);
        query.setMaxResults(2);
        List<Resource> names = query.list();

        return names;

    }
}
