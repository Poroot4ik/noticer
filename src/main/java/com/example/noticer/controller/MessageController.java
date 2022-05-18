package com.example.noticer.controller;

import com.example.noticer.domain.Message;
import com.example.noticer.service.MessageService;
import com.example.noticer.service.MessageTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messagesService;
    private final MessageTagService messageTagService;

    @Autowired
    public MessageController(MessageService messagesService, MessageTagService messageTagService) {
        this.messagesService = messagesService;
        this.messageTagService = messageTagService;
    }

    @GetMapping()
    public String index(Map<String, Object> model) {
/*        model.put("tags", messagesTagRepo.findAll());*/
        model.put("tags", messageTagService.findAll());
        return "messages/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Map<String, Object> model) {
/*        model.put("message", messagesRepo.findById(id).get());*/
        model.put("message", messagesService.findOne(id));
        return "messages/show";
    }

    @GetMapping("/new")
    public String newMessage(@ModelAttribute(value="message") Message message, Map<String, Object> model) {
        return "messages/new";
    }

    @PostMapping()
    public String create(@ModelAttribute(value="message") @Valid Message message,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "messages/new";

        /*if (!message.getTagName().isEmpty()){
            MessageTag tag = messagesTagRepo.findByTagName(message.getTagName());
            if (tag != null) {
                message.setTag(tag);
            }
        }

            messagesTagRepo.save(message.getTag());
            messagesRepo.save(message);*/
        messagesService.save(message);

        return "redirect:/messages";
    }

    @GetMapping("/{id}/edit")
    public String edit(Map<String, Object> model, @PathVariable("id") Integer id) {
       /* model.put("message", messagesRepo.findById(id).get());*/
         model.put("message", messagesService.findOne(id));
        return "messages/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute(value="message") @Valid Message message,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "messages/edit";

        messagesService.update(message);
/*        if (!message.getTagName().isEmpty()){
            MessageTag tag = messagesTagRepo.findByTagName(message.getTagName());
            if (tag != null) {
                message.setTag(tag);
            }
        }

            messagesTagRepo.save(message.getTag());
            messagesRepo.save(message);*/

        return "redirect:/messages";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        messagesService.delete(id);
        return "redirect:/messages";
    }

   /* @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        Iterable<Message> messages;
        List<MessageTag> tags = new ArrayList<>();

        if (filter.isEmpty()) {
*//*            viewMessageList(model);*//*
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
    }*/

}
