/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache.ehcache.service.impl;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.cache.ehcache.service.CodCacheEhcacheService;
import com.tlkj.cod.cache.model.config.CodCacheConfigEhcache;
import com.tlkj.cod.cache.model.CodCacheFormatType;
import com.tlkj.cod.cache.model.CodCacheModel;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonSerializable;
import com.tlkj.cod.log.service.CodLogService;
import org.apache.commons.lang3.StringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.StateTransitionException;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc CodCacheEhcacheServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheEhcacheServiceImpl
 * @date 2019/2/11 7:12 PM
 */
@Service("codCacheEhcache")
@SuppressWarnings("unchecked")
public class CodCacheEhcacheServiceImpl implements CodCacheEhcacheService, CodCacheManager {

    private static CacheManager cacheManager = null;

    @Autowired
    private CodCacheConfigEhcache codCacheConfigEhcache;

    @Autowired
    CodLogService codLogService;

    /**
     * 构造方法初始化 cacheManager
     */
    public CodCacheEhcacheServiceImpl(){
        // new CodCacheEhcacheServiceImpl(codCacheConfigEhcache);
    }

    /**
     * TODO 错误
     */
    @Autowired
    public CodCacheEhcacheServiceImpl(CodCacheConfigEhcache codCacheConfigEhcache){
        // CacheManager管理缓存
        if (cacheManager == null){
            try {
                cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                        // 硬盘持久化地址
                        .with(CacheManagerBuilder.persistence(codCacheConfigEhcache.getRootDirectory()))
                        // 设置一个默认缓存配置
                        .withCache(codCacheConfigEhcache.getName(), getCacheConfiguration(codCacheConfigEhcache))
                        //创建之后立即初始化
                        .build(true);
            } catch (StateTransitionException e){
                codLogService.error("错误", e);
                System.out.println("TODO 错误");
            }
        }
    }

    /**
     * 获取配置
     * @return 默认配置
     */
    private CacheConfiguration<?, ?> getCacheConfiguration(){
        return getCacheConfiguration(codCacheConfigEhcache, codCacheConfigEhcache.getKeyType(), codCacheConfigEhcache.getValueType());
    }

    /**
     * 获取配置
     * @return 默认配置
     */
    private CacheConfiguration<?, ?> getCacheConfiguration(Class<?> keyType, Class<?> valueType){
        return getCacheConfiguration(codCacheConfigEhcache, keyType, valueType);
    }

    /**
     * 获取配置
     * @return 默认配置
     */
    private CacheConfiguration<?, ?> getCacheConfiguration(CodCacheConfigEhcache codCacheConfigEhcache){
        return getCacheConfiguration(codCacheConfigEhcache, codCacheConfigEhcache.getKeyType(), codCacheConfigEhcache.getValueType());
    }

    /**
     * 获取配置
     * @param codCacheConfigEhcache Ehcache配置Model
     * @return 配置
     */
    private CacheConfiguration<?, ?> getCacheConfiguration(CodCacheConfigEhcache codCacheConfigEhcache, Class<?> keyType, Class<?> valueType){
        // 配置默认缓存属性
        return CacheConfigurationBuilder.newCacheConfigurationBuilder(
                // 缓存数据K和V的数值类型
                // 在ehcache3.5中必须指定缓存键值类型,如果使用中类型与配置的不同,会报类转换异常
                keyType, valueType,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                        //设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offHeap中
                        .heap(codCacheConfigEhcache.getHeap(),EntryUnit.ENTRIES)
                        //设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
                        .offheap(codCacheConfigEhcache.getOffHeap(), MemoryUnit.MB)
                        //配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
                        .disk(codCacheConfigEhcache.getDisk(), MemoryUnit.MB,true))
                //设置缓存过期时间
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.of(codCacheConfigEhcache.getTimeToLiveSeconds(), ChronoUnit.SECONDS)))
                //设置被访问后过期时间(同时设置和TTL和TTI之后会被覆盖,这里TTI生效,之前版本xml配置后是两个配置了都会生效)
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.of(codCacheConfigEhcache.getTimeToIdleSeconds(), ChronoUnit.SECONDS)))
                // 缓存淘汰策略 默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
                // .withEvictionAdvisor((s, s2) -> s.length() > 10)
                .build();
    }



    /**
     * 获取Cache，当Cache不存在时自动创建
     *
     * @param cacheName 缓存名称
     * @return Cache
     */
    private Cache getOrAddCache(String cacheName, Class<?> keyType, Class<?> valueType) {
        if (cacheManager == null){
            new CodCacheEhcacheServiceImpl(codCacheConfigEhcache);
        }
        Cache cache = cacheManager.getCache(cacheName, keyType, valueType);
        if (cache == null) {
            synchronized (CodCacheEhcacheServiceImpl.class){
                cache = cacheManager.getCache(cacheName, keyType, valueType);
                if (cache == null) {
                    cacheManager.createCache(cacheName, new CodCacheEhcacheServiceImpl().getCacheConfiguration(keyType, valueType));
                    cache = cacheManager.getCache(cacheName, keyType, valueType);
                }
            }
        }
        return cache;
    }

    private Cache getCache(){
        return getCache(codCacheConfigEhcache.getName());
    }

    @Override
    public Cache getCache(String name) {
        return getCache(name, codCacheConfigEhcache.getKeyType(), codCacheConfigEhcache.getValueType());
    }

    @Override
    public Cache getCache(Class<?> keyType, Class<?> valueType) {
        return getCache(codCacheConfigEhcache.getName(), codCacheConfigEhcache.getKeyType(), codCacheConfigEhcache.getValueType());
    }

    @Override
    public Cache getCache(String name, Class<?> keyType, Class<?> valueType) {
        return getOrAddCache(name, keyType, valueType);
    }

    @Override
    public Object get(Object key) {
        return getCache().get(key);
    }

    @Override
    public boolean set(Object key, Object value) {
        try {
            getCache().put(key, value);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Object get(String name, Object key) {
        return getCache(name).get(key);
    }

    @Override
    public boolean set(String name, Object key, Object value) {
        try {
            getCache(name).put(key, value);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean setAll(String name, Map map) {
        try {
            getCache(name).putAll(map);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Object get(String name, Object key, Class<?> keyType, Class<?> valueType) {
        return getCache(name, keyType, valueType).get(key);
    }

    @Override
    public boolean set(String name, Object key, Object value, Class keyType, Class valueType) {
        try {
            getCache(name, keyType, valueType).put(key, value);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean setAll(String name, Map map, Class keyType, Class valueType) {
        try {
            getCache(name, keyType, valueType).putAll(map);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        return getCache().containsKey(key);
    }

    @Override
    public boolean containsKey(String name, Object key, Class keyType, Class valueType) {
        return getCache(name, keyType, valueType).containsKey(key);
    }

    @Override
    public boolean remove(Object key) {
        try {
            getCache().remove(key);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(String name, Object key, Object value, Class keyType, Class valueType) {
        try {
            getCache(name, keyType, valueType).remove(key);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Set set) {
        try {
            getCache().removeAll(set);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(String name, Set set, Class keyType, Class valueType) {
        try {
            getCache(name, keyType, valueType).remove(set);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    /* CodCacheManager-start */

    @Override
    public Object get(String key) {
        return get(key, Object.class);
    }

    @Override
    public <T> T get(String key, Class<T> zlass) {
        Object obj = getCache().get(key);
        CodCacheModel codCacheModel = null;
        if (obj != null && StringUtils.isNotBlank(obj.toString())){
            try {
                codCacheModel = CodCommonJson.load(obj.toString(), CodCacheModel.class);
            } catch (Exception e){
                codLogService.error("目标类型转换失败");
                try {
                    return zlass.cast(obj);
                } catch (Exception e1){
                    codLogService.error("缓存类型和目标类型不匹配");
                    return null;
                }
            }
        }

        if (codCacheModel == null){
            return null;
        }

        if (codCacheModel.getTime() != 0 && codCacheModel.getTime() < Calendar.getInstance().getTime().getTime()){
            codLogService.debug("缓存过期");
            return null;
        }

        return zlass.cast(codCacheModel.getCodCacheValue(zlass));
    }

    @Override
    public Map getAll(Set key) {
        Map map = new HashMap();
        for (Object o : key){
            map.put(o, get(o));
        }
        return map;
    }


    @Override
    public boolean set(String key, String value) {
        try{
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.STRING, value).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean set(String key, Map value) {
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.MAP, value).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean set(String key, List value) {
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.LIST, value).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean set(String key, Set value) {
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.SET, value).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    /**
     * 存对象
     * 此方法不通用
     *
     * TODO 暂不支持非序列化对象缓存
     *
     * @param key   key
     * @param value value
     */
    @Override
    public boolean set(String key, Object value) {
        try {
            if(value instanceof Serializable){
                //说明实现的序列化
                getCache().put(key, new CodCacheModel<>(CodCacheFormatType.OBJECT, CodCommonSerializable.serialize(value)).getCache());
            } else {
                // getCache("1234", String.class, Object.class).put(key, value);
                //未实现序列化
                System.out.println("对象未序列化");
                // return false;
            }
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean set(String key, String value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.STRING, value, time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean set(String key, Map value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.MAP, value, time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean set(String key, List value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.LIST, value, time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean set(String key, Set value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.SET, value, time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean set(String key, Object value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.OBJECT, CodCommonSerializable.serialize(value), time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, String value) {
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.STRING, value).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, Map value) {
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.MAP, value).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, List value) {
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.LIST, value).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, Set value) {
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.SET, value).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, Object value) {
        try {
            if (!(value instanceof Serializable)){
                //未实现序列化
                codLogService.error("codCacheEhcache 缓存 set 失败");
                return false;
            }
            //说明实现的序列化
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.OBJECT, CodCommonSerializable.serialize(value)).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, String value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.STRING, value, time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, Map value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.MAP, value, time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, List value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.LIST, value, time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, Set value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.SET, value, time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(String key, Object value, int expire) {
        long time = Calendar.getInstance().getTime().getTime() + expire * 1000;
        try {
            getCache().put(key, new CodCacheModel<>(CodCacheFormatType.OBJECT, CodCommonSerializable.serialize(value), time).getCache());
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean del(String key) {
        try {
            getCache().remove(key);
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean clear() {
        try {
            getCache().clear();
        } catch (Exception e){
            codLogService.error("codCacheEhcache 缓存 set 失败");
            return false;
        }
        return true;
    }

    @Override
    public String getSupportType() {
        return "codCacheEhcache";
    }

    public static void main(String[] args) {
        /*long a = Calendar.getInstance().getTime().getTime();
        System.out.println(a);

        long b = a + 60 * 1000;
        System.out.println(new Date().getTime() );
        Date datea = new Date(a);
        Date dateb = new Date(b);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
        System.out.println(sdf.format(datea));
        System.out.println(sdf.format(dateb));*/

        /*SystemSetLog log = new SystemSetLog();
        log.setHref("");
        log.setLevel("info");
        log.setSize("10m");
        log.setSplit("/");
        SystemModel.getInstance().setLog(log);*/

        CodCacheEhcacheServiceImpl ehcacheService = new CodCacheEhcacheServiceImpl();

        Map map = new HashMap();
        map.put("a", "a");
        map.put("b", "b");
        ehcacheService.set( "test", map);
        Map o = ehcacheService.get("test", Map.class);
        System.out.println(o);

        Set set = new HashSet();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        ehcacheService.set( "test1", set, 60 * 30);
        Set o2 = ehcacheService.get("test1", Set.class);
        System.out.println(o2);

        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        ehcacheService.set( "test3", list, 60 * 30);
        List list1 = ehcacheService.get("test3", List.class);
        System.out.println(list1);

        /*
        CodCacheEhcacheServiceImpl a = new CodCacheEhcacheServiceImpl();
        ehcacheService.set( "test2", a, 60 * 30);
        CodCacheEhcacheServiceImpl o4 = ehcacheService.get("test2", CodCacheEhcacheServiceImpl.class);
        System.out.println(o4);
        o4.set("as123", "a123");

        Object asd = ehcacheService.get("as123");
        System.out.println(asd);
        */

        Set set1 = new HashSet();
        set1.add("test");
        set1.add("test1");
        set1.add("test3");
        Map map1 = ehcacheService.getAll(set1);
        System.out.println(map1);
        /*
        ehcacheService.getOrAddCache("test3", String.class, String.class).put("a", "aasd");
        Object asd = ehcacheService.getOrAddCache("test3", String.class, String.class).get("a");
        System.out.println(asd);
        */


    }
}
