package com.xy.test;

import com.xy.service.JtaService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @projectName:atomikos-demo
 * @see:com.xy.test
 * @author:yanggaoli
 * @createTime:2021/4/24 10:03
 * @version:1.0
 */
public class TransactionEssentialsSpringTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context/spring-atomikos.xml");
        JtaService jtaService = context.getBean("jtaService", JtaService.class);
        jtaService.insert("lisi");
    }
}