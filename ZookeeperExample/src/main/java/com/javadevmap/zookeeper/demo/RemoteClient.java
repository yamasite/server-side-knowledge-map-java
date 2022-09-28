package com.javadevmap.zookeeper.demo;

import com.javadevmap.zookeeper.model.ZookeeperModel;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务调用方
 */
@Service
public class RemoteClient {
    private  List<String> serList = null;
    private ZkClient zkClient = null;

    @Autowired
    ZookeeperModel zookeeperModel;
    @PostConstruct
    public void initMethod() {
        // 构建 zkclient
        zkClient = new ZkClient(zookeeperModel.getAddress(),zookeeperModel.getConnectionTimeout());
    }

    public void subscribeChildChanges4Servers() throws Exception {

        zkClient.subscribeChildChanges(Constants.parentZnodePath, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(String.format("-->触发到监听事件:parentPath %s, 其所有子节点: %s", parentPath, currentChilds));
                updateServerList(currentChilds);
                System.out.println("<<<<<<<<<<<<<<<<<<<<监听结束");
            }
        });
    }

    /**
     * 主动获取 server list 数据
     */
    public void getServerList() throws Exception {

        System.out.println(">>>>>>>>>>>>>>>>>>");
        // 先创建出父节点,用于被调用方监听
        if (!zkClient.exists(Constants.parentZnodePath)) {
            zkClient.create(Constants.parentZnodePath, null,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        List<String> children = zkClient.getChildren(Constants.parentZnodePath);
        if (null == children || children.size() == 0) {
            return;
        }
        updateServerList(children);
        System.out.println("<<<<<<<<<<<<<<<<<<<<");
    }

    private void updateServerList(List<String> currentChilds) {
        ArrayList<String> serverList = new ArrayList<String>();
        for (String child : currentChilds) {
            String path = Constants.parentZnodePath + "/" + child;
            String data = zkClient.readData(path);
            serverList.add(new String(data));
        }
        serList = serverList;
        // 打印更新后的服务器列表信息
        for (String server : serverList) {
            System.out.println(String.format("服务数据:%s",server));
        }
    }

}