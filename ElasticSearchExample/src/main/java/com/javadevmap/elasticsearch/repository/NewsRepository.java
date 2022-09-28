package com.javadevmap.elasticsearch.repository;


import com.javadevmap.elasticsearch.model.News;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 接口
 */
public interface NewsRepository extends ElasticsearchRepository<News, Integer> {

    List<News> findByTitle(String name);

}