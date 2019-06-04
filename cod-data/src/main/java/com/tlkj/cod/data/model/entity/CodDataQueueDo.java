package com.tlkj.cod.data.model.entity;

import com.tlkj.cod.dao.annotation.CodField;
import com.tlkj.cod.dao.annotation.CodTable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc codFrame 队列表
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataQueueDo
 * @date 2019/6/4 9:29 PM
 */
@Getter
@Setter
@CodTable(name = "cod_core_queue", comment = "队列表")
public class CodDataQueueDo implements Serializable {

    public static final String TABLE_NAME = "cod_core_queue";

    /**
     * 主键
     */
    @CodField(type = "varchar(32)", isNull = false, comment = "主键")
    private String id;

    /**
     * 状态
     * 1: 可处理
     * 2: 被占用(正在处理)
     * 3: 处理完毕
     */
    @CodField(type = "tinyint(1)", isNull = false, comment = "状态")
    private String state;

    /**
     * 消费状态
     * 1: 未消费
     * 2: 消费成功
     * 3: 消费失败，等待下次消费
     * 4: 作废
     */
    @CodField(type = "tinyint(1)", comment = "消费状态")
    private String consume_state;

    /**
     * 消费次数
     */
    @CodField(type = "int", comment = "消费次数")
    private int consume_count;

    /**
     * 创建时间
     */
    @CodField(type = "timestamp", def = "CURRENT_TIMESTAMP", isNull = false, comment = "创建时间")
    private String create_time;

    /**
     * 更新时间
     */
    @CodField(type = "datetime", comment = "更新时间")
    private String update_time;

    /**
     * 最后一次消费时间
     *
     */
    @CodField(type = "datetime", comment = "最后一次消费时间")
    private String last_consume_time;

    /**
     * 下一次消费时间
     * 可消费开始时间
     */
    @CodField(type = "datetime", comment = "可消费开始时间")
    private String next_consume_time;



    /**
     * 数据信息
     * json格式
     */
    @CodField(type = "text", comment = "数据信息")
    private String data;

}
