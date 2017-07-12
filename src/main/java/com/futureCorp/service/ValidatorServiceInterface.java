package com.futureCorp.service;

public interface ValidatorServiceInterface {

    default boolean validationOfUser(String credential)
    {
        return false;
    }

    default boolean validationOfTopic(String username,String topicName)
    {
        return false;
    }
}
