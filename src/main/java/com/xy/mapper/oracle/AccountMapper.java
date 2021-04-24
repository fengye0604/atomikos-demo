package com.xy.mapper.oracle;

import com.xy.model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    //设置主键（由uuid生成）
    /*
    @SelectKey(keyProperty = "userInfo.id", resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "userInfo.id", useGeneratedKeys = true)
    */
    //设置主键（由序列生成）

    @SelectKey(keyProperty = "klId", resultType = String.class, before = true,
            statement = "select SEQ_T_ACCOUNT.NEXTVAL from dual")
    @Options(keyProperty = "klId", useGeneratedKeys = true)
    @Insert("INSERT INTO T_ACCOUNT(id,money,user_id) VALUES(#{klId},#{money},#{userId})")
    public void insert(Account account);
}