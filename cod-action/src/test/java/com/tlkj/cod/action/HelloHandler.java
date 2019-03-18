package com.tlkj.cod.action;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className com.tlkj.cod.action.HelloHandler
 * @date 2018/11/2 下午11:03
 */
public class HelloHandler extends AbstractHandler {


        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println("<h1>HelloWorld</h1>");

        }

}
