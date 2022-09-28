package com.javadevmap.zookeeper.dao.impl;

import com.javadevmap.zookeeper.dao.ZookeeperDao;
import com.javadevmap.zookeeper.model.ZookeeperModel;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * zookeeper
 */
@Component
public class ZookeeperDaoImpl  implements ZookeeperDao{


    @Autowired
    private ZookeeperModel zookeeperModel;


    public void testZkCRUD(){

        String address = zookeeperModel.getAddress();
        ZkClient zkClient = new ZkClient(address, zookeeperModel.getConnectionTimeout());

        String pathParant="/persis";
        String child01Path=pathParant+"/child01";
        String child02Path=pathParant+"/child02";
        String ephemeral="/ephemeral";
        //创建临时有序节点
        String ephemeralSequential = zkClient.createEphemeralSequential("/ephemeralSequential-", "epheSequentialDatas");
        String data4pathEph= zkClient.readData(ephemeralSequential);
        System.out.println(String.format("临时有序节点路径为：%s,数据为：%s",ephemeralSequential,data4pathEph));
        ephemeralSequential = zkClient.createEphemeralSequential("/ephemeralSequential-", "epheSequentialDatas");
        data4pathEph= zkClient.readData(ephemeralSequential);
        System.out.println(String.format("临时有序节点路径为：%s,数据为：%s",ephemeralSequential,data4pathEph));
        zkClient.createPersistent(child01Path, true);
        boolean exists= zkClient.exists(child01Path);
        if(exists){
            System.out.println(String.format("节点:%s, 级联创建成功",child01Path));
        }
        zkClient.createEphemeral(ephemeral);
        exists= zkClient.exists(ephemeral);
        if(exists){
            System.out.println(String.format("临时节点:%s, 创建成功",ephemeral));
        }
        //delete node
        zkClient.delete(ephemeral);
        zkClient.deleteRecursive(pathParant);
        exists= zkClient.exists(pathParant);
        System.out.println(String.format("节点%s,是否存在：%s",pathParant,exists));


        //2. 创建节点以及子节点
        zkClient.createPersistent(pathParant, "rootDatas");
        String data = zkClient.readData(pathParant);
        System.out.println(String.format("数据节点%s,数据为：%s",pathParant,data));
        zkClient.createPersistent(child01Path, "datas of child01");
        zkClient.createPersistent(child02Path, "datas of child02");

        List<String> list = zkClient.getChildren("/persis");
        for (String p : list) {
            System.out.println("child path is "+p);
            String path = pathParant +"/"+ p;
            data = zkClient.readData(path);
            System.out.println(String.format("数据节点:%s,数据:%s",path,data));
        }

        //3. 更新节点数据
        boolean isExists=zkClient.exists(child01Path);
        System.out.println(String.format("数据节点%s,是否存在：%s",child01Path,isExists));
        if(isExists){
            data=zkClient.readData(child01Path).toString();
            System.out.println(String.format("数据节点%s,数据为：%s",child01Path,data));
            zkClient.writeData(child01Path, "update datas");
            data=zkClient.readData(child01Path).toString();
            System.out.println(String.format("数据节点%s,数据为：%s",child01Path,data));
        }
        //递归删除节点
        zkClient.deleteRecursive(pathParant);
        exists = zkClient.exists(pathParant);
        System.out.println(String.format("节点%s,是否存在：%s",pathParant,exists));
    }

    public void testWathChildChange(){
        try{
            String address = zookeeperModel.getAddress();
            ZkClient zkClient = new ZkClient(new ZkConnection(address), zookeeperModel.getConnectionTimeout());

            String parentPath="/persist";
            boolean exists = zkClient.exists(parentPath);
            if(exists){
                zkClient.deleteRecursive(parentPath);
            }

            zkClient.subscribeChildChanges("/persist", new IZkChildListener() {
                @Override
                public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                    System.out.println(String.format("触发到监听事件:parentPath %s, 其所有子节点: %s",parentPath,currentChilds));
                }
            });

            zkClient.createPersistent(parentPath);
            System.out.println("----> create path: "+parentPath);
            Thread.sleep(1000);
            String child01Path=parentPath+"/child01";
            zkClient.createPersistent(child01Path, "child01 datas");
            System.out.println("----> create path: "+child01Path);
            Thread.sleep(1000);

            String child02path=parentPath+"/child02";
            zkClient.createPersistent(child02path, "child02 datas");
            System.out.println("----> create  path: "+child02path);
            Thread.sleep(1000);

            zkClient.writeData(child02path,"update child02 datas");
            System.out.println("----> update path:"+child02path);
            Thread.sleep(1000);

            zkClient.delete(child02path);
            System.out.println("----> delete path:"+child02path);
            Thread.sleep(1000);

            zkClient.deleteRecursive("/persist");
            System.out.println("----> delete path:/persist");
            Thread.sleep(4*1000);
            System.out.println("done");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void testDataChanges(){

        try{
            String address = zookeeperModel.getAddress();
            ZkClient zkClient = new ZkClient(new ZkConnection(address), zookeeperModel.getConnectionTimeout());
            String path="/persist";
            if(zkClient.exists(path)){
                zkClient.deleteRecursive(path);
            }
            zkClient.createPersistent(path, "datas");
            //对父节点添加监听子节点中数据的变化
            zkClient.subscribeDataChanges("/persist", new IZkDataListener() {
                @Override
                public void handleDataDeleted(String path) throws Exception {
                    System.out.println("删除节点为:" + path);
                }

                @Override
                public void handleDataChange(String path, Object data) throws Exception {
                    System.out.println(String.format("变更节点为:%s, 变更内容为:%s" ,path,data));
                }
            });

            Thread.sleep(1000);
            zkClient.writeData(path, "update datas 01");
            System.out.println("----> write datas:"+path);
            Thread.sleep(1000);

            String child02path=path+"/child02";
            zkClient.createPersistent(child02path, "child02 datas");
            System.out.println("----> create child path:"+child02path);
            Thread.sleep(1000);

            zkClient.writeData(child02path, "update child datas");
            System.out.println("----> update child datas:"+child02path);
            Thread.sleep(1000);

            zkClient.writeData(path, "update datas 02");
            System.out.println("----> update datas:"+path);
            Thread.sleep(1000);

            zkClient.deleteRecursive(path);
            System.out.println("----> delete  path:"+path);
            Thread.sleep(2*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
