package com.tlkj.cod.config.service;


import com.tlkj.cod.config.model.CodConfigChangeEvent;

/**
 *
 * @author sourcod
 */
public interface CodConfigChangeListener {

    public void onChange(CodConfigChangeEvent changeEvent);
}
