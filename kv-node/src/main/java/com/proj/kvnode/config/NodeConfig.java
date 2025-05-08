package com.proj.kvnode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "kvnode")
public class NodeConfig {
    private String id;
    private String host;
    private int port;
    private String leader;
    private List<String> cluster;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getLeader() { return leader; }
    public void setLeader(String leader) { this.leader = leader; }

    public List<String> getCluster() { return cluster; }
    public void setCluster(List<String> cluster) { this.cluster = cluster; }

    public String getSelfAddress() {
        return host + ":" + port;
    }
}
