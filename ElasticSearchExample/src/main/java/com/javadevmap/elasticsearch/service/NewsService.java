package com.javadevmap.elasticsearch.service;


import com.javadevmap.elasticsearch.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    void save(News bean);
    void delete(News bean);
    News findOne(Integer id);
    Iterable<News> findAll();
    Page<News> findAll(Pageable pageable);
    List<News> findByTitle(String name);
}