package com.airbnb.communication.dto;

public class MessageDTO {

    private String receiverEmail;
    private String message;

    public String getReceiverEmail() { return receiverEmail; }
    public void setReceiverEmail(String receiverEmail) { this.receiverEmail = receiverEmail; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}