package com.javadevmap.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "java-dev-map", type = "product")
public class Product {

    @Id
    private String id;
    private String productName;
    private Double price;
    private String brand;
    private String productDesc;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String productPic;

    public Product() {
    }

    public Product(String id, String productName, Double price, String brand) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
    }



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productPic='" + productPic + '\'' +
                '}';
    }
}