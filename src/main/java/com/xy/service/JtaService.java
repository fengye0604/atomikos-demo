package com.xy.service;

import com.xy.mapper.mysql.UserMapper;
import com.xy.mapper.oracle.AccountMapper;
import com.xy.model.Account;
import com.xy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JtaService {
   @Autowired
   private UserMapper userMapper;//操作user所在的库
   @Autowired
   private AccountMapper accountMapper;//操作account所在的库
   @Transactional
   public void insert(String username) {
      User user = new User();
      user.setUsername(username);
      user.setCreateTime(new Date());
      userMapper.insert(user);
       
      //int i = 1 / 0;//模拟异常，spring回滚后，db_user库中user表中也不会插入记录
      Account account = new Account();
      account.setUserId(user.getId());
      account.setMoney(String.valueOf(Math.random()*1000).substring(4));
      accountMapper.insert(account);

      System.out.println(account.getKlId());
   }

   @Transactional
   public Object addUser(String username) {
      User user = new User();
      user.setUsername(username);
      user.setCreateTime(new Date());
      userMapper.insert(user);

//      int i = 1 / 0;//模拟异常，spring回滚后，db_user库中user表中也不会插入记录
      Account account = new Account();
      account.setUserId(user.getId());
      account.setMoney(String.valueOf(Math.random()*1000).substring(4));
      accountMapper.insert(account);
      Map map = new HashMap();
      map.put("user",user);
      map.put("account",account);
      return map;
   }
}