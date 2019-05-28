package com.tlkj.cod.h2.service.impl;

import com.tlkj.cod.common.CodCommonDeviceInfo;
import com.tlkj.cod.dao.bean.CodDaoBean;
import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.dao.util.DBConnectionPool;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodH2ServiceImpl
 * @date 2019/5/17 3:03 PM
 */
@Service
public class CodH2ServiceImpl {

    //@Autowired
    public CodH2ServiceImpl(){
        DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding("utf-8");
        dataConnectBean.setDriverClass("org.h2.Driver");
        // dataConnectBean.setMaxActive();
        dataConnectBean.setUrl("jdbc:h2:./codConfigDB;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL");
        dataConnectBean.setUsername("codframe");
        dataConnectBean.setPassword("123456");
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        DataSource dataSource = dbConnectionPool.getHikariDataSource(dataConnectBean);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Finder finder = new Finder(jdbcTemplate);
        Updater updater = new Updater(jdbcTemplate);
        CodDaoBean codDaoBean = new CodDaoBean();
        codDaoBean.setFinder(finder);
        codDaoBean.setUpdater(updater);

    }

    //数据库连接URL，当前连接的是E:/H2目录下的gacl数据库
    private static final String JDBC_URL = "jdbc:h2:./codConfigDB;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL";
    //连接数据库时使用的用户名
    private static final String USER = CodCommonDeviceInfo.getHardware().getComputerSystem().getSerialNumber();
    //连接数据库时使用的密码
    private static final String PASSWORD = CodCommonDeviceInfo.getHardware().getProcessor().getProcessorID();
    //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
    private static final String DRIVER_CLASS="org.h2.Driver";

    public static void main(String[] args) throws Exception {

        DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding("utf-8");
        dataConnectBean.setDriverClass("org.h2.Driver");
        // dataConnectBean.setMaxActive();
        dataConnectBean.setUrl("jdbc:h2:./codConfigDB;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL");
        dataConnectBean.setUsername(CodCommonDeviceInfo.getHardware().getComputerSystem().getSerialNumber());
        dataConnectBean.setPassword(CodCommonDeviceInfo.getHardware().getProcessor().getProcessorID());
        // DBConnectionPool dbConnectionPool = new DBConnectionPool();
        // DataSource dataSource = dbConnectionPool.getHikariDataSource(dataConnectBean);
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        hikariDataSource.setUsername(dataConnectBean.getUsername());
        hikariDataSource.setPassword(dataConnectBean.getPassword());
        hikariDataSource.setJdbcUrl(dataConnectBean.getUrl());
        hikariDataSource.setMaximumPoolSize(Integer.parseInt(dataConnectBean.getMaxActive()));
        // hikariDataSource.setMinimumIdle(Integer.parseInt(dataConnectBean.getMinIdle()));
        hikariDataSource.setConnectionTestQuery(dataConnectBean.getTestQuery());
        hikariDataSource.setAutoCommit(true);
        // 设置编码
        if (StringUtils.isNotBlank(dataConnectBean.getUseUnicode())){
            hikariDataSource.addDataSourceProperty("useUnicode", dataConnectBean.getUseUnicode());
        }
        if (StringUtils.isNotBlank(dataConnectBean.getCharacterEncoding())){
            hikariDataSource.addDataSourceProperty("characterEncoding", dataConnectBean.getCharacterEncoding());
        }
        // 初始化Sql
        if (StringUtils.isNotBlank(dataConnectBean.getInitSql())){
            hikariDataSource.setConnectionInitSql(dataConnectBean.getInitialSize());
        }
        // dataSource = hikariDataSource;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        // jdbcTemplate.execute("DROP TABLE IF EXISTS USER_INFO");
        // jdbcTemplate.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
        List list = jdbcTemplate.queryForList("SELECT * FROM USER_INFO");
        jdbcTemplate.execute("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','赵春杰','男')");

        ScriptRunner runner = new ScriptRunner(hikariDataSource.getConnection());
        Resources.setCharset(Charset.forName("GBK"));
        runner.setLogWriter(null);//设置是否输出日志
        //在resouse中新建一个文件夹：然后放入sql文件
        runner.runScript(Resources.getResourceAsReader("sql/ceshi.sql"));
        //runner.runScript(Resources.getResourceAsReader("sql/CC21-01.sql"));
        runner.closeConnection();


        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        //如果存在USER_INFO表就先删除USER_INFO表
        // stmt.execute("DROP TABLE IF EXISTS USER_INFO");
        //创建USER_INFO表
        // stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
        //新增
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','大日如来','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','青龙','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','白虎','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','朱雀','女')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','玄武','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','苍狼','男')");
        //删除
        stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='大日如来'");
        //修改
        stmt.executeUpdate("UPDATE USER_INFO SET name='孤傲苍狼' WHERE name='苍狼'");
        //查询
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
        //遍历结果集
        while (rs.next()) {
            System.out.println(rs.getString("id") + "," + rs.getString("name")+ "," + rs.getString("sex"));
        }
        //释放资源
        stmt.close();
        //关闭连接
        conn.close();
    }

}
