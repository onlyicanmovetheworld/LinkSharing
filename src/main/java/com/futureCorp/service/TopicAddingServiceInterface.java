package com.futureCorp.service;

import com.futureCorp.model.Topic;

public interface TopicAddingServiceInterface {

    default String addingTopic(String username,Topic topic)
    {
        return null;
    }

}
