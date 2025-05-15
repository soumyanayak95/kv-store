package com.proj.kvnode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "kvnode")
public class NodeConfig {
    private String name;
    private String id;
    private String self;
    private int port;
    private String leader;
    private List<String> cluster;


    public String getLeader() { return leader; }

    public List<String> getCluster() { return cluster; }

    public String getSelfAddress() {
        return self;
    }

    public void setCluster(List<String> cluster) {
        this.cluster = cluster;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
