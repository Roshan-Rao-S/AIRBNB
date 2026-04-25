package com.airbnb.communication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.communication.dto.MessageDTO;
import com.airbnb.communication.entity.Message;
import com.airbnb.communication.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message sendMessage(MessageDTO dto, String senderEmail) {

        Message msg = new Message();
        msg.setSenderEmail(senderEmail);
        msg.setReceiverEmail(dto.getReceiverEmail());
        msg.setMessage(dto.getMessage());

        return messageRepository.save(msg);
    }

    @Override
    public List<Message> getMessages(String email) {
        return messageRepository.findBySenderEmailOrReceiverEmail(email, email);
    }
}