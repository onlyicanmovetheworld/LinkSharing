package com.futureCorp.holder;

import com.futureCorp.model.User;

public interface UserChecker {

    default boolean existanceCheck(User user)
    {
        if(user!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
