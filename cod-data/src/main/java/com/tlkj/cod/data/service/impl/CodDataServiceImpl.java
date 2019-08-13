package com.tlkj.cod.data.service.impl;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import com.tlkj.cod.dao.util.CreateTable;
import com.tlkj.cod.data.model.entity.CodDataConfigDo;
import com.tlkj.cod.data.service.CodDataService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc cod data service impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataServiceImpl
 * @date 2019/6/3 2:02 PM
 */
@Service
public class CodDataServiceImpl implements CodDataService {

    private Finder finder;

    private Updater updater;

    /**
     * 初始化
     */
    @PostConstruct
    @Override
    public void init(){
        DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DATA.name());

        finder = new Finder(dataSource);
        updater = new Updater(dataSource);

        // config
        if (verifyExists(CodDataConfigDo.TABLE_NAME)){
            String sql = CreateTable.createTable(CodDataConfigDo.class, CodDataConfigDo.TABLE_NAME);
            updater.execute(sql);
        }

        // 执行初始化数据
        int num = finder.from(CodDataConfigDo.TABLE_NAME).select("count(*)").firstForObject(Integer.class);
        if (num == 0){
            initDataSql();
        }
    }

    /**
     * 执行初始化数据
     * @return
     */
    private void initDataSql(){
        // 文件
        setData("cod.file.config.type", "local");
        // 本地
        setData("cod.file.config.local.url", "./.cod-temp/codFile/");
        // oss
        setData("cod.file.config.alioss.endPoint", "http://oss-cn-beijing.aliyuncs.com");
        setData("cod.file.config.alioss.accessKeyId", "accessKeyId");
        setData("cod.file.config.alioss.accessKeySecret", "accessKeySecret");
        setData("cod.file.config.alioss.bucketName", "codframe");
        setData("cod.file.config.alioss.maxConnections", "200");
        setData("cod.file.config.alioss.socketTimeout", "1000");
        setData("cod.file.config.alioss.maxErrorRetry", "3");
        setData("cod.file.config.alioss.projectName", "codframe");
        setData("cod.file.config.alioss.headUrl", "https://codframe.oss-cn-beijing.aliyuncs.com/");
        // qiniu
        setData("cod.file.config.qiniu.accessKey", "accessKey");
        setData("cod.file.config.qiniu.secretKey", "secretKey");

        // 日志
        setData("cod.log.config.type", "clog");
        setData("cod.log.config.href", ".cod-temp/codLog/");
        setData("cod.log.config.pattern", "yyyy/MM/dd");
        setData("cod.log.config.split", "true");
        setData("cod.log.config.size", "10M");
        setData("cod.log.config.level", "info");
        setData("cod.log.config.console", "true");

        // 缓存
        setData("cod.cache.config.type", "codCacheEhcache");
        setData("cod.cache.config.isOpen", "1");
        setData("cod.cache.config.isGlobal", "0");
        setData("cod.cache.config.expire", "30");
        setData("cod.cache.config.last", "true");
        // memcached
        setData("cod.cache.config.memcache.host", "127.0.0.1");
        setData("cod.cache.config.memcache.port", "8000");
        setData("cod.cache.config.memcache.username", "codCacheMemcache");
        setData("cod.cache.config.memcache.password", "123456");
        setData("cod.cache.config.memcache.due", "1000");
        // redis
        setData("cod.cache.config.redis.host", "127.0.0.1");
        setData("cod.cache.config.redis.port", "6379");
        setData("cod.cache.config.redis.password", "123456");
        setData("cod.cache.config.redis.maxTotal", "1000");
        setData("cod.cache.config.redis.maxIdle", "1000");
        setData("cod.cache.config.redis.maxWaitMillis", "1000");
        setData("cod.cache.config.redis.testOnBorrow", "false");
        setData("cod.cache.config.redis.due", "1000");
        // ehcache
        setData("cod.cache.config.ehcache.url", ".cod-temp/codCache/ehcache");
        setData("cod.cache.config.ehcache.name", "defaultCodEhcache");
        setData("cod.cache.config.ehcache.heap", "10000");
        setData("cod.cache.config.ehcache.offHeap", "30");
        setData("cod.cache.config.ehcache.disk", "500");
        setData("cod.cache.config.ehcache.isDisk", "false");
        setData("cod.cache.config.ehcache.timeToLiveSeconds", "21600");
        setData("cod.cache.config.ehcache.timeToIdleSeconds", "21600");
        // json
        setData("cod.cache.config.json.url", ".cod-temp/codCache/json");
        setData("cod.cache.config.json.name", "defaultCodJsonCache");
        setData("cod.cache.config.json.heap", "10000");
        setData("cod.cache.config.json.disk", "500");
        setData("cod.cache.config.json.timeToLiveSeconds", "21600");
        setData("cod.cache.config.json.timeToIdleSeconds", "21600");



    }

    /**
     * 验证是否存在
     * @param table 表名
     * @return 是否不存在
     */
    private boolean verifyExists(String table){
        Integer num = finder.from("INFORMATION_SCHEMA.TABLES")
                .where("table_name", table)
                .select("count(*)")
                .firstForObject(Integer.class);
        return num == 0;
    }

    /**
     * 配置
     */
    @Override
    public Map<String, String> getConfig() {
        List<CodDataConfigDo> dataConfigDos = finder.from(CodDataConfigDo.TABLE_NAME).all(CodDataConfigDo.class);
        return dataConfigDos.stream().collect(Collectors.toMap(CodDataConfigDo::getC_key, CodDataConfigDo::getC_value));
    }

    /**
     * 获取数据
     */
    @Override
    public String getData(String key) {
        CodDataConfigDo dataConfigDo = finder.from(CodDataConfigDo.TABLE_NAME).where("c_key", key).first(CodDataConfigDo.class);
        if (dataConfigDo == null){
            return "";
        }
        return dataConfigDo.getC_value();
    }

    /**
     * 设置数据
     * @param key   key
     * @param value value
     */
    @Override
    public void setData(String key, String value) {
        updater.insert(CodDataConfigDo.TABLE_NAME).setId().set("c_value", value).set("c_key", key).update();
    }

    public static void main(String[] args) {
        String sql = CreateTable.createTable(CodDataConfigDo.class, CodDataConfigDo.TABLE_NAME);
        System.out.println(sql);
    }
}
