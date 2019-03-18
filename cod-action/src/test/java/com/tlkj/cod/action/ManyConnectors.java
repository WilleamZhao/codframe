package com.tlkj.cod.action;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className ManyConnectors
 * @date 2018/11/2 下午11:09
 */
public class ManyConnectors {
    public static void main(String[] args) {
        ServerConnector connector0 = new ServerConnector(new Server());

        connector0.setPort(8080);

        // connector0.setMaxIdleTime(30000);

        //connector0.setRequestHeaderSize(8192);



        ServerConnector connector1 = new ServerConnector(new Server());

        connector1.setHost("127.0.0.1");

        connector1.setPort(8888);

        // connector1.setThreadPool(new QueuedThreadPool(20));

        connector1.setName("admin");


    }
}
