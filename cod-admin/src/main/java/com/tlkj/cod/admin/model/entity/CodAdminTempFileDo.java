/*
 *  Copyright (c) 2018-2019.
 *  Beijing sky blue technology co., LTD.
 *  All rights reserved
 *
 *  author: sourcod
 *  github: https://github.com/WilleamZhao
 *  site：http://codframe.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 通过FileAction上传的文件Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminTempFileDo
 * @date 2019/3/7 6:05 PM
 */
@Getter
@Setter
public class CodAdminTempFileDo extends CodCommonModelConvert implements Serializable {

    public final static String TABLE_NAME = "cod_sys_file";

    /**
     * 主键
     */
    private String id;

    /**
     * 文件名称
     */
    private String file_name;

    /**
     * 文件相对路径
     */
    private String file_url;

    /**
     * 文件大小
     */
    private String file_size;

    /**
     * 文件大小单位
     */
    private String file_unit;

    /**
     * 扩展名
     */
    private String ext_name;

    /**
     * 文件地址代码
     */
    private String file_item_code;

    /**
     * 上传时间
     */
    private String upload_time;

    /**
     * 文件状态
     * -1: 删除; (定期清理)
     * 1: 正常;
     */
    private String status;



}
