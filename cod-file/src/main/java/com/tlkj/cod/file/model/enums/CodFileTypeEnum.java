package com.tlkj.cod.file.model.enums;


/**
 * Desc cod-file 文件类型枚举
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileTypeEnum
 * @date 2019/6/17 3:38 PM
 */
public enum CodFileTypeEnum {

    /**
     * 本地
     */
    LOCAL("codFileLocal"),

    /**
     * 阿里OSS
     */
    ALIOSS("codFileAlioss"),

    /**
     * 七牛
     */
    QINIU("codFileQiniu"),

    /**
     * 又拍云
     */
    UPYUN("codFileUpyun"),

    /**
     * 数据库
     */
    DATABASE("codFileDatabase");

    private String support;

    CodFileTypeEnum(String support){
        this.support = support;
    }

    public String getSupport() {
        return support;
    }

}
