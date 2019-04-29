/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.launcher.model;

import com.tlkj.cod.launcher.CodModuleOrderEnum;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc 初始化model
 *
 * @author sourcod
 * @version 1.0
 * @className LauncherModel
 * @date 2019/4/28 4:49 PM
 */
public class LauncherModel<T> implements Serializable {

    private Map<Integer, Object> map = new HashMap<>();

    /**
     * 状态码
     *
     * -1: 启动失败
     * 0: 未启动
     * 1: 启动成功
     */
    private int state = 0;

    public boolean isStart(){
        if (state == 1){
            return true;
        }
        return false;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Map<Integer, Object> getMap() {
        return map;
    }

    public void addMap(Map<Integer, Object> map) {
        this.map.putAll(map);
    }

    /**
     * 获取单个Data
     */
    public Object getData(int order) {
        return map.get(order);
    }

    /**
     * 设置data
     */
    public void setData(int order, Object o){
        if (CodModuleOrderEnum.isAvailable(order)){
            map.put(order, o);
        }
    }

    /**
     * 获取spring
     */
    public ApplicationContext getSpring() {
        return (ApplicationContext) map.get(CodModuleOrderEnum.SPRING.getOrder());
    }

    /**
     * 设置spring
     */
    public void setSpring(ApplicationContext applicationContext){
        map.put(CodModuleOrderEnum.SPRING.getOrder(), applicationContext);
    }

    /**
     * server
     */
    public void setServer(ServerModel serverModel) {
        map.put(CodModuleOrderEnum.SERVER.getOrder(), serverModel);
    }

    public ServerModel getServer() {
        return (ServerModel) map.get(CodModuleOrderEnum.SERVER.getOrder());
    }
}
