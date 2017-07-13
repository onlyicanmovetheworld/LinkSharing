package com.futureCorp.controller;

import com.futureCorp.model.CreateLinkedResource;
import com.futureCorp.model.ResourceType;
import com.futureCorp.model.Topic;
import com.futureCorp.service.FetchingDataServiceInterface;
import com.futureCorp.service.ResourceAddingServiceInterface;
import com.futureCorp.service.TopicAddingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@EnableWebMvc
public class DashboardController {

    @Autowired
    TopicAddingServiceInterface topicAddingServiceInterface;

    @Autowired
    FetchingDataServiceInterface fetchingDataServiceInterface;

    @Autowired
    ResourceAddingServiceInterface resourceAddingServiceInterface;

    @RequestMapping(value = "/addTopic",method = RequestMethod.POST)
    public  @ResponseBody String addTopic(@ModelAttribute Topic topic, HttpServletRequest request)
    {
        System.out.println(topic.getName());
        return topicAddingServiceInterface.addingTopic(request.getSession().getAttribute("username").toString(),topic);

    }

    @RequestMapping(value = "/fetchTopics",method = RequestMethod.POST)
    public  @ResponseBody List<Topic> fetchTopics(@RequestParam("topicLike")String nameLike)
    {


        return fetchingDataServiceInterface.fetchingList(nameLike);


    }

    @RequestMapping(value = "/loadTopic",method = RequestMethod.GET)
    public String showTopic(@RequestParam("topicName")String topicName,ModelMap modelMap)
    {
        modelMap.addAttribute("topicName",topicName);
        return "topic";


    }

    @RequestMapping(value = "/addLink",method = RequestMethod.POST)
    public @ResponseBody String addLink(@ModelAttribute CreateLinkedResource createLinkedResource, ModelMap modelMap,HttpServletRequest request)
    {
        createLinkedResource.setResourceType(ResourceType.Link);
        return resourceAddingServiceInterface.addingResource(createLinkedResource,request);


    }
}
