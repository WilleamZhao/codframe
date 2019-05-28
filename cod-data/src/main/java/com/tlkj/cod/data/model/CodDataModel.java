package com.tlkj.cod.data.model;

import com.tlkj.cod.data.service.CodDataService;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc cod 数据 model
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataModel
 * @date 2019/5/28 12:08 PM
 */

@Getter
@Setter
public class CodDataModel {

    /**
     * 实现Service
     */
    private CodDataService codDataService;

    /**
     * 是否初始化
     */
    private boolean isInit;

    /**
     * 数据类型
     * 1: H2
     * 2: 配置文件
     */
    private boolean type;

}
