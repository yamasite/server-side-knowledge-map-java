package com.javadevmap.elasticsearch.service.impl;


import com.javadevmap.elasticsearch.model.News;
import com.javadevmap.elasticsearch.repository.NewsRepository;
import com.javadevmap.elasticsearch.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newRepository; 

    @Override
    public void save(News bean) {
        newRepository.save(bean);
    }

    @Override
    public void delete(News bean) {
        newRepository.delete(bean);
    }

    @Override
    public News findOne(Integer id) {
        return newRepository.findOne(id);
    }

    @Override
    public Iterable<News> findAll() {
        return newRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        return newRepository.findAll(pageable);
    }

    @Override
    public List<News> findByTitle(String name) {
        return newRepository.findByTitle(name);
    }




}