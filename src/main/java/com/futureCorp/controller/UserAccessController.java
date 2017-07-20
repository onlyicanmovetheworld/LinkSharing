package com.futureCorp.controller;

import com.futureCorp.holder.Fetcher;
import com.futureCorp.holder.SizeFinder;
import com.futureCorp.model.User;
import com.futureCorp.service.*;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class UserAccessController implements Fetcher,SizeFinder {



    @Autowired
    RegistrationServiceInterface registrationServiceInterface;

    @Autowired
    LoginServiceInterface loginServiceInterface;

    @Autowired
    FetchingDataServiceInterface fetchingDataServiceInterface;

    String view;

    @RequestMapping("/")
            public ModelAndView showHome(HttpServletRequest request)
            {   if(request.getSession().getAttribute("username")==null) {
                System.out.println(request.getSession().getAttribute("inviteTopic"));
                ModelAndView modelAndView = new ModelAndView("home");
                modelAndView.addObject("user", new User());
                modelAndView.addObject("recentShares",fetchingDataServiceInterface.fetchingRecentShares());
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

    @RequestMapping(value="/subscribeToInvite",method = RequestMethod.GET)
    public String subscribeToInvite(@RequestParam("name") String name,HttpServletRequest request)
    {

        if(request.getSession().getAttribute("username")==null) {

            request.getSession().setAttribute("inviteTopic", name);

            return "redirect:/";
        }
 return null;

    }

    @RequestMapping("/dashboard")
    public ModelAndView redirectDashboard(HttpServletRequest httpServletRequest)
    {
        if(httpServletRequest.getSession().getAttribute("inviteTopic")==null&&httpServletRequest.getSession().getAttribute("username")!=null)
        {
            ModelAndView modelAndView = new ModelAndView("dashboard");
            String username =httpServletRequest.getSession().getAttribute("username").toString();
            modelAndView.addObject("inbox",fetchingDataServiceInterface.fetchingInbox(username,0));
            User user = fetchUser(username);
            modelAndView.addObject("user",user);

            modelAndView.addObject("maxSize",fetchMaxSizeInbox(username));

            modelAndView.addObject("subNumber",fetchCountSubscriptionUser(username));
            modelAndView.addObject("topicNumber",fetchCountTopic(username));
            return modelAndView;
        }
        else
        {   String topic =httpServletRequest.getSession().getAttribute("inviteTopic").toString();
        httpServletRequest.getSession().setAttribute("inviteTopic",null);
            return new ModelAndView("redirect:/searchTopic?topicName="+topic+"&index=0");
        }
    }

    @RequestMapping("/imageFetch")
    public void imageFetcher(@RequestParam("username") String username, HttpServletResponse response)
    {

                User user = fetchUser(username);

                byte[] photo = user.getPhoto();

        try {
            response.getOutputStream().write(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
