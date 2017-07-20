package com.futureCorp.dao;

import com.futureCorp.holder.SessionInteractor;
import com.futureCorp.model.OTP;
import com.futureCorp.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.Date;

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

    @Override
    public Boolean updatePassword(String oldPassword, String newPassword,String email) {
        Session session = sessionFactory.openSession();
        String queryString;
        Query query;
        startSession(session);
        queryString = "update User set password = :newPassword,lastUpdated=:date where password = :oldPassword AND emailId=:email";
        query = session.createQuery(queryString);
        query.setString("newPassword",newPassword);
        query.setString("oldPassword",oldPassword);
        query.setString("email",email);
        query.setParameter("date",new Date(), TemporalType.TIMESTAMP);
        commited = query.executeUpdate();
        if(commited>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean updateDetails(User user) {
        Session session = sessionFactory.openSession();
        String queryString;
        Query query;
        startSession(session);
        queryString = "update User set firstName=:firstName,lastName=:lastName,username=:username,lastUpdated=:date where emailId =:email";
        query = session.createQuery(queryString);
        query.setString("firstName",user.getFirstName());
        query.setString("lastName",user.getLastName());
        query.setString("username",user.getUsername());
        query.setString("email",user.getEmailId());
        query.setParameter("date",new Date(), TemporalType.TIMESTAMP);
        commited = query.executeUpdate();
        if(commited>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean updatePhoto(MultipartFile file,String email)  {
        Session session = sessionFactory.openSession();
        String queryString;
        Query query;
        startSession(session);
        queryString = "update User set photo=:photo,lastUpdated=:date where emailId =:email";
        query = session.createQuery(queryString);
        try {
            query.setParameter("photo",file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        query.setString("email",email);
        query.setParameter("date",new Date(), TemporalType.TIMESTAMP);
        commited = query.executeUpdate();
        if(commited>0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
