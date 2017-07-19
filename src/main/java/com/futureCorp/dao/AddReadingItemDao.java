package com.futureCorp.dao;

import com.futureCorp.holder.Fetcher;
import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.ReadingItem;
import com.futureCorp.model.Resource;
import com.futureCorp.model.Subscription;
import com.futureCorp.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddReadingItemDao implements AddReadingItemDaoInterface,SessionInteractor,Fetcher {


    Session session;
    @Override
    public Boolean addReadingItem(Resource resource) {
            ReadingItem item;
        session = sessionFactory.openSession();

        String queryString = " select user.username from Subscription where topic.name = :name and  topic.createdBy.username = :username";
        Query query = session.createQuery(queryString);
        query.setString("name",resource.getTopic().getName());
        query.setString("username",resource.getTopic().getCreatedBy().getUsername());
        List<String> usernames = query.list();

        for(String username :usernames)
        {
            User user = fetchUser(username);
            item = new ReadingItem();
            item.setUser(user);
            item.setResource(resource);
            startSession(session);
            session.save(item);
            stopSession(session);
        }



        return true;

    }


}
