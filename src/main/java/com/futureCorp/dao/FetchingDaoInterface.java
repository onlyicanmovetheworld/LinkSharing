package com.futureCorp.dao;

import com.futureCorp.model.Resource;
import com.futureCorp.model.Topic;
import org.hibernate.Query;

import java.util.List;


public interface FetchingDaoInterface {

    default List<Topic> fetchTopic(String nameLike)
    {
        return null;
    }

    default List<Resource> fetchResource(String topicName ,Integer index)
    {
        return null;
    }

    default List<Object> fetchDataforAdmin(String className,String type)
    {
        return null;
    }


   default List<Topic> fetchSubscribedTopic(String nameLike,String username) {

        return null;
    }


    default List<Resource> fetchRecentShares() {

        return null;
    }

    default List<Resource> fetchInbox(String username,Integer index) {

        return null;
    }
}
