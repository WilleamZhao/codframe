package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 登陆Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminLoginDto
 * @date 2019/8/12 7:53 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminLoginDto implements Serializable {

    /**
     * 描述
     */
    private String msg;

    /**
     * token
     */
    private String token;

    /**
     * code
     */
    private String code;

}
