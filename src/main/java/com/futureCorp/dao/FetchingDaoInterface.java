package com.futureCorp.dao;

import java.util.List;

public interface FetchingDaoInterface {

    default List<String> fetchTopic(String nameLike)
    {
        return null;
    }

}
