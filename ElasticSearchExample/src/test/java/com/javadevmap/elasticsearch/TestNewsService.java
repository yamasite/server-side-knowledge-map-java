package com.javadevmap.elasticsearch;


import com.javadevmap.elasticsearch.model.News;
import com.javadevmap.elasticsearch.service.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Brand 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchExampleApplication.class)
public class TestNewsService {

    @Autowired
    private NewsService brandService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 通过 ElasticsearchTemplate 创建索引和添加映射
     */
    @Before
    public void createIndex() {
        elasticsearchTemplate.deleteIndex(News.class);
        elasticsearchTemplate.createIndex(News.class);
        elasticsearchTemplate.putMapping(News.class);
        elasticsearchTemplate.refresh(News.class);
    }


    /**
     * 添加100条测试数据的方法
     */
    @Test
    public void testSearch() {
//
        List<String> list4Title = new ArrayList<>();
        list4Title.add("航拍西班牙田野艳丽美景 色彩柔美如缎带");
        list4Title.add("无人机航拍：“天空之眼”瞰博鳌");
        list4Title.add("无人机航拍广州黄浦公园 看羊城最美时");
        list4Title.add("心形爱晚湖 爱上天津大学的航拍在这儿");
        list4Title.add("首届海峡无人机航拍创作大赛启动");

        for (int i = 1; i <= 100; i++) {
            News bean = new News();
            bean.setId(i);
            bean.setTitle(list4Title.get(new Random().nextInt(5)));

            brandService.save(bean);
        }

        List<News> list = brandService.findByTitle("无人机 航拍");

        System.out.println("总记录数：" + list.size());
        for (News bean : list) {
            System.out.println(bean);
        }


    }


}