package com.futureCorp.service;

import com.futureCorp.model.User;

import javax.servlet.http.HttpServletRequest;

public interface RegistrationServiceInterface {

    default String registering(User user, HttpServletRequest request)
    {

        return "home";
    }

}
