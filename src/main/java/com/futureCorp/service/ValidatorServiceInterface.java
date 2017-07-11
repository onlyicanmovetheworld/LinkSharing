package com.futureCorp.service;

public interface ValidatorServiceInterface {

    default boolean validation(String credential)
    {
        return false;
    }
}
