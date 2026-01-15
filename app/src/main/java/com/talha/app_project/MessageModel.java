package com.talha.app_project;

public class MessageModel {
    public String sender;
    public String text;
    public long time;

    public MessageModel(String sender, String text, long time) {
        this.sender = sender;
        this.text = text;
        this.time = time;
    }
}
