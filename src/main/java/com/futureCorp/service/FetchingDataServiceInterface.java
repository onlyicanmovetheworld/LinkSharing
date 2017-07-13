package com.futureCorp.service;

import com.futureCorp.model.Topic;

import java.util.List;

public interface FetchingDataServiceInterface {

    default List<Topic> fetchingList(String nameLike)
    {
        return null;
    }

}
