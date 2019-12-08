package com.tlkj.cod.cache.database.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 数据库缓存实体类
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheDBDo
 * @date 2019/8/9 11:20 PM
 */
@Getter
@Setter
public class CodCacheDBDo extends CodCommonModelConvert implements Serializable {

    public static final String TABLE_NAME = "cod_sys_cache";

    /**
     * 主键
     */
    private String id;

    /**
     * key
     */
    private String k;

    /**
     * value
     */
    private String v;

    /**
     * 最后一次访问到期还是初始化到期
     * 1: TTI
     * 0: TTL
     */
    private String idle;

    /**
     * 到期时间 (秒)
     */
    private String due;

    /**
     * 状态
     * 0: 禁用
     * 1: 启用
     */
    private String state;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;
}
