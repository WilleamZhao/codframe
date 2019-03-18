/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonSerializable;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * Desc CodFrame 缓存model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheModel
 * @date 2019/2/13 3:33 PM
 */
public class CodCacheModel<T> implements Serializable {

    private static final long serialVersionUID = -5279312001807085872L;

    /**
     * 缓存类型
     *
     * 0: String
     * 1: Map
     * 2: List
     * 3: Set
     * 4: File
     *
     */
    private CodCacheFormatType type;

    /**
     * 缓存值
     */
    private T value;

    /**
     * 过期时间
     */
    private long time;

    public CodCacheFormatType getType() {
        return type;
    }

    public void setType(CodCacheFormatType type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public CodCacheModel(){

    }

    public CodCacheModel(CodCacheFormatType type, T value){
        this.type = type;
        this.value = value;
    }

    public CodCacheModel(CodCacheFormatType type, T value, long time){
        this.type = type;
        this.value = value;
        this.time = time;
    }

    @JsonIgnore
    public String getCache(){
        return CodCommonJson.dump(this);
    }

    /*public Object getCodCacheValue(){
        return getCodCacheValue();
    }*/

    /**
     * 获取缓存值
     * @return
     */
    public T getCodCacheValue(Class<T> zlass){
        switch (this.getType()){
            case STRING:
                return this.getValue();
            case SET:
                return (T) new HashSet((List)this.getValue());
            case File:
                return this.getValue();
            case LIST:
                return this.getValue();
            case MAP:
                return this.getValue();
            case OBJECT:
                try {
                    return CodCommonSerializable.serializeToObject(this.getValue().toString(), zlass);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("类型转换错误");
                    e.printStackTrace();
                    return null;
                }
            default:
                return this.getValue();
        }
    }

}
