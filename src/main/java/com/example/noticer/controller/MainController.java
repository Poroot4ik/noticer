package com.example.noticer.controller;

import com.example.noticer.check.MyCheckObject;
import com.example.noticer.domain.Message;
import com.example.noticer.domain.MessageTag;
import com.example.noticer.repos.MessageRepo;
import com.example.noticer.repos.MessageTagRepo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepo;

    @Autowired
    private MessageTag messageTag;

    @GetMapping()
    public String main() {
        return "redirect:messages";
    }

}