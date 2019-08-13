package com.tlkj.cod.test.api;

import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * Desc jvm 测试接口
 *
 * @author sourcod
 * @version 1.0
 * @className JvmTestApi
 * @date 2019/5/28 10:04 AM
 */
@RestController
@RequestMapping("test/jvm")
public class CodTestJvmApi extends GeneralResponse {

    /**
     * 获取临时目录
     * @return
     */
    @RequestMapping("temp/dir")
    public Response getTempDir(){
        Properties props = System.getProperties();
        return success(props.getProperty("java.io.tmpdir"));
    }
}
