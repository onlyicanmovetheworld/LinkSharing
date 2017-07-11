package com.futureCorp.service;

import com.futureCorp.dao.ValidatorDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorService implements ValidatorServiceInterface {

    boolean validated;

    @Autowired
    ValidatorDaoInterface validatorDaoInterface;

    @Override
    public boolean validation(String credential) {
        if(credential.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
        {
            return validated=validatorDaoInterface.validateEmailExistance(credential);
        }
        else
        {
            return validatorDaoInterface.validateUsernameExistance(credential);
        }
    }
}
