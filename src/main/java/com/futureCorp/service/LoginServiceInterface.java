package com.futureCorp.service;

public interface LoginServiceInterface {

    default String loginUser(String credentials,String password)
    {
        return "error";
    }

}
