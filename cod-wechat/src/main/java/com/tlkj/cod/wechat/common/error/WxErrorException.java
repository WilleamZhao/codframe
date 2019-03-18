/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.wechat.common.error;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className WxErrorException
 * @date 2019/3/12 4:12 PM
 */
public class WxErrorException extends Exception {

    private WxError error;

    public WxErrorException(WxError error) {
        super(error.toString());
        this.error = error;
    }

    public WxErrorException(WxError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public WxError getError() {
        return this.error;

    }
}
