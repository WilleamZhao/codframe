package com.tlkj.cod.config.model;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.model.enums.CodPropertyChangeType;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigModel
 * @date 2019/5/30 11:25 AM
 */
@Getter
@Setter
public class CodConfigModel {

    /**
     * key
     */
    private String key;

    /**
     * 旧值
     */
    private String oldValue;

    /**
     * 新值
     */
    private String newValue;

    /**
     * 源类型
     */
    private CodConfigSourceType type;

    /**
     * 修改类型
     */
    private CodPropertyChangeType changeType;

    /**
     * 是否更改过
     */
    private boolean modify;
}
