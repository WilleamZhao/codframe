package com.tlkj.cod.file.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import com.tlkj.cod.dao.annotation.CodDaoColumn;
import com.tlkj.cod.dao.annotation.CodDaoId;
import com.tlkj.cod.dao.annotation.CodDaoTable;

/**
 * Desc cod-file 数据存储表
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileDatabaseDo
 * @date 2019/6/18 2:44 PM
 */
@CodDaoTable(name = "cod_file_database_storage", comment = "数据库存储文件表")
public class CodFileDatabaseDo extends CodCommonModelConvert {

    public static final String TABLE_NAME = "cod_file_database_storage";

    /**
     * 主键
     */
    @CodDaoId
    @CodDaoColumn(type = "varchar(32)", isNull = false, comment = "主键")
    private String id;

    /**
     * url
     */
    @CodDaoColumn(type = "varchar(1000)", comment = "url")
    private String url;

    /**
     * 文件名
     */
    @CodDaoColumn(type = "varchar(1000)", comment = "文件名称")
    private String file_name;

    /**
     * 存储
     */
    @CodDaoColumn(type = "blob", comment = "存储")
    private String storage;

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
