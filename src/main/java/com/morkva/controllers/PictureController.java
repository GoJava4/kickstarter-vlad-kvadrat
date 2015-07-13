package com.morkva.controllers;

import com.morkva.entities.Picture;
import com.morkva.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public void showImage(@PathVariable Integer imageId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Picture picture = pictureService.getById(imageId);
        System.out.println("PICTURE ID = " + picture.getId());
        System.out.println("PICTURE DATA = " + Arrays.toString(picture.getImage()));
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(picture.getImage());
        response.getOutputStream().close();
    }
}
