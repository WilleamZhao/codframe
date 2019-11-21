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

    private static final long serialVersionUID = -5298903303430353838L;

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
     * 数据源缓存
     */
    private Map<String, DataSource> datasourcdMap = new HashMap<>();

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
        return this.getData(order, Object.class);
    }

    /**
     * 获取单个 Data
     * @param order  序号
     * @param tClass 类
     * @param <T>    范型
     * @return
     */
    public <T> T getData(int order, Class<T> tClass) {
        return tClass.cast(map.get(order));
    }

    /**
     * 设置data
     * 默认 系统保留不能设置
     * @param order 模块序号
     * @param o     对象
     */
    public void setData(int order, Object o){
        setData(order, o, true);
    }

    /**
     * 设置data
     * @param isVerify 是否验证
     */
    public void setData(int order, Object o, boolean isVerify){
        if (isVerify){
            if (CodModuleOrderEnum.isAvailable(order)){
                map.put(order, o);
            }
        } else {
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
     * 获取 spring bean
     */
    public <T> T getBean(String beanName, Class<T> zlass) {
        return getSpring().getBean(beanName, zlass);
    }

    /**
     * 获取 spring bean
     */
    public <T> T getBean(Class<T> zlass) {
        return getSpring().getBean(zlass);
    }

    /**
     * 获取 spring bean
     */
    public Object getBean(String name) {
        return getSpring().getBean(name);
    }

    /**
     * 设置spring
     */
    public void setSpring(ApplicationContext applicationContext){
        this.setData(CodModuleOrderEnum.SPRING.getOrder(), applicationContext, false);
    }

    /**
     * 核心数据源
     */
    public void setCodData(DataSource dataSource) {
        this.setData(CodModuleOrderEnum.DATA.getOrder(), dataSource, false);
    }

    public DataSource getCodData(){
        return this.getData(CodModuleOrderEnum.DATA.getOrder(), DataSource.class);
    }

    /**
     * 模块数据源
     * 不设置默认 默认数据源
     */
    public void setModuleDatasource(String name, DataSource dataSource){
        datasourcdMap.put(name, dataSource);
    }

    public DataSource getModuleDatasource(String name){
        return datasourcdMap.get(name);
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
