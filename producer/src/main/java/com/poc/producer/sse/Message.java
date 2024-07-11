package com.poc.producer.sse;

import lombok.Data;

@Data
public class Message {
    private String text;
    private int position;

    public Message(String text, int position) {
        this.text = text;
        this.position = position;
    }

}
