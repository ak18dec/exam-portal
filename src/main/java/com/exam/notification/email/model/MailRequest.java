package com.exam.notification.email.model;

public class MailRequest {

    private static final String SENDER = "Exam Portal Support <mail_id>";
    private String recipient;
    private String sender = SENDER;
    private String subject;
    private String name;

    public MailRequest() {
    }

    public MailRequest(String recipient, String sender, String subject, String name) {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
        this.name = name;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        if(sender != null && !sender.isEmpty()) {
            this.sender = sender;
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MailRequest{" +
                "recipient='" + recipient + '\'' +
                ", sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
