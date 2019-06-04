package com.tlkj.cod.dao.model;

import com.tlkj.cod.dao.annotation.Table;

/**
 * Desc 附件表
 *
 * @author sourcod
 * @version 1.0
 * @className CodSysAttachment
 * @date 2019/5/28 8:38 AM
 */
@Table(name = "cod_sys_attachment")
public class CodSysAttachment {

    private String id;
    private String url;

    /**
     *
     */
    private String head_code;
    private String file_size;
    private String file_unit;
    private String file_type;
    private String file_extname;
    private String upload_ip;
    private String upload_date;
    private String user_id;
    private String file_name;
    private String user_name;
    private String file_status;
    private String sort;
    private String create_time;
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHead_code() {
        return head_code;
    }

    public void setHead_code(String head_code) {
        this.head_code = head_code;
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

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_extname() {
        return file_extname;
    }

    public void setFile_extname(String file_extname) {
        this.file_extname = file_extname;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFile_status() {
        return file_status;
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
}
