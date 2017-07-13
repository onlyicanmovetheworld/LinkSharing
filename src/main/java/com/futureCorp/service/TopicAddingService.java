package com.futureCorp.service;

import com.futureCorp.dao.SubscriberDaoInterface;
import com.futureCorp.dao.TopicAddingDaoInterface;
import com.futureCorp.holder.Fetcher;
import com.futureCorp.model.Topic;
import com.futureCorp.model.User;
import com.futureCorp.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicAddingService implements TopicAddingServiceInterface,Fetcher {

    @Autowired
    TopicAddingDaoInterface topicAddingDaoInterface;

    @Autowired
    SubscriptionServiceInterface subscriptionServiceInterface;

    @Override
    public String addingTopic(String username ,Topic topic) {
        User user = fetchUser(username);
        topic.setCreatedBy(user);

        if(topicAddingDaoInterface.addTopic(topic)&&subscriptionServiceInterface.subscribe(username,topic.getName() ,"very serious").contains("Success"))
        {
            return "Successfully Added";
        }
        else
        {
            return "Error Adding Topic...Try again later..!!";
        }
    }
}
