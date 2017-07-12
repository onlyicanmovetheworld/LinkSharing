package com.futureCorp.service;

public interface SubscriptionServiceInterface {

    default String subscribe(String username,String topicName,String seriousness)
    {
        return null;
    }

}
