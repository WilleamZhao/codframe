/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.entity;

/**
 * Desc 公司表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameCompanyDo
 * @date 2018/10/30 下午2:47
 */
public class CodFrameCompanyDo {

    public static final String TABLE_NAME = "cod_sys_company";

    /**
     * 主键
     */
    private String id;

    /**
     * 公司名称
     */
    private String company_name;

    /**
     * 公司昵称
     */
    private String company_nickname;

    /**
     * 公司编号
     */
    private String company_no;

    /**
     * 公司联系方式
     */
    private String company_contact;

    /**
     * 公司传真
     */
    private String company_fax;

    /**
     * 公司联系电话
     */
    private String company_phone;

    /**
     * 税号
     */
    private String company_ein;

    /**
     * 公司地址
     */
    private String company_address;

    /**
     * 状态
     */
    private String state;

    /**
     * 排序
     */
    private String sort;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_nickname() {
        return company_nickname;
    }

    public void setCompany_nickname(String company_nickname) {
        this.company_nickname = company_nickname;
    }

    public String getCompany_no() {
        return company_no;
    }

    public void setCompany_no(String company_no) {
        this.company_no = company_no;
    }

    public String getCompany_contact() {
        return company_contact;
    }

    public void setCompany_contact(String company_contact) {
        this.company_contact = company_contact;
    }

    public String getCompany_fax() {
        return company_fax;
    }

    public void setCompany_fax(String company_fax) {
        this.company_fax = company_fax;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getCompany_ein() {
        return company_ein;
    }

    public void setCompany_ein(String company_ein) {
        this.company_ein = company_ein;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
