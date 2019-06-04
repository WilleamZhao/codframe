package com.tlkj.cod.service.system;

/**
 * @ClassName: SendEmailService
 * @Description: 发送邮件
 * @Author yjk
 * @Date 2019/6/4 4:56 PM
 */
public interface SendEmailService {

    /**
     * 发送邮件
     *
     * @param toEmailAddress 收件箱地址
     * @param emailTitle 邮件主题
     * @param emailContent 邮件内容
     * @return
     */
    boolean sendEmail(String toEmailAddress, String emailTitle, String emailContent);

}
