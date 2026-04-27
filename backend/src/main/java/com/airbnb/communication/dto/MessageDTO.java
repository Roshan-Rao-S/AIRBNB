package com.airbnb.communication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MessageDTO {

    @Email(message = "Invalid receiver email")
    @NotBlank(message = "Receiver email is required")
    private String receiverEmail;

    @NotBlank(message = "Message is required")
    private String message;

    public String getReceiverEmail() { return receiverEmail; }
    public void setReceiverEmail(String receiverEmail) { this.receiverEmail = receiverEmail; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
