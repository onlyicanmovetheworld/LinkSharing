package com.futureCorp.controller;

import com.futureCorp.model.User;
import com.futureCorp.service.LoginServiceInterface;
import com.futureCorp.service.RegistrationServiceInterface;
import com.futureCorp.service.ValidatorServiceInterface;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class UserAccessController {



    @Autowired
    RegistrationServiceInterface registrationServiceInterface;

    @Autowired
    LoginServiceInterface loginServiceInterface;

    String view;

    @RequestMapping("")
            public ModelAndView showHome(HttpServletRequest request)
            {   if(request.getSession().getAttribute("username")==null) {

                ModelAndView modelAndView = new ModelAndView("home");
                modelAndView.addObject("user", new User());

                return modelAndView;
            }
            else
            {
               return new ModelAndView("redirect:/dashboard");
            }
            }
    @RequestMapping("/forgotPassword")
    public ModelAndView showForgot()
    {
        ModelAndView modelAndView = new ModelAndView("forgotPassword");


        return modelAndView;
    }

    @RequestMapping(value = "/registerUser" ,method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, ModelMap modelMap, @RequestParam("photo")MultipartFile file,HttpServletRequest request) throws IOException {
        if(!file.isEmpty())
        {
            user.setPhoto(file.getBytes());
        }
        else
        {
            Path path = Paths.get("C:\\Users\\Shubham\\Downloads\\LinkSharing\\src\\main\\resources\\unknown.png");
            byte[] data = Files.readAllBytes(path);
            user.setPhoto(data);
        }
        view = registrationServiceInterface.registering(user,request);

        view="redirect:/dashboard";
        return view;
    }

    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public String loginUser(@RequestParam("credentials") String credentials, @RequestParam("password") String password, HttpServletRequest request) throws IOException {



        view =loginServiceInterface.loginUser(credentials,password,request);
        view="redirect:/dashboard";
        return view;
    }

    @RequestMapping("/subscribeToInvite")
    public String subscribeToInvite(@RequestParam("name") String topicName,HttpServletRequest request)
    {
        String name=topicName.substring(0,topicName.indexOf("By"));
        String username=topicName.substring(topicName.indexOf("By")+2,topicName.length());
        if(request.getSession().getAttribute("username")==null) {
            request.getSession().setAttribute("inviteTopic", topicName);

            return "redirect:/";
        }
        else
        {
            return "invite";
        }

    }

    @RequestMapping("/dashboard")
    public String redirectDashboard(HttpServletRequest httpServletRequest)
    {
        if(httpServletRequest.getSession().getAttribute("inviteTopic")==null)
        {
            return "dashboard";
        }
        else
        {   String topic =httpServletRequest.getSession().getAttribute("inviteTopic").toString();
        httpServletRequest.getSession().setAttribute("inviteTopic",null);
            return "redirect:/subscribeToInvite?name="+topic;
        }
    }

}
