package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.Resource;
import org.hibernate.Session;

public class ResourceAddingDao implements ResourceAddingDaoInterface,SessionInteractor {

    @Override
    public boolean AddResource(Resource resource) {
        Session session = sessionFactory.openSession();
        startSession(session);
        Integer commit =(Integer) session.save(resource);
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
