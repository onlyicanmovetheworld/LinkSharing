package com.futureCorp.service;

public interface UserActionServiceInterface {

    default boolean markingAsRead(Integer id)
    {
        return false;
    }


}
