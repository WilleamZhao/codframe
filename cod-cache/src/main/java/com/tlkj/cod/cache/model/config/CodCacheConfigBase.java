package com.tlkj.cod.cache.model.config;

import com.tlkj.cod.cache.model.CodCacheTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc codFrame 缓存基础配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheConfigBase
 * @date 2019/8/9 11:39 PM
 */
@Getter
@Setter
public class CodCacheConfigBase implements Serializable {


    private static final long serialVersionUID = -4176969637716122761L;

    /**
     * 过期时间 默认30分钟
     */
    private long due = 1000 * 60 * 30;

    /**
     * 缓存类型
     * 默认ehcache
     */
    private CodCacheTypeEnum type = CodCacheTypeEnum.EHCACHE;

    /**
     * 缓存个数
     * -1 无限制
     */
    private int heap = -1;

    /**
     * 硬盘持久化大小
     */
    private int disk = 500;
}
