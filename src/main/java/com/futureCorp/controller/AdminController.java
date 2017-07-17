package com.futureCorp.controller;

import com.futureCorp.model.Topic;
import com.futureCorp.service.FetchingDataServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {


    @Autowired
    FetchingDataServiceInterface fetchingDataServiceInterface;


    @RequestMapping(value = "/fetchDataForAdmin",method = RequestMethod.POST)
    public  @ResponseBody
    List<Object> addTopic(@RequestParam("className") String className ,@RequestParam("type") String type, HttpServletRequest request)
    {

        return fetchingDataServiceInterface.fetchingListForAdmin(className,type);

    }


}
