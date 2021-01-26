package com.example.medenjak.data;

public class MessageService {
    private static MessageService instance;
    public static MessageService getInstance() {
        if(instance == null)
            instance = new MessageService();
        return instance;
    }

    private String message;
    private MessageService() {
        message = null;
    }

    public void putMessage(String msg){
        message = msg;
    }

    public String getNotification(){
        String s = message;
        message = null;
        return s;
    }
}
