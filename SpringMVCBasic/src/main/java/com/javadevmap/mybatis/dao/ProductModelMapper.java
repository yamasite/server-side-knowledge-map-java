package com.javadevmap.mybatis.dao;

import com.javadevmap.mybatis.model.ProductModel;
import com.javadevmap.mybatis.model.ProductModelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductModelMapper {
    long countByExample(ProductModelExample example);

    int deleteByExample(ProductModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductModel record);

    int insertSelective(ProductModel record);

    List<ProductModel> selectByExample(ProductModelExample example);

    ProductModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductModel record, @Param("example") ProductModelExample example);

    int updateByExample(@Param("record") ProductModel record, @Param("example") ProductModelExample example);

    int updateByPrimaryKeySelective(ProductModel record);

    int updateByPrimaryKey(ProductModel record);
}