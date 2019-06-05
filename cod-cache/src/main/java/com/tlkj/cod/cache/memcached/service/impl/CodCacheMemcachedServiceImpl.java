/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.memcached.service.impl;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.cache.memcached.service.CodCacheMemcachedService;
import com.tlkj.cod.cache.model.CodCacheMemcachedModel;
import com.tlkj.cod.log.service.CodLogService;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * Desc codFrame memcached缓存实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheMemcachedServiceImpl
 * @date 2019/2/11 7:02 PM
 */
@Service("codCacheMemcached")
public class CodCacheMemcachedServiceImpl implements CodCacheMemcachedService, CodCacheManager {

    private CodLogService codLogService;

    private static MemcachedClient mcc = null;

    @Autowired
    public CodCacheMemcachedServiceImpl(CodLogService codLogService){
        this.codLogService = codLogService;
    }

    public CodCacheMemcachedServiceImpl(){
        try {
            //int DEFAULT_TIMEOUT = CodCacheMemcachedModel.getInstance().getTimeout();
            //TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;
            String password = CodCacheMemcachedModel.getInstance().getPassword();
            String username = CodCacheMemcachedModel.getInstance().getUsername();
            AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" }, new PlainCallbackHandler(username, password));
            if (mcc == null) {
                synchronized (CodCacheMemcachedServiceImpl.class){
                    if (mcc == null){
                        String host = CodCacheMemcachedModel.getInstance().getHost();
                        String port = CodCacheMemcachedModel.getInstance().getPort();
                        mcc = new MemcachedClient(
                                new ConnectionFactoryBuilder().setProtocol(ConnectionFactoryBuilder.Protocol.BINARY).setAuthDescriptor(ad).build(),
                                AddrUtil.getAddresses(host + ":" + port));
                    }
                }
            }
        } catch (IOException e) {
            codLogService.error("MemCached:加载失败", e);
        }
        codLogService.info("MemCached:成功加载");
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public boolean flush() {
        return false;
    }

    @Override
    public Map<String, Object> getMulti(Collection<String> keys) {
        return null;
    }

    @Override
    public Map<String, Object> getMulti(String[] keys) {
        return null;
    }

    @Override
    public Map<String, Object> asyncGetMulti(Collection<String> keys) {
        return null;
    }

    @Override
    public Map<String, Object> asyncGetMulti(String[] keys) {
        return null;
    }

    @Override
    public long increment(String key, int by, long defaultValue, int expire) {
        return 0;
    }

    @Override
    public long increment(String key, int by) {
        return 0;
    }

    @Override
    public long decrement(String key, int by, long defaultValue, int expire) {
        return 0;
    }

    @Override
    public long decrement(String key, int by) {
        return 0;
    }

    @Override
    public long asyncIncrement(String key, int by) {
        return 0;
    }

    @Override
    public long asyncDecrement(String key, int by) {
        return 0;
    }

    @Override
    public boolean getBooleanValue(Future<Boolean> f) {
        return false;
    }

    @Override
    public long getLongValue(Future<Long> f) {
        return 0;
    }




    @Override
    public boolean set(String key, Object value) {

        return false;
    }

    @Override
    public boolean set(String key, String value, int expire) {
        Future<Boolean> f = mcc.set(key, expire, value);
        return getBooleanValue(f);
    }

    @Override
    public boolean set(String key, Map value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, List value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, Set value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, Object value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, String value) {
        return false;
    }

    @Override
    public boolean replace(String key, Map value) {
        return false;
    }

    @Override
    public boolean replace(String key, List value) {
        return false;
    }

    @Override
    public boolean replace(String key, Set value) {
        return false;
    }

    @Override
    public boolean replace(String key, Object value) {
        return false;
    }

    @Override
    public boolean replace(String key, String value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, Map value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, List value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, Set value, int expire) {
        return false;
    }

    @Override
    public String getSupportType() {
        return "codCacheMemcached";
    }

    /* CodCacheManager-start */

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public <T> T get(String key, Class<T> klass) {
        return null;
    }

    @Override
    public Map getAll(Set key) {
        return null;
    }

    @Override
    public boolean set(String key, String value) {
        Future<Boolean> f = mcc.set(key, 0, value);
        return getBooleanValue(f);
    }

    @Override
    public boolean set(String key, Map value) {
        return false;
    }

    @Override
    public boolean set(String key, List value) {
        return false;
    }

    @Override
    public boolean set(String key, Set value) {
        return false;
    }

    @Override
    public Object asyncGet(String key) {
        return null;
    }

    @Override
    public boolean add(String key, Object value) {
        return false;
    }

    @Override
    public boolean add(String key, Object value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, Object value, int expire) {
        return false;
    }

    @Override
    public boolean del(String key) {
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }



}
