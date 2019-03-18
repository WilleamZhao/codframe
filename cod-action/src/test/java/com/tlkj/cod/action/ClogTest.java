/*
 * Copyright (c) 2018-2018.
 * Beijing blue sky technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action;

import org.junit.Test;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className ClogTest
 * @date 2018/12/25 1:01 PM
 */
public class ClogTest {

    @Test
    public void log(){
        String[] s = new String[]{"a","b","c","d"};
        String str = String.join("/", s);
        System.out.println(str);
    }
}
