/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.common;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Desc 验证码工具类
 *
 * @author sourcod
 * @version 1.0
 * @className VerifyCodeUtil
 * @date 2018/7/2 下午2:25
 */
public class CodCommonVerifyCode {

    private static Random random = new Random();

    /**
     * desc 定义图片的width
     */
    private static int width = 120;

    /**
     * desc 定义图片的height
     */
    private static int height = 40;

    /**
     * desc 定义图片上显示验证码的个数
     */
    private static int codeCount = 4;

    /**
     * desc 左侧距离
     **/
    private static int xx = 20;

    /**
     * desc 字体大小
     **/
    private static int fontHeight = 25;

    /**
     * desc 上侧距离
     **/
    private static  int codeY = 30;
    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 生成验证码
     */
    public static String generateVerifyCode(){
        StringBuffer sb = new StringBuffer();
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            // 将产生的四个随机数组合在一起。
            sb.append(code);
        }
        return sb.toString();
    }

    /**
     * 生成验证码并上传OSS
     * @return 验证码, 验证码地址
     */
    public static InputStream createCode(String code) throws IOException {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 设置背景色
        gd.setColor(getRandColor(200, 250));
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("宋体", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);

        // desc 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        int red, green, blue;

        //使用string类型的tocharArray（）方法进行转换。
        char[] codes = code.toCharArray();
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            Color c = new Color(red, green, blue);
            gd.setColor(c);
            gd.drawString(String.valueOf(codes[i]), (i + 1) * xx, codeY);

        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "jpg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;
    }

    /**
     * 给定范围获得随机颜色
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static void main(String[] args) throws Exception {
        //创建文件输出流对象
        OutputStream out = new FileOutputStream("/Users/sourcod/"+System.currentTimeMillis()+".jpg");
        String code = CodCommonVerifyCode.generateVerifyCode();
        InputStream is = CodCommonVerifyCode.createCode(CodCommonVerifyCode.generateVerifyCode());
        // String url = OSSUtil.uploadOSS(String.valueOf(CodCommonDate.now().getTime()) + ".jpg", is);
        System.out.println("验证码的值为："+ code);
        // System.out.println("验证码的路径为："+ url);
    }
}
