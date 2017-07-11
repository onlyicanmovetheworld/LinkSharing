package com.futureCorp.dao;


import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.User;
import org.hibernate.Session;

public class RegisterDao implements RegisterDaoInterface,SessionInteractor {

    @Override
    public boolean saveRegisteredUser(User user) {

       Session session = sessionFactory.openSession();
        startSession(session);
        Integer commited =(Integer) session.save(user);
        stopSession(session);

        if(commited>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
