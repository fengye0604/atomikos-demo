package com.xy.controller;


import com.xy.service.JtaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @projectName:atomikos-demo
 * @see:com.xy.controller
 * @author:yanggaoli
 * @createTime:2021/4/24 20:41
 * @version:1.0
 */
@RestController
@RequestMapping("/testTransaction")
public class TransactionEssentialsController {
    @Autowired
    JtaService jtaService;
    @RequestMapping("add/{username}")
    public ResponseEntity addUser(@PathVariable("username") String username){
        Object o = jtaService.addUser(username);
        return ResponseEntity.ok().body(o);
    }


    @RequestMapping("test/{username}")
    public ResponseEntity test(@PathVariable("username") String username){
        username += "，你好呀！";
        return ResponseEntity.ok().body(username);

    }
}
