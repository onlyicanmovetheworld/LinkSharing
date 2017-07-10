package com.futureCorp.dao;


import com.futureCorp.model.User;
import org.hibernate.Session;

public class RegisterUser implements RegisterUserInterface {

    @Override
    public boolean saveRegisteredUser(User user) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer commited =(Integer) session.save(user);
        session.getTransaction().commit();
        session.close();

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
