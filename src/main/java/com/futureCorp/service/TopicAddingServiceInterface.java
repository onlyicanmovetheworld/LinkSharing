package com.futureCorp.service;

public interface TopicAddingServiceInterface {

    default String addingTopic(String username,String topicName,String visibility)
    {
        return null;
    }

}
