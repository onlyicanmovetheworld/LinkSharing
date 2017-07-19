package com.futureCorp.dao;

public interface UserActionDaoInterface {

    default Boolean addAsRead(Integer id)
    {
        return false;
    }

}
