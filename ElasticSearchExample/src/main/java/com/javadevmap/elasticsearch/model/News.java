package com.javadevmap.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "java-dev-map02", type = "news")
public class News {

    @Id
    @Field(index = FieldIndex.not_analyzed, store = true, type = FieldType.Integer)
    private Integer id;

    @Field(index = FieldIndex.analyzed, analyzer = "ik_max_word", store = true, searchAnalyzer = "ik_max_word", type = FieldType.String)
    private String title;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}