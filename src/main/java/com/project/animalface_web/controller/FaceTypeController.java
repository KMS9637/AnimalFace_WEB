package com.project.animalface_web.controller;

//import com.project.animalface_web.service.FaceTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/faceType")
public class FaceTypeController {
//    private final FaceTypeService faceTypeService;

    @GetMapping("/upload")
    public String faceTypeUpload(Model model) {
        return "faceType/upload";
    }

    @GetMapping("/result")
    public String faceTypeList(Model model) {
        return "faceType/result";
    }

}//Class
