package com.example.noticer.controller;

import com.example.noticer.domain.Message;
import com.example.noticer.domain.MessageTag;
import com.example.noticer.repos.MessageRepo;
import com.example.noticer.repos.MessageTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepo;


    @RequestMapping(value = {"/greeting", "/"}, method = RequestMethod.GET)
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> seatList = messageRepo.findAll();
        Iterable<MessageTag> rowList = messageTagRepo.findAll();

        model.put("rows", rowList);
        model.put("seats", seatList);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        MessageTag messageTag;
        Message message;

        if (tag != null && !tag.isEmpty()) {
            messageTag = messageTagRepo.findByName(tag);
        }
        else {
            return "main";
        }

        if (messageTag == null) {
            messageTag = new MessageTag(tag);
        }

        if (text != null && !text.isEmpty()) {
            message = new Message(text, messageTag);
        }
        else {
            return "main";
        }
        messageTagRepo.save(messageTag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        MessageTag messageTag;
        messageTag = messageTagRepo.findByName(filter);

        Iterable<Message> messages;
        if (messageTag != null) {
            messages = messageRepo.findByTag(messageTag);
        }
        else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);
        return "main";
    }

}