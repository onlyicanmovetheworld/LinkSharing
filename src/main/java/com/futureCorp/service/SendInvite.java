package com.futureCorp.service;

import com.futureCorp.holder.SendEmail;

import java.io.UnsupportedEncodingException;

public class SendInvite implements SendInviteInterface,SendEmail {

    @Override
    public Boolean sendingInviteEmail(String to, String topic,String who) {
        String message="<html><body>You were sent an invitation by "+who+"<br><a href='http://10.1.12.163:8080/subscribeToInvite?name="+topic+"'>Click Here</a> to subscribe now <body></html>";

        try {
            SendEmail.send(to,message,"Invite to subscribe by "+who);
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
