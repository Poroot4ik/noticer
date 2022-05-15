package com.example.noticer.controller;

import com.example.noticer.domain.MessageTag;
import com.example.noticer.repos.MessageRepo;
import com.example.noticer.repos.MessageTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepo;

    @Autowired
    private MessageTag messageTag;

    @RequestMapping(value = {"/","/main"}, method = RequestMethod.GET)
    public String main() {
        return "redirect:messages";
    }

}