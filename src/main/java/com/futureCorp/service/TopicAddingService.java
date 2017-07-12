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
    public String addingTopic(String username, String topicName, String visibility) {
        User user = fetchUser(username);
        Topic topic = new Topic();
        topic.setCreatedBy(user);
        topic.setName(topicName);
        if(visibility.equalsIgnoreCase("private"))
        {
            topic.setVisibility(Visibility.Private);
        }
        if(topicAddingDaoInterface.addTopic(topic)&&subscriptionServiceInterface.subscribe(username,topicName,"very serious").contains("Success"))
        {
            return "Successfully Added";
        }
        else
        {
            return "Error Adding Topic...Try again later..!!";
        }
    }
}
