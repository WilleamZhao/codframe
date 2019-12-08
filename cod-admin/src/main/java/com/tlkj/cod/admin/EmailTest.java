package com.tlkj.cod.admin;

import com.tlkj.cod.admin.service.CodAdminSendEmailService;
import com.tlkj.cod.admin.service.impl.CodAdminSendEmailServiceImpl;

/**
 * @ClassName: EmailTest
 * @Description: TODO
 * @Author yjk
 * @Date 2019/6/5 3:16 PM
 */
public class EmailTest {


    public static void main(String[] args) {
        CodAdminSendEmailService emailService;
        emailService = new CodAdminSendEmailServiceImpl();
        String filename = "/Users/yujinkai/Downloads/报销表.xlsx";
        boolean b = emailService.sendEmail("18614234320@163.com", "测试", "23额威风威风威风", filename);
    }
}
