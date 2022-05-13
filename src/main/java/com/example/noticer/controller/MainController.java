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
public class MainController implements ApplicationContextAware {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepo;

    @Autowired
    private MessageTag messageTag;


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
    public String add(@ModelAttribute(value="message") Message message,@ModelAttribute(value="messageTag") MessageTag messageTag,Map<String, Object> model) {

        if (!messageTag.getTagName().isEmpty()){
            MessageTag tag = messageTagRepo.findByTagName(messageTag.getTagName());
            if (tag != null) {
                messageTag = tag;
            }
        }

        String text = message.getText();
        if (text != null && !text.isEmpty()) {
            if (!messageTag.getTagName().isEmpty()) {
                message.setTag(messageTag);
            }
        }

        if (!MyCheckObject.isObjectFieldsNull(message)) {
            messageTagRepo.save(messageTag);
            messageRepo.save(message);
        }

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

        messageTag = messageTagRepo.findByTagName(filter);
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

    @DeleteMapping("/main/{id}")
    public String deleteMessage(@PathVariable Integer id, Map<String, Object> model) {
        Optional<Message> message = messageRepo.findById(id);
        MessageTag messageTag = message.get().getTag();
        messageRepo.deleteById(id);
        List<Message> messages =messageRepo.findByTag(messageTag);
        if (messages.isEmpty()) {
            messageTagRepo.deleteById(messageTag.getId());
        }
        viewMessageList(model);

        return "redirect:/main";
    }

}