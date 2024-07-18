package com.poc.consumer.sse.controller;

import com.poc.consumer.service.SseClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/consumer")
public class SseClientController {

    private final SseClientService sseClientService;

    @Autowired
    public SseClientController(SseClientService sseClientService){this.sseClientService = sseClientService;}

    @GetMapping("/receive-flux")
    public Flux<String> startSse() {
        return sseClientService.getServerSentEvents();
    }

    @GetMapping("/receive")
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter();
        Flux<String> flux = sseClientService.getServerSentEvents();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            flux.doOnNext(data -> {
                        try {
                            emitter.send(SseEmitter.event().data(data));
                        } catch (IOException e) {
                            emitter.completeWithError(e);
                        }
                    }).doOnComplete(emitter::complete)
                    .doOnError(emitter::completeWithError)
                    .subscribe();
        });

        return emitter;
    }
}
