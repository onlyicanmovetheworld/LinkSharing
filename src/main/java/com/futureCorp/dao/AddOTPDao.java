package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.OTP;
import org.hibernate.Query;
import org.hibernate.Session;

public class AddOTPDao implements AddOTPDaoInterface,SessionInteractor {

    Integer commited;
    @Override
    public Boolean addVerficationCode(OTP otp) {
        Session session = sessionFactory.openSession();
        startSession(session);
        String queryString = "  from OTP where email = :email";
        Query query = session.createQuery(queryString);
        query.setString("email", otp.getEmail());
        if(query.uniqueResult()!=null)
        {
            queryString = "update OTP set otp = :otp where email = :email";
             query = session.createQuery(queryString);
            query.setInteger("otp",otp.getOtp());
            query.setString("email",otp.getEmail());
             commited = query.executeUpdate();
        }
            else
        {
             commited = (Integer) session.save(otp);
        }
        stopSession(session);

        if(commited>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
