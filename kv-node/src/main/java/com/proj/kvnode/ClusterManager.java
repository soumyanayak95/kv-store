package com.proj.kvnode;

import com.proj.kvnode.config.NodeConfig;
import org.springframework.stereotype.Component;

@Component
public class ClusterManager {
    private final NodeConfig config;

    public ClusterManager(NodeConfig config) {
        this.config = config;
    }

    public boolean isLeader() {
        return config.getLeader().equals("leader");
    }

    public String getLeaderAddress() {
        return config.getLeader();
    }
}
