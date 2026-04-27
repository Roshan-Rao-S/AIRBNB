package com.airbnb.communication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.airbnb.communication.dto.MessageDTO;
import com.airbnb.communication.entity.Message;
import com.airbnb.communication.service.MessageService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Message sendMessage(@jakarta.validation.Valid @RequestBody MessageDTO dto,
                               HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return messageService.sendMessage(dto, email);
    }

    @GetMapping
    public List<Message> getMessages(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return messageService.getMessages(email);
    }
}
