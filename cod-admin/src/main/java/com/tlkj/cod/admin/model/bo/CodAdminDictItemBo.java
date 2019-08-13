/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 根据code 获取字典数据Bo
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDictItemBo
 * @date 2018/11/30 6:14 PM
 */
@Getter
@Setter
public class CodAdminDictItemBo implements Serializable {

    public CodAdminDictItemBo() {
    }

    public CodAdminDictItemBo(String id, String name, String code, String value, String englishName, String allPin, String simplePin, String remark) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.value = value;
        this.englishName = englishName;
        this.allPin = allPin;
        this.simplePin = simplePin;
        this.remark = remark;
    }

    private String id;

    private String name;

    private String code;

    private String value;

    private String englishName;

    private String allPin;

    private String simplePin;

    private String remark;

}
