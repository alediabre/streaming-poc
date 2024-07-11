package com.poc.producer.sse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/stream")
public class SseController{

    @Autowired
    SseService sseService;

    @GetMapping("/replicate")
    public SseEmitter eventStream(@RequestParam(name = "phrase", required = false) String phrase){
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> sseService.executeSseLogic(emitter,phrase));
        return emitter;
    }

}