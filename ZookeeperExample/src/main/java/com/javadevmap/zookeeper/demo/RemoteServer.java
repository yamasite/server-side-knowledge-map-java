package com.javadevmap.zookeeper.demo;

import com.javadevmap.zookeeper.model.ZookeeperModel;
import net.sf.json.JSONObject;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 服务提供方
 */
@Service
public class RemoteServer {
	private ZkClient zkClient = null;

	@Autowired
	ZookeeperModel zookeeperModel;

	@PostConstruct
	public void initMethod() {
		// 创建zkclient
		zkClient = new ZkClient(zookeeperModel.getAddress(), zookeeperModel.getConnectionTimeout());
	}
	/**
	 * 在固定节点下面创建临时有序节点
	 */
	public void registerServer(String serverName,String address, String port) throws Exception {
		// 先创建出父节点,用于被调用方监听
		if (!zkClient.exists(Constants.parentZnodePath)) {
			zkClient.create(Constants.parentZnodePath, null,
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}

		Map<String,String> datas=new HashMap<>();
		datas.put("serverName",serverName);
		datas.put("host",address);
		datas.put("port",port);
		// 服务权重  0-100
		datas.put("weight",new Random().nextInt(100)+"");
		JSONObject jsonObject = JSONObject.fromObject(datas);
		String result = jsonObject.toString();
		//在指定路径下面创建临时节点
		String pathName = zkClient.create(
				Constants.parentZnodePath + "/" + serverName + "-",
				result, Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("[Server]>>>>>>" + serverName + " is register success! pathName = "+pathName);
	}

}
