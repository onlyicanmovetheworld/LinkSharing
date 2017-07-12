package com.futureCorp.service;

import java.util.List;

public interface FetchingDataServiceInterface {

    default List<String> fetchingList(String nameLike)
    {
        return null;
    }

}
