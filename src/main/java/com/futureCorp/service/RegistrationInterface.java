package com.futureCorp.service;

import com.futureCorp.model.User;

public interface RegistrationInterface {

    default String registering(User user)
    {

        return "home";
    }

}
