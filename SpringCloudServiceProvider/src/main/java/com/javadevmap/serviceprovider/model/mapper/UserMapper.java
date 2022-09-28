package com.javadevmap.serviceprovider.model.mapper;

import com.javadevmap.serviceprovider.model.User;
import com.javadevmap.serviceprovider.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (id, address, ",
        "age, name, phone_num)",
        "values (#{id,jdbcType=INTEGER}, #{address,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{phoneNum,jdbcType=VARCHAR})"
    })
    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    @Select({
        "select",
        "id, address, age, name, phone_num",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set address = #{address,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "phone_num = #{phoneNum,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}