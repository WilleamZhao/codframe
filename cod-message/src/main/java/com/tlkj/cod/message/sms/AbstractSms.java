/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.message.sms;

/**
 * Desc 短信抽象类
 *
 * @author sourcod
 * @version 1.0
 * @className AbstractSms
 * @date 2019/1/31 11:54 AM
 */
public abstract class AbstractSms implements SmsService {


    void send(String phone, String content){

    }


    void getService(String type){

    }

    /*private enum SMSType {
        ALI("ali", 0, AliSmsService),
        TENCENT("", 1, TencentSmsService);

        *//**
         * 状态码
         *//*
        private String name;

        *//**
         * 错误名称
         *//*
        private int state;

        private

        SMSType(String name, int state) {
            this.name = name;
            this.state = state;
        }

        public String getName() {
            return name;
        }

        public int getState() {
            return state;
        }
    }*/
}
