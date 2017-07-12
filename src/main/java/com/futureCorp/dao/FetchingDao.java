package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class FetchingDao implements FetchingDaoInterface,SessionInteractor{

Session session;
    @Override
    public List<String> fetchTopic(String nameLike) {
        session=sessionFactory.openSession();
        startSession(session);
        String queryString = "select name from Topic where name like :name AND visibility = 1";
        Query query = session.createQuery(queryString);
        query.setString("name", nameLike+"%");
        List<String> names = query.list();
        stopSession(session);
        return names;
    }
}
