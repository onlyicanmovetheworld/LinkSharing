package com.futureCorp.dao;


import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.Topic;
import org.hibernate.Session;


public class TopicAddingDao implements TopicAddingDaoInterface,SessionInteractor {

    @Override
    public boolean addTopic(Topic topic) {

        Session session = sessionFactory.openSession();
        startSession(session);
        Integer commit =(Integer) session.save(topic);
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
