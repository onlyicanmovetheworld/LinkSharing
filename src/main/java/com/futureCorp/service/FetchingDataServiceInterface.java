package com.futureCorp.service;

import com.futureCorp.model.Resource;
import com.futureCorp.model.Topic;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface FetchingDataServiceInterface {

    default List<Topic> fetchingList(String nameLike)
    {
        return null;
    }
    default List<Resource> fetchingList(String topicName, Integer index)
    {
        return null;
    }

}
