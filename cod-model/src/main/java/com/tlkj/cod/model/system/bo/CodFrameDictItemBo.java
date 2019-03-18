/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.bo;

import java.io.Serializable;

/**
 * Desc 根据code 获取字典数据Bo
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameDictItemBo
 * @date 2018/11/30 6:14 PM
 */
public class CodFrameDictItemBo implements Serializable {

    public CodFrameDictItemBo() {
    }

    public CodFrameDictItemBo(String id, String name, String code, String value, String englishName, String allPin, String simplePin, String remark) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAllPin() {
        return allPin;
    }

    public void setAllPin(String allPin) {
        this.allPin = allPin;
    }

    public String getSimplePin() {
        return simplePin;
    }

    public void setSimplePin(String simplePin) {
        this.simplePin = simplePin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
