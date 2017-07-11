package com.futureCorp.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginServiceInterface {

    default String loginUser(String credentials, String password, HttpServletRequest request)
    {
        return "error";
    }

}
