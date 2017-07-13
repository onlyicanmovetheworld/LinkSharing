package com.futureCorp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DownloadController {

    @RequestMapping(value = "/download",method= RequestMethod.GET)
    public void downloadPDFResource( HttpServletRequest request,
                                     HttpServletResponse response,
                                     @ModelAttribute("filePath") String filePath)
    {
        //If user is not authorized - he should be thrown out from here itself

        //Authorized user will download the file

        Path file = Paths.get(filePath);
        if (Files.exists(file))
        {


            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
