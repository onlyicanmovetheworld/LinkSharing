package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserActionDao implements UserActionDaoInterface,SessionInteractor {

    Session session;
    @Override
    public Boolean addAsRead(Integer id) {
        session = sessionFactory.openSession();
        startSession(session);
        String queryString = "update ReadingItem set isRead=true where id=:id";
        Query query = session.createQuery(queryString);
        query.setInteger("id",id);
       int updated = query.executeUpdate();
        stopSession(session);
        if(updated>0) {
            return true;
        }
        else {
            return false;
        }
    }
}
