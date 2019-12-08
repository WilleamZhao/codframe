/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Desc 输出LOGO信息
 *
 * @author sourcod
 * @version 1.0
 * @className LogoListener
 * @date 2018/6/30 下午5:18
 */
public class LogoListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(LogoListener.class);

    /**
     *
     * desc TODO 可以绑定到license授权文件输出授权信息
     * @author sourcod
     * @date 2018/6/30
     * @param sce
     * @return void
     **/
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InputStream reader = this.getClass().getResourceAsStream("/banner");
        BufferedReader br = new BufferedReader(new InputStreamReader(reader));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            br.close();
        } catch (IOException e){
            logger.error("LOGO错误");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
