package com.futureCorp.controller;

import com.futureCorp.model.User;
import com.futureCorp.service.LoginServiceInterface;
import com.futureCorp.service.RegistrationServiceInterface;
import com.futureCorp.service.ValidatorServiceInterface;
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

    @RequestMapping("/")
            public ModelAndView showHome()
            {
                ModelAndView modelAndView = new ModelAndView("home");
                modelAndView.addObject("user",new User());

                return modelAndView;
            }

    @RequestMapping(value = "/registerUser" ,params = {"register"},method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("user") User user, BindingResult result, ModelMap modelMap, @RequestParam("photo")MultipartFile file) throws IOException {
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
        view = registrationServiceInterface.registering(user);

        return new ModelAndView(view);
    }

    @RequestMapping(value = "/loginUser",params = {"login"},method = RequestMethod.POST)
    public ModelAndView loginUser(@RequestParam("credentials") String credentials,@RequestParam("password") String password) throws IOException {

        view =loginServiceInterface.loginUser(credentials,password);

        return new ModelAndView(view);
    }



}
