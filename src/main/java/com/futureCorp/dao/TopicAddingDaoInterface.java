package com.futureCorp.dao;

import com.futureCorp.model.Topic;


public interface TopicAddingDaoInterface {

    default boolean addTopic(Topic topic)
    {
        return false;
    }
}
