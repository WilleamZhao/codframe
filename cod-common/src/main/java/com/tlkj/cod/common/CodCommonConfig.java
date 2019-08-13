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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


/**
 * 配置文件工具类
 * 
 * @author sourcod
 * @time 2016-12-07 10:10:59
 */
public class CodCommonConfig {

	private static final Logger logger = LoggerFactory.getLogger(CodCommonConfig.class);

	private static Properties prop = new Properties();
	private static Yaml yaml = new Yaml();
	static private Map map = new HashMap();

	static {
		try {
			logger.info("开始加载配置文件！");
			// 是不是classpath路径
			boolean isClassPath = true;
			// 获取config配置文件夹路径
			File file = new File("conf");
			if (file.listFiles() == null || file.listFiles().length == 0){
				file = new File(CodCommonConfig.class.getResource("/conf").getPath());
				isClassPath = false;
			}

			for (File f : Objects.requireNonNull(file.listFiles())) {
				// 只加载properties配置文件
				if (f.getName().contains(".properties")) {
					InputStream in = isClassPath ? new FileInputStream("conf/" + f.getName()) : CodCommonConfig.class.getClassLoader().getResourceAsStream("conf/" + f.getName());
					prop.load(in);
					logger.info("加载配置文件" + f.getName() + "成功！");
				}

				if (f.getName().contains(".yml")) {
					InputStream in = isClassPath ? new FileInputStream("conf/" + f.getName()) : CodCommonConfig.class.getClassLoader().getResourceAsStream("conf/" + f.getName());
					map = (Map) yaml.load(in);
					logger.info("加载配置文件" + f.getName() + "成功！");
				}
			}
		} catch (Exception e) {
			logger.error("读取配置文件错误", e);
		}
	}

	public static String getValueByKey(String key) {
		if (StringUtils.isEmpty(key)){
			return null;
		}
		String propKey = prop.getProperty(key);
		if (StringUtils.isNotEmpty(propKey)){
			return propKey;
		}
		String[] strings = key.split("\\.");
		Map tempMap = map;
		for (int i = 0; i < strings.length; i++){
			if (i < strings.length - 1){
				tempMap = (Map) tempMap.get(strings[i]);
			} else {
				propKey = tempMap.get(strings[i]) != null ? tempMap.get(strings[i]).toString() : "";
			}
		}
		// key   key;
		return propKey;
	}
}
