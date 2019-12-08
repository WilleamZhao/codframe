package com.tlkj.cod.dao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Desc Sql帮助文件
 *
 * @author sourcod
 * @version 1.0
 * @className SqlHelper
 * @date 2019/5/17 7:13 PM
 */
public class SqlHelper {

    /**
     * getText方法吧path路径里面的文件按行读数来放入一个大的String里面去
     * 并在换行的时候加入\r\n
     */
    public static String getText(String path){
        File file = new File(path);
        if(!file.exists()||file.isDirectory()){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try{
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String temp = null;
            temp = br.readLine();
            while(temp!=null){
                if(temp.length()>=2){
                    String str1 = temp.substring(0, 1);
                    String str2 = temp.substring(0, 2);
                    if("#".equals(str1) || "--".equals(str2) || "/*".equals(str2) || "//".equals(str2)){
                        temp = br.readLine();
                        continue;
                    }
                    sb.append(temp).append("\r\n");
                }

                temp = br.readLine();
            }
            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * getSqlArray方法
     * 从文件的sql字符串中分析出能够独立执行的sql语句并返回
     */
    public static List<String> getSql(String sql){
        String s = sql;
        s = s.replaceAll("\r\n", "\r");
        s = s.replaceAll("\r", "\n");
        //用;把所有的语句都分开成一个个单独的句子
        String[] sqlarry = s.split(";");
        sqlarry = filter(sqlarry);
        return Arrays.asList(sqlarry);
    }

    public static String[] filter(String[] ss){
        List<String> strs = new ArrayList<>();
        for(String s : ss){
            if(s!=null&&!s.equals("")){
                strs.add(s);
            }
        }
        String[] result = new String[strs.size()];
        for(int i=0; i<strs.size(); i++){
            result[i] = strs.get(i);
        }
        return result;
    }

}
