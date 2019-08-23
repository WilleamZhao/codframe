/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.launcher.model;

import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.enums.CodModuleLauncherStateEnum;
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
 * @className CodModuleLauncherModel
 * @date 2019/4/28 4:49 PM
 */
public class CodModuleLauncherModel implements Serializable {

    private static volatile CodModuleLauncherModel singleton;

    public static CodModuleLauncherModel getInstance() {
        if (singleton == null) {
            synchronized (CodModuleLauncherModel.class) {
                if (singleton == null) {
                    singleton = new CodModuleLauncherModel();
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
    private CodModuleLauncherStateEnum stateEnum = CodModuleLauncherStateEnum.CONTINUE;

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

    public CodModuleLauncherStateEnum getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(CodModuleLauncherStateEnum stateEnum) {
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
    public void setServer(CodModuleServerModel codModuleServerModel) {
        map.put(CodModuleOrderEnum.SERVER.getOrder(), codModuleServerModel);
    }

    public CodModuleServerModel getServer() {
        return (CodModuleServerModel) map.get(CodModuleOrderEnum.SERVER.getOrder());
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
     */
    public void finish(){
        this.stateEnum = CodModuleLauncherStateEnum.SUCCESS;
    }

    /**
     * 失败不继续
     */
    public void fail(){
        this.stateEnum = CodModuleLauncherStateEnum.FAIL;
    }

    /**
     * 停止执行下一步
     * 启动结束
     */
    public CodModuleLauncherModel stop(){
        this.stateEnum = CodModuleLauncherStateEnum.STOP;
        return this;
    }

    /**
     * 继续执行不刷新
     * 默认
     */
    public void next(){
        this.stateEnum = CodModuleLauncherStateEnum.CONTINUE;
    }
}
