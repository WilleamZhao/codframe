/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc xml转换工具类
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonXml
 * @date 2019/5/21 5:04 PM
 */
public class CodCommonXml {

    public static String toXml(Object o) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //去掉默认报文头
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(o, writer);
        String result = writer.toString();
        System.out.println(result);
        return result;
    }

    public static <T> T toBean(String xml, Class<T> zlass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(zlass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return zlass.cast(unmarshaller.unmarshal(new StringReader(xml)));
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("a", "");
        map.put("b", "");
        map.put("c", "");
        try {
            CodCommonXml.toXml(map);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
