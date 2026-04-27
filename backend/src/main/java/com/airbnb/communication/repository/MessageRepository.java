package com.airbnb.communication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airbnb.communication.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderEmailOrReceiverEmail(String sender, String receiver);
}
