package com.futureCorp.service;

import com.futureCorp.dao.TopicAddingDaoInterface;
import com.futureCorp.holder.UserFetcher;
import com.futureCorp.model.Topic;
import com.futureCorp.model.User;
import com.futureCorp.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicAddingService implements TopicAddingServiceInterface,UserFetcher {

    @Autowired
    TopicAddingDaoInterface topicAddingDaoInterface;

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
        if(topicAddingDaoInterface.addTopic(topic))
        {
            return "Successfully Added";
        }
        else
        {
            return "Error Adding Topic...Try again later..!!";
        }
    }
}
