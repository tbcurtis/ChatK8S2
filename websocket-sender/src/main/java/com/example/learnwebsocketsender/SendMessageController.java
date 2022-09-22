package com.example.learnwebsocketsender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class SendMessageController {

    @PostMapping("/submit")
    public void submitMessage(@RequestBody String message) {
        //log.debug("Sending message: " + message);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8091/submit";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(message, headers);
        restTemplate.postForObject(url, requestEntity, String.class);
    }
}