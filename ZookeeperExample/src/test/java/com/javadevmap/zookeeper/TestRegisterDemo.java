package com.javadevmap.zookeeper;

import com.javadevmap.zookeeper.demo.RemoteClient;
import com.javadevmap.zookeeper.demo.RemoteServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * 服务注册
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZookeeperExampleApplication.class)

public class TestRegisterDemo {

    @Autowired
    RemoteServer server;
    @Autowired
    RemoteClient client;

    @Test
    public void startRemoteServer() throws Exception{

            String  serverName="orderServer";
            String  address="192.168.1."+new Random().nextInt(255);// 模拟ip
            int port=1000+new Random().nextInt(599);//端口在1000-1599之间
            server.registerServer(serverName,address, port+"");//注册服务
            System.out.println("[Server]>>>>>>" + serverName + " is Online ......");
            Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void startRemoteClient()throws Exception{

        client.getServerList();
        client.subscribeChildChanges4Servers();
        Thread.sleep(Long.MAX_VALUE);

    }

}
