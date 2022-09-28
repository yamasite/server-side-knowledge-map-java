package com.javadevmap.elasticjobexample.model.mapper;

import com.javadevmap.elasticjobexample.model.OrderJob;
import com.javadevmap.elasticjobexample.model.OrderJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OrderJobMapper {
    int countByExample(OrderJobExample example);

    int deleteByExample(OrderJobExample example);

    @Delete({
        "delete from order_job",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into order_job (id, price, ",
        "userid, status, createtime, ",
        "statis)",
        "values (#{id,jdbcType=BIGINT}, #{price,jdbcType=DOUBLE}, ",
        "#{userid,jdbcType=BIGINT}, #{status,jdbcType=BIT}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{statis,jdbcType=BIT})"
    })
    int insert(OrderJob record);

    int insertSelective(OrderJob record);

    List<OrderJob> selectByExample(OrderJobExample example);

    @Select({
        "select",
        "id, price, userid, status, createtime, statis",
        "from order_job",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    OrderJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderJob record, @Param("example") OrderJobExample example);

    int updateByExample(@Param("record") OrderJob record, @Param("example") OrderJobExample example);

    int updateByPrimaryKeySelective(OrderJob record);

    @Update({
        "update order_job",
        "set price = #{price,jdbcType=DOUBLE},",
          "userid = #{userid,jdbcType=BIGINT},",
          "status = #{status,jdbcType=BIT},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "statis = #{statis,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(OrderJob record);
}