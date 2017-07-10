package com.futureCorp.controller;

import com.futureCorp.model.User;
import com.futureCorp.service.RegistrationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class UserController {



    @Autowired
    RegistrationInterface registrationInterface;


    @RequestMapping("/")
            public ModelAndView showHome()
            {
                return new ModelAndView("home","user",new User());
            }

    @RequestMapping("/registerUser")
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
        String view = registrationInterface.registering(user);

        return new ModelAndView(view);
    }

}
