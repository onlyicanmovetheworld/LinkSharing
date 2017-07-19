package com.futureCorp.service;

import com.futureCorp.dao.UserActionDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class UserActionService implements UserActionServiceInterface{

    @Autowired
    UserActionDaoInterface userActionDaoInterface;

    @Override
    public boolean markingAsRead(Integer id) {
        return userActionDaoInterface.addAsRead(id);
    }
}
