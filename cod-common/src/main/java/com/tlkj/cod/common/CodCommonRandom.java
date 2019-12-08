/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.common;

import java.util.Random;

/**
 * Desc 随机数工具
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonRandom
 * @date 2019/11/7 8:04 PM
 */
public class CodCommonRandom {

    /**
     * 获取 区间 ( 包含 ) 随机数
     * @param minNum 最小数
     * @param maxNum 最大数
     * @return
     */
    public static int getRandom(int minNum, int maxNum) {
        Random random = new Random();
        return random.nextInt(maxNum - minNum + 1) + minNum;
    }

    /**
     * 获取指定长度随机数
     * @param size 位数
     * @return 指定长度随机数
     */
    public static String getRandomForSize(int size){
        int minNum = 1;
        StringBuilder max = new StringBuilder("9");
        for (int i = 1; i < size; i++) {
            max.append(9);
        }
        int maxNum = Integer.parseInt(max.toString());
        return completion(size, getRandom(minNum, maxNum));
    }

    /**
     * 用 0 补全位数
     * 如:
     * 1 -> 0001
     * 99 -> 0099
     * @param size 几位
     * @param num  数字
     * @return
     */
    private static String completion(int size, int num){
        String tempNum = String.valueOf(num);
        while (tempNum.length() < size){
            tempNum = "0" + tempNum;
        }
        return tempNum;
    }

    /**
     * 根据指定长度生成字母和数字的随机数
     * 0~9的ASCII为48~57
     * A~Z的ASCII为65~90
     * a~z的ASCII为97~122
     * @param length 指定长度
     * @return 指定长度随机字符串
     */
    public static String getRandomASCIIForSize(int length) {
        StringBuilder sb=new StringBuilder();
        //随机用以下三个随机生成器
        Random rand=new Random();
        Random randdata=new Random();
        int data=0;
        for(int i=0;i<length;i++)
        {
            int index=rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch(index)
            {
                case 0:
                    //仅仅会生成0~9
                    data=randdata.nextInt(10);
                    sb.append(data);
                    break;
                case 1:
                    //保证只会产生65~90之间的整数
                    data=randdata.nextInt(26)+65;
                    sb.append((char)data);
                    break;
                case 2:
                    //保证只会产生97~122之间的整数
                    data=randdata.nextInt(26)+97;
                    sb.append((char)data);
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++){
            String a = CodCommonRandom.getRandomForSize(3);
            int b = CodCommonRandom.getRandom(50, 60);
            String c = CodCommonRandom.getRandomASCIIForSize(4);
            System.out.println("指定位数 = " + a);
            System.out.println("指定范围 = " + b);
            System.out.println("指定范围ascii = " + c);
        }
    }
}
