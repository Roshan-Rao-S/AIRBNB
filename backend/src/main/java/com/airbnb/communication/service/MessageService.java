package com.airbnb.communication.service;

import java.util.List;

import com.airbnb.communication.dto.MessageDTO;
import com.airbnb.communication.entity.Message;

public interface MessageService {

    Message sendMessage(MessageDTO dto, String senderEmail);

    List<Message> getMessages(String email);
}