package com.example.noticer.controller;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController implements ApplicationContextAware {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepo;

    @Autowired
    private MessageTag messageTag;

    @Autowired
    private Message message;

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


    @RequestMapping(value = {"/greeting", "/"}, method = RequestMethod.GET)
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        viewMessageList(model);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {

        if (tag != null && !tag.isEmpty()) {
            messageTag = messageTagRepo.findByName(tag);
        }
        else {
            return "main";
        }

        if (messageTag == null) {
            messageTag = (MessageTag) context.getBean("messageTag");
            messageTag.setName(tag);
        }

        if (text != null && !text.isEmpty()) {
            message.setText(text);
            message.setTag(messageTag);
        }
        else {
            return "main";
        }
        messageTagRepo.save(messageTag);
        messageRepo.save(message);

        viewMessageList(model);

        return "main";
    }

    private void viewMessageList(Map<String, Object> model) {

        Iterable<Message> messages = messageRepo.findAll();
        Iterable<MessageTag> tags = messageTagRepo.findAll();

        model.put("tags", tags);
        model.put("messages", messages);
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        Iterable<Message> messages;
        List<MessageTag> tags = new ArrayList<>();

        if (filter.isEmpty()) {
            viewMessageList(model);
            return "main";
        }

        messageTag = messageTagRepo.findByName(filter);
        tags.add(messageTag);

        if (messageTag != null) {
            messages = messageRepo.findByTag(messageTag);
        }
        else {
            messages = messageRepo.findAll();
        }
        model.put("tags", tags);
        model.put("messages", messages);
        return "main";
    }


}