package com.tlkj.cod.launcher.model.enums;

/**
 * Desc 启动状态枚举
 *
 * @author sourcod
 * @version 1.0
 * @className LauncherStateEnum
 * @date 2019/5/31 4:25 PM
 */
public enum LauncherStateEnum {

    /**
     * 继续执行, 不论成功还是失败
     */
    CONTINUE,

    /**
     * 停止执行, 不论成功还是失败
     */
    STOP,

    /**
     * 失败不继续
     */
    FAIL,

    /**
     * 成功, 继续
     */
    SUCCESS
}
