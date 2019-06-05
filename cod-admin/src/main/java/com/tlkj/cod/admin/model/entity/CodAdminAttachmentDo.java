/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 附件表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminAttachmentDo
 * @date 2018/11/15 10:26 PM
 */
@Getter
@Setter
public class CodAdminAttachmentDo extends CodCommonModelConvert {

    public static final String TABLE_NAME = "cod_sys_attachment";

    /**
     * 主键
     */
    private String id;

    /**
     * 文件名称
     */
    private String file_name;

    /**
     * 路径头字典数据表code
     */
    private String head_code;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文件大小
     */
    private String file_size;

    /**
     * 文件单位
     * 1: byte
     * 2: kb
     * 3: mb (默认)
     * 4: gb
     * 5: tb
     */
    private String file_unit;

    /**
     * 文件扩展名
     */
    private String file_extname;

    /**
     * 上传用户id
     */
    private String user_id;

    /**
     * 上传人
     */
    private String user_name;

    /**
     * 文件状态 0: 删除; 1: 正常; -1: 彻底删除(默认60天后清理);
     */
    private String file_status;

    /**
     * 文件类型
     */
    private String file_type;

    /**
     * 排序
     */
    private String sort;

    /**
     * 上传ip
     */
    private String upload_ip;

    /**
     * 上传时间
     */
    private String upload_date;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;
}
