package com.proj.kvnode.service;

import com.proj.kvnode.config.NodeConfig;
import com.proj.kvnode.model.NodeState;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;

@Service
public class KvNodeService {

    private final Map<String, String> store = new ConcurrentHashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();

    private final List<String> cluster;
    private final NodeState state;
    private final NodeConfig config;

    public KvNodeService(NodeConfig config) {
        this.config = config;
        this.state = config.getLeader().equals("leader") ? NodeState.LEADER: NodeState.FOLLOWER;
        this.cluster = config.getCluster();
    }

    public String get(String key) {
        return store.get(key);
    }

    public void put(String key, String value, boolean internal) {
        System.out.println("isInternal? : " + internal);
        if (state == NodeState.LEADER || internal) {
            store.put(key, value);
            if(!internal) replicatePut(key, value);
        } else {
            forwardToLeader(key, value);
        }
    }

    public void delete(String key, boolean internal) {
        System.out.println("isInternal? : " + internal);
        if (state == NodeState.LEADER || internal) {
            store.remove(key);
            if(!internal) replicateDelete(key);
        } else {
            forwardDeleteToLeader(key);
        }
    }

    private void replicatePut(String key, String value) {
        for (String peer : cluster) {
            if (peer.startsWith("worker")) {
                try {
                    String url = "http://" + peer + "/kv/" + key + "?internal=true";
                    restTemplate.put(url, value);
                } catch (Exception ignored) {
                }
            }
        }
    }

    private void replicateDelete(String key) {
        for (String peer : cluster) {
            System.out.println(config.getLeader());
            if (peer.startsWith("worker")) {
                try {
                    String url = "http://" + peer + "/kv/" + key + "?internal=true";
                    restTemplate.delete("url in replicateDelete:  " + url);
                } catch (Exception ignored) {
                }
            }
        }
    }

    private void forwardToLeader(String key, String value) {
        String url = "http://leader:8080/kv/" + key;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        restTemplate.put(url, new HttpEntity<>(value, headers));
    }

    private void forwardDeleteToLeader(String key) {
        String url = "http://leader:8080/kv/" + key;
        System.out.println("url in forwardDeleteToLeader:  " +url);
        restTemplate.delete(url);
    }

}