package com.futureCorp.service;

import com.futureCorp.model.ReadingItem;
import com.futureCorp.model.Resource;
import com.futureCorp.model.Topic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
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

    default List<Object> fetchingListForAdmin(String className,String type)
    {
        return null;
    }

    default List<Topic> fetchingSubscribedList(String nameLike, HttpServletRequest request) {
        return null;

    }
    default List<Resource> fetchingRecentShares()
    {
        return null;
    }
    default List<ReadingItem> fetchingInbox(String username, Integer index)
    {
        return null;
    }
}
