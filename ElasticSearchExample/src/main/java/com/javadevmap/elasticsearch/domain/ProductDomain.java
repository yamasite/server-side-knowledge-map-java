package com.javadevmap.elasticsearch.domain;

import java.io.Serializable;

public class ProductDomain implements Serializable {
    private Integer id;
    private String productName;
    private Integer price;
    private String productDesc;
    private String productPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
        return "ProductDomain{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", productDesc='" + productDesc + '\'' +
                ", productPic='" + productPic + '\'' +
                '}';
    }
}