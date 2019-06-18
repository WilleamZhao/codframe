package com.tlkj.cod.data.model.entity;

import com.tlkj.cod.dao.annotation.CodDaoColumn;
import com.tlkj.cod.dao.annotation.CodDaoTable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc codFrame 核心配置表
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataConfigDo
 * @date 2019/6/4 4:19 PM
 */
@Getter
@Setter
@CodDaoTable(name = "cod_core_config", comment = "核心配置表")
public class CodDataConfigDo implements Serializable {

    public static final String TABLE_NAME = "cod_core_config";

    /**
     * 配置ID
     */
    @CodDaoColumn(type = "varchar(32)", isNull = false)
    private String id;

    /**
     * 配置key
     */
    @CodDaoColumn(type = "varchar(1000)", comment = "配置key")
    private String c_key;

    /**
     * 配置值
     */
    /*@CodDaoId*/
    @CodDaoColumn(type = "varchar(2000)", comment = "配置值")
    private String c_value;

    /**
     * 配置名称
     */
    @CodDaoColumn(type = "varchar(2000)", comment = "配置名称")
    private String c_name;

    /**
     * 序号
     */
    @CodDaoColumn(type = "int", comment = "序号")
    private int sort;

    /**
     * 创建时间
     */
    @CodDaoColumn(type = "timestamp", def = "CURRENT_TIMESTAMP", isNull = false, comment = "创建时间")
    private String create_time;

    /**
     * 修改时间
     */
    @CodDaoColumn(type = "datetime", comment = "修改时间")
    private String update_time;
}
