package com.poc.producer.service;

import com.poc.producer.sse.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
public class SseService {

    @Autowired
    @Qualifier("nullMessage")
    private Message nullMessage;


    public void executeSseLogic(SseEmitter emitter, String phrase){
        try{
            if (phrase==null){
                SseEmitter.SseEventBuilder event = createEvent(String.valueOf(0), nullMessage);
                emitter.send(event);
            } else{
                for (int i = 0; i<phrase.length(); i++){
                    Message message = new Message(String.valueOf(phrase.charAt(i)), i);
                    SseEmitter.SseEventBuilder event = createEvent(String.valueOf(i), message);
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            }
            emitter.complete();
        } catch (IOException | InterruptedException e){
            emitter.completeWithError(e);
        }
    }

    private SseEmitter.SseEventBuilder createEvent(String id, Object data){
        return SseEmitter.event().id(id).data(data);
    }
}