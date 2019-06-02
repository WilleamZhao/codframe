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
import com.tlkj.cod.launcher.model.enums.LauncherStateEnum;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc 初始化model
 * 单例模式
 *
 * @author sourcod
 * @version 1.0
 * @className LauncherModel
 * @date 2019/4/28 4:49 PM
 */
public class LauncherModel implements Serializable {

    private static volatile LauncherModel singleton;

    public static LauncherModel getInstance() {
        if (singleton == null) {
            synchronized (LauncherModel.class) {
                if (singleton == null) {
                    singleton = new LauncherModel();
                }
            }
        }
        return singleton;
    }

    /**
     * data
     */
    private Map<Integer, Object> map = new HashMap<>();

    /**
     * 状态码
     *
     * -1: 启动失败
     * 0: 未启动
     * 1: 启动成功
     */
    private int state = 0;

    /**
     * 模块启动状态
     */
    private LauncherStateEnum stateEnum = LauncherStateEnum.CONTINUE;

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

    public LauncherStateEnum getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(LauncherStateEnum stateEnum) {
        this.stateEnum = stateEnum;
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
    public AnnotationConfigWebApplicationContext getSpring() {
        AnnotationConfigWebApplicationContext applicationContext = (AnnotationConfigWebApplicationContext) map.get(CodModuleOrderEnum.SPRING.getOrder());
        if (applicationContext == null){
            applicationContext = new AnnotationConfigWebApplicationContext();
            setSpring(applicationContext);
        }
        return applicationContext;
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

    /**
     * data
     */
    public void setCodData(DataSource dataSource){
        map.put(CodModuleOrderEnum.DATA.getOrder(), dataSource);
    }

    public DataSource getCodData(){
        return (DataSource) map.get(CodModuleOrderEnum.DATA.getOrder());
    }

    /**
     * 继续执行下一步
     * 刷新
     * @return
     */
    public void finish(){
        this.stateEnum = LauncherStateEnum.SUCCESS;
    }

    /**
     * 失败不继续
     */
    public void fail(){
        this.stateEnum = LauncherStateEnum.FAIL;
    }

    /**
     * 停止执行下一步
     * @return
     */
    public LauncherModel stop(){
        this.stateEnum = LauncherStateEnum.STOP;
        return this;
    }

    /**
     * 继续执行不刷新
     */
    public void next(){
        this.stateEnum = LauncherStateEnum.CONTINUE;
    }
}
