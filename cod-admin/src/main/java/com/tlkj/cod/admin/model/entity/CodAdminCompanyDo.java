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

import java.io.Serializable;

/**
 * Desc 公司表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminCompanyDo
 * @date 2018/10/30 下午2:47
 */
@Getter
@Setter
public class CodAdminCompanyDo extends CodCommonModelConvert implements Serializable {

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

}
