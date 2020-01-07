package com.cnxk;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
/*   @Results({
     @Result(property = "wukong",column = "desc")
   })*/
  @Select("select id,username,age,phone,`desc` from user where id=#{id}")
  public User selectUser(Integer id);

}
