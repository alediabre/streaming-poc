package com.poc.consumer.service;

import com.poc.producer.sse.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Service
public class SseClientService {

    private final WebClient webClient;

    @Autowired
    public SseClientService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("https://ycw6a77hydrmi4qpe4xvnv6ffy0axgft.lambda-url.us-east-1.on.aws").build();
    }

    public Flux<String> getServerSentEvents(){
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("body","{\"question\":\"What is malaria?\", \"context\":[{\"id\":\"30669301\", \"content\":\"Malaria is a mosquito-borne disease caused by parasites of the genus Plasmodium (P [...].\"},{\"id\":\"20205131\", \"content\":\"Malaria is a parasitic disease transmitted by the bites of Anopheles mosquitoes. It is a common and life-threatening disease in tropical and subtropical countries. There is no vaccination available, and prevention is based on a combination of chemoprophylaxis and avoidance of mosquito bites.\"},{\"id\":\"12364370\", \"content\":\"Malaria is among the oldest of diseases. In one form or another, it has infected and affected our ancestors since long before the origin of the human line. During our recent evolution, its influence has probably been greater than that of any other infectious agent. Here we attempt to trace the forms and impacts of malaria from a distant past through historical times to the present. In the last sections, we review the current burdens of malaria across the world and discuss present-day approaches to its management. Only by following, or attempting to follow, malaria throughout its evolution and history can we understand its character and so be better prepared for our future management of this ancient ill.\"},{\"id\":\"12216239\", \"content\":\"Malaria is a common and dangerous tropical disease, caused by a parasite transmitted to humans through the bite of the anopheles mosquito. This article reviews the prevention, symptoms, diagnosis and treatment of malaria, and outlines key aspects of pre-travel advice.\"}]}");
        bodyMap.put("resource","/chat");

        return webClient.post()
                .uri("/events")
                .bodyValue(bodyMap)
                .retrieve()
                .bodyToFlux(String.class);
    }

}
