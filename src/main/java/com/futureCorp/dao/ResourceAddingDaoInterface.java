package com.futureCorp.dao;

import com.futureCorp.model.CreateLinkedResource;
import com.futureCorp.model.Resource;

public interface ResourceAddingDaoInterface {

    default boolean AddResource(Resource resource)
    {
        return false;

    }

}
