package com.tlkj.cod.data.service.impl;

import com.tlkj.cod.data.service.CodDataService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc cod data service impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataServiceImpl
 * @date 2019/6/3 2:02 PM
 */
@Service
public class CodDataServiceImpl implements CodDataService {

    /**
     * 初始化
     */
    @Override
    public void init() {

    }

    /**
     * 配置
     */
    @Override
    public Map<String, String> config() {
        Map<String, String> map = new HashMap<>();

        map.put("a", "a");

        return map;
    }

    /**
     * 获取数据
     */
    @Override
    public void getData() {

    }

    /**
     * 设置数据
     */
    @Override
    public void setData() {

    }
}
