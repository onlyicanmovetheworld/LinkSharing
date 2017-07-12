package com.futureCorp.dao;

import com.futureCorp.model.Subscription;
import com.futureCorp.model.Topic;

public interface SubscriberDaoInterface {

    default boolean addSubscriber(Subscription subscription)
    {
        return false;
    }
}
