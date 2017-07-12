package com.futureCorp.controller;

import com.futureCorp.service.FetchingDataServiceInterface;
import com.futureCorp.service.TopicAddingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    TopicAddingServiceInterface topicAddingServiceInterface;

    @Autowired
    FetchingDataServiceInterface fetchingDataServiceInterface;

    @RequestMapping(value = "/addTopic",method = RequestMethod.POST)
    public  @ResponseBody String addTopic(@RequestParam("topicName")String name, @RequestParam String visibility, HttpServletRequest request)
    {
        return topicAddingServiceInterface.addingTopic(request.getSession().getAttribute("username").toString(),name,visibility);

    }

    @RequestMapping(value = "/fetchTopics",method = RequestMethod.POST)
    public  @ResponseBody List<String> fetchTopics(@RequestParam("topicLike")String nameLike)
    {

        return fetchingDataServiceInterface.fetchingList(nameLike);


    }
}
