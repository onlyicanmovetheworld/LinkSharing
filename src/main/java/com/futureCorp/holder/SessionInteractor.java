package com.futureCorp.holder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public interface SessionInteractor {
    final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    default void startSession(Session session)
    {
        session.beginTransaction();
    }
    default void stopSession(Session session)
    {
        session.getTransaction().commit();
        session.close();
    }
}
