package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//mapper操作数据库逻辑代码
public interface UserMapper {
    //登录
    @Select("SELECT uuid,phone FROM user WHERE phone= #{phone} and password= #{password}")
    User selectByUserPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);


    // 注册
    @Insert("INSERT INTO user ( uuid, phone,password ) VALUES ( #{uuid}, #{phone},#{password} )")
    int insertNewUser(@Param("uuid") String uuid, @Param("phone") String phone, @Param("password") String password);
}
