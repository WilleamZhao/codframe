/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className DispatcherServlet
 * @date 2019/4/23 3:58 PM
 */
@WebServlet(value = "/*", loadOnStartup = 1, name = "dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("asdasdasdasd");
        super.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("asdasdasdasd");


        super.init();
    }
}
