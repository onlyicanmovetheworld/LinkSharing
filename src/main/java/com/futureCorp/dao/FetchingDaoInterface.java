package com.futureCorp.dao;

import com.futureCorp.model.Topic;

import java.util.List;


public interface FetchingDaoInterface {

    default List<Topic> fetchTopic(String nameLike)
    {
        return null;
    }

}
