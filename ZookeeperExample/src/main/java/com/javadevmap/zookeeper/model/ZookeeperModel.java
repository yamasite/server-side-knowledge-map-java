package com.javadevmap.zookeeper.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="zk")
public class ZookeeperModel {
    /** 服务器地址列表*/
    private String address;
    /**毫秒*/
    private int  connectionTimeout;

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getConnectionTimeout() {
        return connectionTimeout;
    }
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    @Override
    public String toString() {
        return "ZookeeperModel{" +
                "address='" + address + '\'' +
                ", connectionTimeout=" + connectionTimeout +
                '}';
    }
}
