package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.OTP;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;

public class UpdateDataDao implements UpdateDataDaoInterface,SessionInteractor {

    Integer commited;
    @Override
    public Boolean updatePassword(OTP otp, String password) {

        Session session = sessionFactory.openSession();
        startSession(session);
        String queryString = " from OTP where email = :email AND otp = :otp ";
        Query query = session.createQuery(queryString);
        query.setString("email", otp.getEmail());
        query.setInteger("otp",otp.getOtp());
        if(query.uniqueResult()!=null)
        {
            queryString = "update User set password = :password where emailId = :email";
            query = session.createQuery(queryString);
            query.setString("password",password);
            query.setString("email",otp.getEmail());
            commited = query.executeUpdate();
             queryString = " delete from OTP where email = :email AND otp = :otp ";
            query = session.createQuery(queryString);
            query.setString("email", otp.getEmail());
            query.setInteger("otp",otp.getOtp());
            query.executeUpdate();

        }
        else
        {
            return false;
        }
        stopSession(session);

    return true;
    }
}
