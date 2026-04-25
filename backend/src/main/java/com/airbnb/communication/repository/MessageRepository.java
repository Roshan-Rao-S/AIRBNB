package com.airbnb.communication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.airbnb.communication.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findBySenderEmailOrReceiverEmail(String sender, String receiver);
}