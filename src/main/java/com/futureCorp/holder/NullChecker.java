package com.futureCorp.holder;

import com.futureCorp.model.User;

public interface NullChecker {

    default boolean nullCheck(Object object)
    {
        if(object==null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
