package com.futureCorp.holder;

import com.futureCorp.model.User;


import javax.servlet.http.HttpSession;

public interface HttpSessionSetter {



    default void setSessionAttribute(String key, String value, HttpSession session)
    {
            session.setAttribute(key,value);
    }

    default void invalidateSession(HttpSession session)
    {
            session.invalidate();
    }
}
