package com.futureCorp.controller;

import com.futureCorp.model.Topic;
import com.futureCorp.service.FetchingDataServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {


    @Autowired
    FetchingDataServiceInterface fetchingDataServiceInterface;


    @RequestMapping(value = "/fetchDataForAdmin",method = RequestMethod.POST)
    public  @ResponseBody
    List<Object> fetchDataForAdmin(@RequestParam("className") String className ,@RequestParam("type") String type, HttpServletRequest request)
    {

        return fetchingDataServiceInterface.fetchingListForAdmin(className,type);

    }

    @RequestMapping(value = "/fetchUserForAdmin")
    public
    ModelAndView fetchUserForAdmin(HttpServletRequest request)
    {   if(request.getSession().getAttribute("username")!=null) {
        ModelAndView modelAndView = new ModelAndView("adminPage");
        modelAndView.addObject("classname", "User");
        return modelAndView;
    }
    return new ModelAndView("redirect:/");

    }

    @RequestMapping(value = "/fetchTopicForAdmin")
    public
    ModelAndView fetchTopicForAdmin(HttpServletRequest request)
    {
        if(request.getSession().getAttribute("username")!=null) {
            ModelAndView modelAndView = new ModelAndView("adminPage");
             modelAndView.addObject("classname", "Topic");

            return modelAndView;
        }
       return new ModelAndView("redirect:/");

    }

    @RequestMapping(value = "/fetchPostForAdmin")
    public
    ModelAndView fetchPostForAdmin(HttpServletRequest request)
    {   if(request.getSession().getAttribute("username")!=null) {
        ModelAndView modelAndView = new ModelAndView("adminPage");
        modelAndView.addObject("classname", "Resource");
        return modelAndView;
    }
        return new ModelAndView("redirect:/");
    }

}
