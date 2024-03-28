package com.example.Eventic_backend.Model;

public class EmailRequest {

    private String recipientEmail;
    private byte[] ticketImage;

    // Getters and setters
    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public byte[] getTicketImage() {
        return ticketImage;
    }

    public void setTicketImage(byte[] ticketImage) {
        this.ticketImage = ticketImage;
    }
}