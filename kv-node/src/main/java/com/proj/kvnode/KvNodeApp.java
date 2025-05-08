package com.proj.kvnode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/kv")
public class KvNodeApp {

    public static void main(String[] args) {
        SpringApplication.run(KvNodeApp.class, args);
    }

    @GetMapping("/test")
    public String test() {
        return "pong";
    }
}
