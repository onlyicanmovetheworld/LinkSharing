package com.futureCorp.service;

import com.futureCorp.dao.AddReadingItemDaoInterface;
import com.futureCorp.dao.ResourceAddingDaoInterface;
import com.futureCorp.holder.Fetcher;
import com.futureCorp.model.CreateLinkedResource;
import com.futureCorp.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class ResourceAddingService implements ResourceAddingServiceInterface,Fetcher{

    @Autowired
    ResourceAddingDaoInterface resourceAddingDaoInterface;

    @Autowired
    AddReadingItemDaoInterface addReadingItemDaoInterface;

    @Override
    public String addingResource(CreateLinkedResource createLinkedResource, HttpServletRequest request) {
        Resource resource = new Resource();

        resource.setCreatedBy(fetchUser(request.getSession().getAttribute("username").toString()));
        resource.setTopic(fetchTopic(createLinkedResource.getTopic()));
        resource.setDescription(createLinkedResource.getDesc());
        resource.setLink(createLinkedResource.getLink());
        resource.setResourceType(createLinkedResource.getResourceType());
        if(resourceAddingDaoInterface.AddResource(resource)&&addReadingItemDaoInterface.addReadingItem(resource))
        {
            return "Resource added Successfully";
        }
            else
        {
            return "Unable to add resource..!!";
        }
    }
}
