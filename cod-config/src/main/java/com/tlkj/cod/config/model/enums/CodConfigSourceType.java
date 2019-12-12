package com.tlkj.cod.config.model.enums;

import java.util.Arrays;

/**
 * 配置源类型
 *
 * @author sourcod
 */
public enum CodConfigSourceType {

    /**
     * apollo配置中心
     */
    APOLLO("codConfigApolloServiceImpl", 2),

    /**
     * 数据库
     */
    DATABASE("codConfigDatabaseServiceImpl", 1),

    /**
     * CodData
     * 本地
     */
    DATA("codConfigDataServiceImpl", 0),

    /**
     * 缓存
     */
    CACHE("codConfigCacheServiceImpl", 4),

    /**
     * 配置文件
     */
    PROPERTIES("codConfigPropertiesServiceImpl", 5);

    /**
     * 描述
     */
    private String name;

    private int order;

    CodConfigSourceType(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public static void main(String[] args) {

    }
}
