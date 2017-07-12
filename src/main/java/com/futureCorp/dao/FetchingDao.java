package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.User;
import com.futureCorp.model.Visibility;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class FetchingDao implements FetchingDaoInterface,SessionInteractor{

Session session;
    @Override
    public List<String> fetchTopic(String nameLike) {
        session=sessionFactory.openSession();
        startSession(session);
        String queryString = "select name from Topic where name like :name AND visibility = :visibility";
        Query query = session.createQuery(queryString);
        query.setString("name", nameLike+"%");
        query.setString("visibility", Visibility.Public.getValue());
        List<String> names = query.list();

        stopSession(session);
        return names;
    }
}
