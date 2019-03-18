/*
 * Copyright (c) 2018-2018.
 * Beijing blue sky technology co., LTD.
 * All rights reserved
 */

package com.tlkj.cod.action;

import org.junit.Test;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className StringTest
 * @date 2018/12/6 9:52 AM
 */
public class StringTest {

    @Test
    public void join(){
        String[] s = new String[]{"a","b","c","d"};
        String str = String.join("/", s);
        System.out.println(str);
    }
}
