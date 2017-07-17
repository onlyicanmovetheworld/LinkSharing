package com.futureCorp.service;

public interface SendInviteInterface {

    default Boolean sendingInviteEmail(String to, String topic,String who)
    {
        return false;
    }

}
