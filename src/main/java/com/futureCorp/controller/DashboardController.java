package com.futureCorp.controller;

import com.futureCorp.dao.UserActionDaoInterface;
import com.futureCorp.holder.Fetcher;
import com.futureCorp.holder.SizeFinder;
import com.futureCorp.model.*;
import com.futureCorp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

@Controller
@EnableWebMvc
public class DashboardController implements SizeFinder,Fetcher {

    @Autowired
    TopicAddingServiceInterface topicAddingServiceInterface;

    @Autowired
    FetchingDataServiceInterface fetchingDataServiceInterface;

    @Autowired
    ResourceAddingServiceInterface resourceAddingServiceInterface;

    @Autowired
    SendInviteInterface sendInviteInterface;

    @Autowired
    UserActionServiceInterface userActionServiceInterface;


    @RequestMapping(value = "/addTopic",method = RequestMethod.POST)
    public  @ResponseBody String addTopic(@ModelAttribute Topic topic, HttpServletRequest request)
    {

        return topicAddingServiceInterface.addingTopic(request.getSession().getAttribute("username").toString(),topic);

    }

    @RequestMapping(value = "/fetchTopics",method = RequestMethod.POST)
    public  @ResponseBody List<Topic> fetchTopics(@RequestParam("topicLike")String nameLike)
    {


        return fetchingDataServiceInterface.fetchingList(nameLike);


    }

    @RequestMapping(value = "/fetchSubscribedTopics",method = RequestMethod.POST)
    public  @ResponseBody List<Topic> fetchSubscribedTopics(@RequestParam("topicLike")String nameLike,HttpServletRequest request)
    {


        return fetchingDataServiceInterface.fetchingSubscribedList(nameLike,request);


    }


    @RequestMapping(value = "/searchTopic",method = RequestMethod.GET)
    public ModelAndView showTopic(@RequestParam("topicName")String topicName,@RequestParam("index")String index, ModelMap modelMap)
    {
        ModelAndView modelAndView = new ModelAndView("topicShow");
        if(index.equalsIgnoreCase("0"))
        {
            modelAndView.addObject("maxSize",fetchMaxSizeTopicResource(topicName));

        }
        List<Resource> resources = fetchingDataServiceInterface.fetchingList(topicName, Integer.parseInt(index));
       Topic topic = fetchTopic(topicName);
        modelAndView.addObject("subNumber",fetchCountSubscriptionTopic(topicName));
        modelAndView.addObject("postNumber",fetchCountPost(topicName));

        modelAndView.addObject("resourceList",resources);
        modelAndView.addObject("topic",topic);
        System.out.println(resources.size());

        return modelAndView;


    }

   @RequestMapping(value = "/sendInvite",method = RequestMethod.POST)
    public @ResponseBody String sendInvite(@RequestParam("email") String email,@RequestParam("topic") String topic,HttpServletRequest request)
    {


        String who = request.getSession().getAttribute("username").toString();
        return sendInviteInterface.sendingInviteEmail(email,topic,who)+"";
    }





    @RequestMapping(value = "/searchAjaxTopic", method=RequestMethod.POST)
    public @ResponseBody List<Resource> showTopic(@RequestParam("topicName")String topicName,@RequestParam("index")String index)
    {

        List resources = fetchingDataServiceInterface.fetchingList(topicName, Integer.parseInt(index));

                return resources;

    }

    @RequestMapping(value = "/fetchAjaxInbox", method=RequestMethod.POST)
    public @ResponseBody List<ReadingItem> showInbox(@RequestParam("index")String index,HttpServletRequest request,ModelAndView modelAndView)
    {

        List<ReadingItem> inbox = fetchingDataServiceInterface.fetchingInbox(request.getSession().getAttribute("username").toString(), Integer.parseInt(index));

        return inbox;

    }


    @RequestMapping(value = "/addLink",method = RequestMethod.POST)
    public @ResponseBody String addLink(@ModelAttribute CreateLinkedResource createLinkedResource1, ModelMap modelMap,HttpServletRequest request)
    {
        createLinkedResource1.setResourceType(ResourceType.Link);
        return resourceAddingServiceInterface.addingResource(createLinkedResource1,request);


    }

    @RequestMapping(value = "/addDocument",method = RequestMethod.POST)

    public @ResponseBody String addDocument(@ModelAttribute CreateLinkedResource createLinkedResource,
                                            @RequestParam("file") MultipartFile file, ModelMap modelMap, HttpServletRequest request)
    {

        createLinkedResource.setResourceType(ResourceType.Document);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file


                // Create the file on server
                Random rand = new Random();

                int  n = rand.nextInt(10000) + 1;
                String path ="C:/Users/Shubham/Downloads/LinkSharing/src/main/webapp/resources/assets/"
                        +request.getSession().getAttribute("username")+createLinkedResource.getTopic()+n +file.getOriginalFilename();
                path=path.replaceAll(" ","_");
                File serverFile = new File(path);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath());
                    createLinkedResource.setLink(path);
                return resourceAddingServiceInterface.addingResource(createLinkedResource, request);
            } catch (Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {


            return "error";

        }

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return ("redirect:/");
    }


    @RequestMapping("/markAsRead")
    public @ResponseBody String markAsRead(@RequestParam("id") String id)
    {

        return userActionServiceInterface.markingAsRead(Integer.parseInt(id))+"";
    }

    @RequestMapping("/updateProfile")
    public ModelAndView updateProfile(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView("updateProfile");
       modelAndView.addObject("userData",fetchUser(request.getSession().getAttribute("username").toString()));
       return modelAndView;
    }

}
