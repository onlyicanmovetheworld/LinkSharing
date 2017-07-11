package com.futureCorp.service;

import com.futureCorp.model.User;

public interface RegistrationServiceInterface {

    default String registering(User user)
    {

        return "home";
    }

}
