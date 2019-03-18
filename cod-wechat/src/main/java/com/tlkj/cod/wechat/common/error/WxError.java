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

import com.tlkj.cod.wechat.common.WxType;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Desc 微信错误码.
 * 请阅读：
 * 公众平台：<a href="https://mp.weixin.qq.com/wiki">全局返回码说明</a>
 * 企业微信：<a href="https://work.weixin.qq.com/api/doc#90000/90139/90313">全局错误码</a>
 *
 * @author sourcod
 * @version 1.0
 * @className WxError
 * @date 2019/3/12 4:03 PM
 */
public class WxError implements Serializable {

    private static final long serialVersionUID = 189957421942953811L;

    /**
     * 微信错误代码.
     */
    private int errorCode;

    /**
     * 微信错误信息.
     * （如果可以翻译为中文，就为中文）
     */
    private String errorMsg;

    /**
     * 微信接口返回的错误原始信息（英文）.
     */
    private String errorMsgEn;

    private String json;

    public static WxError fromJson(String json) {
        return fromJson(json, null);
    }

    public static WxError fromJson(String json, WxType type) {
        /*
        final WxError wxError = WxGsonBuilder.create().fromJson(json, WxError.class);
        if (StringUtils.isNotEmpty(wxError.getErrorMsg())) {
            wxError.setErrorMsgEn(wxError.getErrorMsg());
        }

        if (type == null) {
            return wxError;
        }

        if (type == WxType.MP) {
            final String msg = WxMpErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
            if (msg != null) {
                wxError.setErrorMsg(msg);
            }
        } else if (type == WxType.CP) {
            final String msg = WxCpErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
            if (msg != null) {
                wxError.setErrorMsg(msg);
            }
        } else if (type == WxType.MiniApp) {
            final String msg = WxMaErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
            if (msg != null) {
                wxError.setErrorMsg(msg);
            }
        }
        return wxError;
        */
        return null;
    }

    @Override
    public String toString() {
        if (this.json != null) {
            return this.json;
        }
        return "错误: Code=" + this.errorCode + ", Msg=" + this.errorMsg;
    }
}
