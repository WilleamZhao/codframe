/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * 流工具类
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className IOUtil
 * @date 2018/8/12 下午10:47
 */
public class CodCommonIO {

    /**
     * 判断路径是否是http/https请求
     * @param url 地址
     * @return
     */
    public static boolean IsHttp(String url){
        return StringUtils.isNotBlank(url) && url.startsWith("http");
    }

    /**
     * upload file to network folder
     * @param href network address
     * @param io   inputStream
     * @since 1.8+
     * @throws IOException If the first byte cannot be read for any reason
     * other than the end of the file, if the input stream has been closed, or
     * if some other I/O error occurs.
     */
    public static void outputHttpFile(String href, InputStream io) throws IOException {
        URL url = new URL(href);
        InputStream inputStream = url.openStream();
        outputFile("", inputStream);
    }

    /**
     * upload file to location folder
     * @param path path
     * @param io   inputStream
     * @throws IOException If the first byte cannot be read for any reason
     * other than the end of the file, if the input stream has been closed, or
     * if some other I/O error occurs.
     */
    public static void outputFile(String path, InputStream io) throws IOException {
        outputFile(path, "", io);
    }


    public static void outputFile(String path, String content) throws IOException {
        outputFile(path, "", new ByteArrayInputStream(content.getBytes()));
    }

    public static void outputFile(String path, String fileName, String content, boolean append) throws IOException {
        outputFile(path, fileName, content.getBytes(), append);
    }

    public static void outputFile(String path, String fileName, InputStream io) throws IOException {
        outputFile(path, fileName, io, 1024);
    }

    public static void outputFile(String path, String fileName, InputStream io, int byteLength) throws IOException {
        outputFile(path, fileName, io, byteLength, false);
    }

    public static void outputFile(String path, String fileName, InputStream io, int byteLength, boolean append) throws IOException {
        if (!createPath(path)){
            return;
        }
        if (!fileName.startsWith("/")){
            fileName = "/" + fileName;
        }
        OutputStream output = new FileOutputStream(path + fileName, append);
        byte[] buff = new byte[byteLength];
        while (io.read(buff, 0, byteLength) > 0) {
            output.write(buff);
        }
        output.flush();
        output.close();
    }

    public static void outputFile(String path, String fileName, byte[] io, boolean append) throws IOException {
        if (!createPath(path)){
            return;
        }
        if (!fileName.startsWith("/")){
            fileName = "/" + fileName;
        }
        OutputStream output = new FileOutputStream(path + fileName, append);
        output.write(io);
        output.flush();
        output.close();
    }

    /**
     * 创建文件夹
     * @param path 路径
     * @return 是否创建成功
     */
    public static boolean createPath(String path){
        boolean isOk;
        File fileDir = new File(path);
        //如果不存在 则创建
        if (!fileDir.exists()) {
            isOk = fileDir.mkdirs();
        } else {
            return true;
        }
        return isOk;
    }

    /**
     * 获取输入流
     * @param url 路径
     * @return 输入流
     * @throws IOException IOException
     * @throws URISyntaxException Url地址不正确
     */
    private InputStream getInputStream(String url) throws IOException, URISyntaxException {
        InputStream is = null;
        if (IsHttp(url)){
            HttpEntity httpEntity = null;
            HttpResponse httpResponse;
            if (url.startsWith("http")){
                if (url.startsWith("https")){
                    httpResponse = CodCommonHttpClient.httpGet(url);
                    if (httpResponse.getStatusLine().getStatusCode() == 200 || httpResponse.getStatusLine().getStatusCode() == 206){
                        httpEntity = httpResponse.getEntity();
                    }
                } else {
                    httpResponse = CodCommonHttpClient.httpGet(url);
                    if (httpResponse.getStatusLine().getStatusCode() == 200 || httpResponse.getStatusLine().getStatusCode() == 206){
                        httpEntity = httpResponse.getEntity();
                    }
                }
                if (httpEntity == null){
                    return null;
                }
                is = httpEntity.getContent();
                if (is != null){
                    return is;
                }
            }
        } else {
            is = getInputStream(new File(url));
            return is;
        }

        return null;
    }


    private InputStream getInputStream(File file) throws IOException {
        return FileUtils.openInputStream(file);
    }

    /**
     * inputStream转String
     * gzip格式自动转换
     *
     * @author zcj
     * @time 2016-12-07 16:08:10
     * @param ips
     * @return 转换后InputStream
     */
    public static String getString(InputStream ips) {
        // 取前两个字节
        byte[] header = new byte[2];
        try {
            BufferedInputStream bis = new BufferedInputStream(ips);
            bis.mark(2);
            int result = bis.read(header);
            // reset输入流到开始位置
            bis.reset();
            // 判断是否是GZIP格式
            int ss = (header[0] & 0xff) | ((header[1] & 0xff) << 8);
            if (result != -1 && ss == GZIPInputStream.GZIP_MAGIC) {
                System.out.println("是gzip格式");
                // logger.info("是gzip格式");
                return CodCommonIO.readInputStream(new GZIPInputStream(bis));
            }
            return CodCommonIO.readInputStream(bis);
        } catch (IOException e) {
            // logger.error("判断是否是gzip异常", e);
            return null;
        }
    }

    /**
     * 处理返回文件流
     *
     * @author willeam
     * @time 2016-12-07 16:13:26
     * @param is
     * @return
     * @throws IOException
     */
    private static String readInputStream(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line + "\n");
        }
        return buffer.toString();
    }
}
