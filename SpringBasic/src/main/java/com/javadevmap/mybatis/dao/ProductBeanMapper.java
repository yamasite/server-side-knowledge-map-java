package com.javadevmap.mybatis.dao;

import com.javadevmap.mybatis.domain.ProductBean;

public interface ProductBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductBean record);

    int insertSelective(ProductBean record);

    ProductBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductBean record);

    int updateByPrimaryKey(ProductBean record);
}