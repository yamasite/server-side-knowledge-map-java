package com.javadevmap.elasticjobexample.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserStatisManualMapper {
	int createTable(@Param("tableName") String tableName);  
    int existTable(String tableName);    
    void insertUserStatis(Map<Object, Object> map);
}
