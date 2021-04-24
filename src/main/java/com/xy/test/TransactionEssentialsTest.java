package com.xy.test;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import oracle.jdbc.xa.client.OracleXADataSource;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;

/**
 * @description:
 * @projectName:atomikos-demo
 * @see:com.xy.test
 * @author:yanggaoli
 * @createTime:2021/4/24 10:03
 * @version:1.0
 */
public class TransactionEssentialsTest {
    private static AtomikosDataSourceBean createAtomikosDataSourceBean(String dbName) {
        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("url", "jdbc:mysql://localhost:3306/" + dbName);
        p.setProperty("user", "root");
        p.setProperty("password", "root");

        // 使用AtomikosDataSourceBean封装com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        //atomikos要求为每个AtomikosDataSourceBean名称，为了方便记忆，这里设置为和dbName相同
        ds.setUniqueResourceName(dbName);
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setXaProperties(p);
        return ds;
    }

    private static AtomikosDataSourceBean createAtomikosDataSourceBeanOracle() {

        /*
        oracle.qa.db.url=jdbc\:oracle\:thin\:@127.0.0.1\:1521\:orcl
        oracle.qa.db.driverClassName=oracle.jdbc.OracleDriver
        oracle.qa.db.testQuery=select 1 from dual
        oracle.qa.db.user=sh_12345hotline
        oracle.qa.db.password=sh_12345hotline
         */

        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("URL", "jdbc:oracle:thin:@127.0.0.1:1521:orcl");
        p.setProperty("user", "sh_12345hotline");
        p.setProperty("password", "sh_12345hotline");

        // 使用AtomikosDataSourceBean封装com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        //atomikos要求为每个AtomikosDataSourceBean名称，为了方便记忆，这里设置为和dbName相同
        ds.setUniqueResourceName("orcl25");
        ds.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
        ds.setXaProperties(p);
        return ds;
    }


    public static void main(String[] args) {
        testTranscationDiffrentBD();
//        testTranscationDubble();
//        testTranscationSingle();
    }
    //一个库测试事务
    public static void testTranscationSingle() {
        AtomikosDataSourceBean ds1 = createAtomikosDataSourceBean("trade");
        Connection conn1 = null;
        PreparedStatement ps1 = null;
        UserTransaction userTransaction = new UserTransactionImp();
        try {
            // 开启事务
            userTransaction.begin();

            // 执行db1上的sql
            conn1 = ds1.getConnection();
            ps1 = conn1.prepareStatement("insert into trade_user (username) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, "tianshouzhi1");
            ps1.executeUpdate();
            ResultSet generatedKeys = ps1.getGeneratedKeys();
            int userId = -1;
            while (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);// 获得自动生成的userId
                System.out.println(userId);
            }

            // 两阶段提交
            userTransaction.commit();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                userTransaction.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                ps1.close();
                conn1.close();
                ds1.close();
            } catch (Exception ignore) {
            }
        }
    }
    //两个库测试事务
    public static void testTranscationDubble() {
        AtomikosDataSourceBean ds1 = createAtomikosDataSourceBean("trade");
        AtomikosDataSourceBean ds2 = createAtomikosDataSourceBean("test");

        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        UserTransaction userTransaction = new UserTransactionImp();
        try {
            // 开启事务
            userTransaction.begin();

            // 执行db1上的sql
            conn1 = ds1.getConnection();
            ps1 = conn1.prepareStatement("INSERT into trade_user (username,password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, "1152x");
            ps1.setString(2, UUID.randomUUID().toString().substring(5));
            ps1.executeUpdate();
            ResultSet generatedKeys = ps1.getGeneratedKeys();
            int userId = -1;
            while (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);// 获得自动生成的userId
            }

            // 模拟异常 ，直接进入catch代码块，2个都不会提交


            // 执行db2上的sql
            conn2 = ds2.getConnection();
            ps2 = conn2.prepareStatement("INSERT into account(user_id,money) VALUES (?,?)");
            ps2.setInt(1, userId);
            ps2.setDouble(2, 20000000);
            ps2.executeUpdate();
//            int i=1/0;
            // 两阶段提交
            userTransaction.commit();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                userTransaction.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                ps1.close();
                ps2.close();
                conn1.close();
                conn2.close();
                ds1.close();
                ds2.close();
            } catch (Exception ignore) {
            }
        }
    }


    //两个库测试事务
    public static void testTranscationDiffrentBD() {
        AtomikosDataSourceBean ds1 = createAtomikosDataSourceBean("trade");
        AtomikosDataSourceBean ds2 = createAtomikosDataSourceBeanOracle();

        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        UserTransaction userTransaction = new UserTransactionImp();
        try {
            // 开启事务
            userTransaction.begin();

            // 执行db1上的sql
            conn1 = ds1.getConnection();
            ps1 = conn1.prepareStatement("INSERT into trade_user (username,password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, "1154x");
            ps1.setString(2, UUID.randomUUID().toString().substring(5));
            ps1.executeUpdate();
            ResultSet generatedKeys = ps1.getGeneratedKeys();
            int userId = -1;
            while (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);// 获得自动生成的userId
            }

            // 模拟异常 ，直接进入catch代码块，2个都不会提交


            // 执行db2上的sql
            conn2 = ds2.getConnection();
            ps2 = conn2.prepareStatement("INSERT into demo(file_path) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, userId+UUID.randomUUID().toString().substring(5));
            ps2.executeUpdate();
//            int i=1/0;
            // 两阶段提交
            userTransaction.commit();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                userTransaction.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                ps1.close();
                ps2.close();
                conn1.close();
                conn2.close();
                ds1.close();
                ds2.close();
            } catch (Exception ignore) {
            }
        }
    }
}