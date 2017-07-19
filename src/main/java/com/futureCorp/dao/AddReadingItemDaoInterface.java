package com.futureCorp.dao;

import com.futureCorp.model.Resource;
import com.futureCorp.model.Subscription;

public interface AddReadingItemDaoInterface {

    default Boolean addReadingItem(Resource resource)
    {
        return false;
    }



}
