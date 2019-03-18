/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.apple.model.enums;

/**
 * Desc 验证
 *
 * @author sourcod
 * @version 1.0
 * @className VerifyReceiptEnum
 * @date 2019/2/27 5:41 PM
 */
public enum VerifyReceiptEnum {

    A0(0, 0, "成功", "success"),
    A1(21000, 0, "App Store无法读取你提供的JSON数据", ""),
    A2(21002, 0, "收据数据不符合格式", ""),
    A3(21003, 0, "收据无法被验证", ""),
    A4(21004, 0, "你提供的共享密钥和账户的共享密钥不一致", ""),
    A5(21005, 0, "收据服务器当前不可用", ""),
    A6(21006, 0, "收据是有效的，但订阅服务已经过期。当收到这个信息时，解码后的收据信息也包含在返回内容中", ""),
    A7(21007, 0, "收据信息是测试用（sandbox），但却被发送到产品环境中验证", ""),
    A8(21008, 0, "收据信息是产品环境中使用，但却被发送到测试环境中验证", ""),
    A9(21010, 0, "App Store无法读取你提供的JSON数据", ""),
    A10(21100, 21199, "App Store无法读取你提供的JSON数据", "");

    /**
     * 错误码
     */
    private int errorNum;

    /**
     * 最大错误码
     */
    private int errorMaxNum;

    /**
     * 中文提示内容
     */
    private String zhContent;

    /**
     * 英文提示内容
     */
    private String enContent;


    VerifyReceiptEnum(int errorNum, int errorMaxNum, String zhContent, String enContent){
        this.errorNum = errorNum;
        this.errorMaxNum = errorMaxNum;
        this.zhContent = zhContent;
        this.enContent = enContent;
    }

    public static boolean verifyState(int stateNum){
        if (stateNum == 0){
            return true;
        }
        return false;
        /*
        VerifyReceiptEnum[] verifyReceiptEnums = VerifyReceiptEnum.values();
        for (VerifyReceiptEnum receiptEnum = verifyReceiptEnums){
            if (receiptEnum.errorNum == stateNum){
                return receiptEnum;
            }
        }
        */
    }
}
