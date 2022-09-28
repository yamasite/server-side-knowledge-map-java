package com.javadevmap.mysqlexample.mapper;

import com.javadevmap.mysqlexample.model.OrderAndProductModel;
import com.javadevmap.mysqlexample.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义mapper 文件
 */
public interface ProductManualMapper {

    Integer insertProduct(@Param("pro") Product product);

    List<OrderAndProductModel> getOrderProductList();
}
