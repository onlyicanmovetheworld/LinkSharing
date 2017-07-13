package com.futureCorp.service;

import com.futureCorp.model.CreateLinkedResource;

import javax.servlet.http.HttpServletRequest;

public interface ResourceAddingServiceInterface {

    default String addingResource(CreateLinkedResource createLinkedResource, HttpServletRequest request)
    {
        return null;
    }

}
