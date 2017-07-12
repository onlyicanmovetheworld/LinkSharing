package com.futureCorp.service;

import com.futureCorp.dao.SubscriberDaoInterface;
import com.futureCorp.holder.Fetcher;
import com.futureCorp.model.Seriousness;
import com.futureCorp.model.Subscription;
import com.futureCorp.model.Topic;
import com.futureCorp.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionService implements SubscriptionServiceInterface,Fetcher {

    @Autowired
    SubscriberDaoInterface subscriberDaoInterface;

    @Override
    public String subscribe(String username,String topicName,String seriousness) {
        User user = fetchUser(username);
        Topic topic = fetchTopic(username,topicName);
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setTopic(topic);
        if(seriousness.equalsIgnoreCase("very serious"))
        {
            subscription.setSeriousness(Seriousness.VerySerious);
        }
        else if(seriousness.equalsIgnoreCase("casual"))
        {
            subscription.setSeriousness(Seriousness.Casual);
        }
        if(subscriberDaoInterface.addSubscriber(subscription))
        {
            return "Subscribed Successfully";
        }
        else
        {
            return "Error Adding Topic...Try again later..!!";
        }
    }
}
