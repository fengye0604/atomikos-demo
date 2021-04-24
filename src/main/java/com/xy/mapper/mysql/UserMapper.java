package com.xy.mapper.mysql;

import com.xy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
   @Insert("INSERT INTO trade_user(user_id,username,create_time) VALUES(#{id},#{username},#{createTime})")
   @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
   public void insert(User user);
}