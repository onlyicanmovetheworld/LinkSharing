package com.futureCorp.service;

import com.futureCorp.dao.FetchingDaoInterface;
import com.futureCorp.model.Resource;
import com.futureCorp.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FetchingDataService implements FetchingDataServiceInterface{

    @Autowired
    FetchingDaoInterface fetchingDaoInterface;

    @Override
    public List<Topic> fetchingList(String nameLike) {
      return fetchingDaoInterface.fetchTopic(nameLike);

    }

    @Override
    public List<Resource> fetchingList(String topicName, Integer index) {

        topicName = topicName.substring(0,topicName.indexOf("~"));
        System.out.println(topicName);
        return fetchingDaoInterface.fetchTopic(topicName,index);
    }
}
