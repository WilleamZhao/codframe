package com.tlkj.cod.data.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc cod-data 配置Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataConfigDto
 * @date 2019/8/13 4:51 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodDataConfigDto extends CodCommonModelConvert {

    @JsonProperty("id")
    private String id;

    @JsonProperty("key")
    private String cKey;

    @JsonProperty("value")
    private String cValue;

    @JsonProperty("name")
    private String cName;

    @JsonProperty("desc")
    private String cDesc;

    @JsonProperty("sort")
    private int sort;

    @JsonProperty("createTime")
    private String createTime;

    @JsonProperty("updateTime")
    private String updateTime;

}
