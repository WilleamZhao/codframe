package com.tlkj.cod.config.service;


import com.tlkj.cod.config.model.CodConfigChangeEvent;

/**
 * 修改配置
 *
 * @author sourcod
 */
public interface CodConfigChangeListener {

    /**
     *
     * @param changeEvent
     */
    void onChange(CodConfigChangeEvent changeEvent);
}
