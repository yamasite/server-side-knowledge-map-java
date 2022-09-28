package com.javadevmap.mongodbexample.model;

import org.springframework.data.annotation.Id;

/**
 * 商品
 */
public class Product {
    @Id
    private Integer id;
    private String name;
    private int price;
    /** 产品页面对应的商品详情 */
    private String htmlDetail;

    public Product() {
    }

    public Product(Integer id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getHtmlDetail() {
        return htmlDetail;
    }

    public void setHtmlDetail(String htmlDetail) {
        this.htmlDetail = htmlDetail;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}