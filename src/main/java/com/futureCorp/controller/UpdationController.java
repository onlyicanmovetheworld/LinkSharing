package com.futureCorp.controller;

import com.futureCorp.holder.Fetcher;
import com.futureCorp.model.OTP;
import com.futureCorp.model.User;
import com.futureCorp.service.UpdationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

import static com.futureCorp.holder.SendEmail.password;

@Controller
public class UpdationController implements Fetcher {

    @Autowired
    UpdationServiceInterface updationServiceInterface;

    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public  @ResponseBody
    String updateForgottenPassword(@ModelAttribute OTP otp, @RequestParam("password")String password) throws UnsupportedEncodingException {
        if(updationServiceInterface.updatingPassword(otp,password))
        {
            return true+"";
        }
        else
        {
            return false+"";
        }

    }

    @RequestMapping(value = "/updateUserProfilePassword",method = RequestMethod.POST)
    public
    String updatePassword( @RequestParam("oldPassword")String oldPassword, @RequestParam("newPassword")String newPassword,HttpServletRequest request) throws UnsupportedEncodingException {

        User old = fetchUser(request.getSession().getAttribute("username").toString());

        if(updationServiceInterface.updatingPassword(oldPassword,newPassword,old.getEmailId()))
        {
            return "redirect:/updateProfile";
        }
        else
        {
            return false+"";
        }

    }
    @RequestMapping(value = "/updateUserProfileDetails",method = RequestMethod.POST)
    public
    String updateDetails(@ModelAttribute User user, HttpServletRequest request) throws UnsupportedEncodingException {

        User old = fetchUser(request.getSession().getAttribute("username").toString());
        user.setEmailId(old.getEmailId());
        if(updationServiceInterface.updatingDetails(user))
        {

            request.getSession().setAttribute("username",user.getUsername());

            return "redirect:/updateProfile";
        }
        else
        {
            return false+"";
        }

    }

    @RequestMapping(value = "/updatePhoto",method = RequestMethod.POST)
    public
    String updateDetails(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("aya1");
        User old = fetchUser(request.getSession().getAttribute("username").toString());
        if(updationServiceInterface.updatingPhoto(file,old.getEmailId()))
        {
            System.out.println("aya");
            return "redirect:/updateProfile";
        }
        else
        {
            return false+"";
        }

    }
}
