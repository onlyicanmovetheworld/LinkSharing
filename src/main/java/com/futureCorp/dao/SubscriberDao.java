package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.Subscription;
import com.futureCorp.model.Topic;
import org.hibernate.Session;

public class SubscriberDao implements SubscriberDaoInterface,SessionInteractor {

    @Override
    public boolean addSubscriber(Subscription subscription) {
        Session session = sessionFactory.openSession();
        startSession(session);
        Integer commit =(Integer) session.save(subscription);
        stopSession(session);

        if(commit>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
