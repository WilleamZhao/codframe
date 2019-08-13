/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.search.solr.model;

import org.apache.solr.common.SolrInputField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className SolrModel
 * @date 2019/2/21 8:02 PM
 */
public class SolrModel {

    /**
     * 转换Model
     */
    public Map convertModel(){
        Map<String, SolrInputField> fields = new HashMap<>();
        Field[] fields1 = this.getClass().getDeclaredFields();
        for (Field field : fields1){
            org.apache.solr.client.solrj.beans.Field field1 = field.getAnnotation(org.apache.solr.client.solrj.beans.Field.class);
            if (field1 != null){
                try {
                    SolrInputField solrInputField = new SolrInputField(field.getName());
                    solrInputField.setValue(this.getClass().getDeclaredMethod("get" + toUpperCaseFirstOne(field.getName())).invoke(this));
                    fields.put(field.getName(), solrInputField);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return fields;
    }

    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
