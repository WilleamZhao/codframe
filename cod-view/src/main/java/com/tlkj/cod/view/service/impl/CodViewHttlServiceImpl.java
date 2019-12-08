/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.view.service.impl;

import com.tlkj.cod.view.service.CodViewHttlService;
import httl.Engine;
import httl.Template;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc httl 实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodViewHttlServiceImpl
 * @date 2019/9/26 10:30 PM
 */
@Service
public class CodViewHttlServiceImpl implements CodViewHttlService {

    public static void main(String[] args) {
        CodViewHttlServiceImpl codViewHttlService = new CodViewHttlServiceImpl();
        codViewHttlService.parse("/books.httl");
    }

    @Override
    public String parse(String txt) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user", "123");
        parameters.put("book", "456");

        Engine engine = Engine.getEngine();
        String a = "";
        OutputStream outputStream = null;
            outputStream = new ByteArrayOutputStream();
        try {
            Template template = engine.getTemplate(txt, parameters);
            template.render(parameters, outputStream);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        // BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        PrintStream printStream = new PrintStream(outputStream);
        printStream.print(1);
        System.out.println(a);
        return null;
    }
}
