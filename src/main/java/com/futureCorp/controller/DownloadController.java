package com.futureCorp.controller;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;


@Controller
public class DownloadController {

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadResource( HttpServletRequest request,
                                     HttpServletResponse response)
    {


        try
        {

            String fileName=request.getParameter("filePath");

         fileName=fileName.replaceAll("'","\\");
            fileName=fileName.substring(2,fileName.length()-2);
            System.out.println(fileName);

            fileName="file:///"+fileName;
            System.out.println(fileName);



            URL url = new URL(fileName);
            final URLConnection connection = url.openConnection();

            final InputStream is = connection.getInputStream();
            OutputStream outStream = response.getOutputStream();

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileName.substring(79));
            response.setHeader(headerKey,headerValue);

            int data;
            byte b[]=new byte[999999];
            while ((data = is.read(b)) != -1) {
                byte tmp[]= ArrayUtils.subarray(b, 0, data);
                outStream.write(tmp);
            }


        }
        catch (Exception ex) {
            ex.printStackTrace();

        }
    }



}








