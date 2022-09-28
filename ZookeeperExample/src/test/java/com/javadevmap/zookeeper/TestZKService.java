package com.javadevmap.zookeeper;

import com.javadevmap.zookeeper.dao.ZookeeperDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Zookeeper 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZookeeperExampleApplication.class)
public class TestZKService {
    @Autowired
    ZookeeperDao zookeeperDao;
    @Test
    public void testZkClient() throws Exception {
            zookeeperDao.testZkCRUD();
    }

    @Test
    public void testWathChildChange()throws Exception{
        zookeeperDao.testWathChildChange();
    }

    @Test
    public void testDataChanges()throws  Exception{
       zookeeperDao.testDataChanges();
    }

}