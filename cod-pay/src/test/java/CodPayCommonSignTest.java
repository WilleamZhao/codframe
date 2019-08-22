/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

import static org.junit.Assert.*;

import com.tlkj.cod.pay.common.CodPayCommonSign;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.Map;

/**
 * Desc 测试
 *
 * @author sourcod
 * @version 1.0
 * @className CodPayCommonSignTest
 * @date 2019/8/21 11:50 AM
 */
public class CodPayCommonSignTest {

    @Test
    public void testCreateWechatSign(){
        Map map = new HashedMap();
        map.put("a", "a");
        assertEquals("1687f564d39ec46ac69dab8da044cd3c", new CodPayCommonSign().createWechatSign(map, ""));
    }
}
