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

/**
 * Desc 附件表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminAttachmentDo
 * @date 2018/11/15 10:26 PM
 */
public class CodAdminAttachmentDo {

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

    private String create_time;
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getFile_unit() {
        return file_unit;
    }

    public void setFile_unit(String file_unit) {
        this.file_unit = file_unit;
    }

    public String getFile_extname() {
        return file_extname;
    }

    public void setFile_extname(String file_extname) {
        this.file_extname = file_extname;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUpload_ip() {
        return upload_ip;
    }

    public void setUpload_ip(String upload_ip) {
        this.upload_ip = upload_ip;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getHead_code() {
        return head_code;
    }

    public void setHead_code(String head_code) {
        this.head_code = head_code;
    }

    public String getFile_status() {
        return file_status;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public void setFile_status(String file_status) {
        this.file_status = file_status;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
