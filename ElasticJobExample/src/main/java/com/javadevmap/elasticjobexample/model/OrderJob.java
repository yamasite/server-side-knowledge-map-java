package com.javadevmap.elasticjobexample.model;

import java.util.Date;

public class OrderJob {
    private Long id;

    private Double price;

    private Long userid;

    private Boolean status;

    private Date createtime;

    private Boolean statis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getStatis() {
        return statis;
    }

    public void setStatis(Boolean statis) {
        this.statis = statis;
    }
}