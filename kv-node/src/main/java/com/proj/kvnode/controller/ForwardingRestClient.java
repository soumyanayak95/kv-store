package com.proj.kvnode.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ForwardingRestClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public void forwardPut(String leaderUrl, String key, String value) {
        String url = "http://" + leaderUrl + "/kv/" + key;
        HttpEntity<String> entity = new HttpEntity<>(value, defaultHeaders());
        System.out.println("forwardPut url: " + url);
        restTemplate.put(url, entity);
    }

    public void forwardDelete(String leaderUrl, String key) {
        String url = "http://" + leaderUrl + "/kv/" + key;
        System.out.println("forwardDelete url: " + url);
        restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(defaultHeaders()), Void.class);
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return headers;
    }
}
