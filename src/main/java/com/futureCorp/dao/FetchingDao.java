package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;

import com.futureCorp.model.ReadingItem;
import com.futureCorp.model.Resource;
import com.futureCorp.model.Topic;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

public class FetchingDao implements FetchingDaoInterface,SessionInteractor{

Session session;
    @Override
    public List<Topic> fetchTopic(String nameLike) {
        session=sessionFactory.openSession();
        startSession(session);
        String queryString = " select name,createdBy.username from Topic where name like :name AND visibility = :visibility";
        Query query = session.createQuery(queryString);
        query.setString("name", nameLike+"%");
        query.setString("visibility", "Public");
        List<Topic> names = query.list();
        stopSession(session);
        return names;
    }

    @Override
    public List<Topic> fetchSubscribedTopic(String nameLike,String username) {
        session=sessionFactory.openSession();
        startSession(session);
        String queryString = " select topic.name,topic.createdBy.username from Subscription where topic.name like :name and  user.username = :username";
        Query query = session.createQuery(queryString);
        query.setString("name", nameLike+"%");
        query.setString("username",username);
        List<Topic> names = query.list();
        stopSession(session);
        return names;
    }

    @Override
    public List<Resource> fetchResource(String topicName, Integer index) {

        session=sessionFactory.openSession();

        String queryString = "from Resource where topic.name = :name AND topic.visibility = :visibility";
        Query query = session.createQuery(queryString);
        query.setString("name", topicName);
        query.setString("visibility", "Public");
        query.setFirstResult(index);
        query.setMaxResults(2);
        List<Resource> names = query.list();

        return names;

    }

    @Override
    public List<Object> fetchDataforAdmin(String className, String type) {

        session = sessionFactory.openSession();
        String queryString;
        switch (type)
        {
            case "Active":{
                                queryString ="From User where active = true";
                                break;
            }
            case "inActive":{
                queryString ="From User where active = false";
                break;
            }
            case "Private":{
                queryString ="From Topic where visibility = 'Private'";
                break;
            }
            case "Public":{
                queryString ="From Topic where visibility = 'Public'";
                break;
            }
            case "Link":{
                queryString ="From Resource where resourceType = 'Link'";
                break;
            }
            case "Document":{
                queryString ="From Resource where  resourceType = 'Document'";
                break;
            }

            default:{
                        queryString="From "+className   ;
            }
        }

        Query query = session.createQuery(queryString);

        List<Object> fetchedData = query.list();

        return fetchedData;

    }


    @Override
    public List<Resource> fetchRecentShares() {
        session = sessionFactory.openSession();
        String queryString = "from Resource where  topic.visibility = :visibility order by createdDate desc ";
        Query query = session.createQuery(queryString);
        query.setString("visibility", "Public");
        query.setMaxResults(5);
        List<Resource> recent = query.list();

        return recent;

    }

    @Override
    public List<ReadingItem> fetchInbox(String username, Integer index) {
        session = sessionFactory.openSession();
        String queryString = " from ReadingItem r where  r.user.username = :username and isRead = false ";
        Query query = session.createQuery(queryString);
        query.setString("username", username);
        query.setFirstResult(index);
        query.setMaxResults(5);
        List<ReadingItem> inbox = query.list();

        return inbox;

    }
}
