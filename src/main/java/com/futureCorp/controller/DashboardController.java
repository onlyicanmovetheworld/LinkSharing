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

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

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

                File serverFile = new File("C:/Users/Shubham/Downloads/LinkSharing/src/main/webapp/resources/assets"
                        + File.separator +request.getSession().getAttribute("username")+"_"+createLinkedResource.getTopic()+"_"+n +file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath());
                    createLinkedResource.setLink(serverFile.getAbsolutePath());
                return resourceAddingServiceInterface.addingResource(createLinkedResource, request);
            } catch (Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {


            return "error";

        }

    }

}
