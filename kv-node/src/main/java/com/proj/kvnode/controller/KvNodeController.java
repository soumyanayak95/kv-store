package com.proj.kvnode.controller;

import com.proj.kvnode.service.KvNodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class KvNodeController {

    private final KvNodeService kvNodeService;

    public KvNodeController(KvNodeService kvNodeService) {
        this.kvNodeService = kvNodeService;
    }

    @PutMapping("/kv/{key}")
    public ResponseEntity<String> put(
            @PathVariable String key,
            @RequestBody String value,
            @RequestParam(defaultValue = "false") boolean internal) {
        kvNodeService.put(key, value, internal);
        return ResponseEntity.ok("Stored");
    }

    @GetMapping("/kv/{key}")
    public ResponseEntity<String> get(@PathVariable String key) {
        String value = kvNodeService.get(key);
        if (value == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value);
    }

    @DeleteMapping("/kv/{key}")
    public ResponseEntity<String> delete(
            @PathVariable String key,
            @RequestParam(defaultValue = "false") boolean internal) {
        kvNodeService.delete(key, internal);
        return ResponseEntity.ok("Deleted");
    }
}
