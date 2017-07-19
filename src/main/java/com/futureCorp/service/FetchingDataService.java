package com.futureCorp.service;

import com.futureCorp.dao.FetchingDaoInterface;
import com.futureCorp.model.ReadingItem;
import com.futureCorp.model.Resource;
import com.futureCorp.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FetchingDataService implements FetchingDataServiceInterface{

    @Autowired
    FetchingDaoInterface fetchingDaoInterface;

    @Override
    public List<Topic> fetchingList(String nameLike) {
      return fetchingDaoInterface.fetchTopic(nameLike);

    }

    @Override
    public List<Topic> fetchingSubscribedList(String nameLike, HttpServletRequest request) {
        return fetchingDaoInterface.fetchSubscribedTopic(nameLike,request.getSession().getAttribute("username").toString());

    }

    @Override
    public List<Resource> fetchingList(String topicName, Integer index) {

        topicName = topicName.substring(0,topicName.indexOf("By"));
        System.out.println(topicName);
        if(index==0)
        {

        }
        return fetchingDaoInterface.fetchResource(topicName,index);
    }

    @Override
    public List<Object> fetchingListForAdmin(String className, String type) {
        return fetchingDaoInterface.fetchDataforAdmin(className,type);
    }

    @Override
    public List<Resource> fetchingRecentShares() {
        return fetchingDaoInterface.fetchRecentShares();
    }

    @Override
   public List<ReadingItem> fetchingInbox(String username, Integer index)
    {
        return fetchingDaoInterface.fetchInbox(username,index);
    }
}
