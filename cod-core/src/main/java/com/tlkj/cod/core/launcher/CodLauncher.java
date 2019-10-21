/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.launcher;

import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.core.common.CodCoreFindClass;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.core.main.CodSpringConfiguration;
import com.tlkj.cod.launcher.exception.CodModuleStartFailException;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.env.MapPropertySource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Desc codFrame框架 启动引导
 *
 * @author sourcod
 * @version 1.0
 * @className com.tlkj.cod.launcher.CodLauncher
 * @date 2019/4/25 4:51 PM
 */
public class CodLauncher {

    // private static List<Integer> list = new ArrayList<>();
    // private static LinkedList<CodModuleInitialize> linkedList = new LinkedList<>();

    private static List list = new ArrayList();
    private static final CodModuleLauncherModel LAUNCHER_MODEL = CodModuleLauncherModel.getInstance();
    private static Logger logger = LoggerFactory.getLogger(CodLauncher.class);

    /**
     * 1. 加载模块
     * 2. 加载server init
     * 3. 顺序执行
     * 4. 启动完成
     *
     * @param args
     */
    public static void main(String[] args) {
        logger.info("开始启动codFrame");
        Date startDate = CodCommonDate.now();

        if (LAUNCHER_MODEL.isStart()){
            return;
        }
        CodModuleInitialize codModuleInitialize = null;

        LinkedList<CodModuleInitialize> linkedList = new LinkedList<>();

        // 插入排序
        List<Integer> list = new ArrayList<>();
        try {
            for (Class<?> c : CodCoreFindClass.getAllAssignedClass(CodModuleInitialize.class, "com.tlkj.cod")) {
                codModuleInitialize = (CodModuleInitialize) c.newInstance();
                int order = codModuleInitialize.order();
                if (!CodModuleOrderEnum.isAvailable(order, c)){
                    continue;
                }

                // 不加载
                if (codModuleInitialize.order() == Integer.MIN_VALUE){
                    continue;
                }

                int i = 0;
                for (int o : list){
                    if (o < order){
                        i++;
                    }
                }
                list.add(order);
                linkedList.add(i, codModuleInitialize);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            System.out.println("启动异常");
            e.printStackTrace();
        }

        /*
         * 注入spring配置
         */
        LAUNCHER_MODEL.getSpring().register(CodSpringConfiguration.class);

        LAUNCHER_MODEL.getSpring().register(CommonAnnotationBeanPostProcessor.class);

        // TODO 验证license

        // 设置环境
        setEnv("dev");

        int i = 1;
        boolean isFinish = true;
        m : for (CodModuleInitialize module : linkedList){
            /*if (i > 6){
                break;
            }*/
            try {
                logger.info("开始启动 第 {} 个模块; 序号: {}; 名称: {}模块;", i, module.order(), getModelName(module));
                LAUNCHER_MODEL.getSpring().scan(module.name());
                module.init(LAUNCHER_MODEL);
                switch (LAUNCHER_MODEL.getStateEnum()){
                    case FAIL:
                        LAUNCHER_MODEL.fail();
                        throw new CodModuleStartFailException();
                    case STOP:
                        logger.info("停止启动模块{}", getModelName(module));
                        break m;
                    case SUCCESS:
                        LAUNCHER_MODEL.getSpring().refresh();
                        LAUNCHER_MODEL.next();
                        break;
                    case CONTINUE:
                        break;
                    default:
                        break;
                }
                // 调用成功方法
                module.success(LAUNCHER_MODEL);
                logger.info("启动 {} 模块完成", getModelName(module));
            } catch (Exception e) {
                // TODO cod set log Debug
                e.printStackTrace();
                module.fail(LAUNCHER_MODEL, e);
                switch (LAUNCHER_MODEL.getStateEnum()){
                    case STOP:
                        logger.error("{}模块 启动失败, 停止启动", getModelName(module));
                        isFinish = false;
                        break m;
                    case CONTINUE:
                        logger.error("{}模块 启动失败, 继续启动", getModelName(module));
                        break;
                    default:
                        logger.error("{}模块 启动失败, 继续启动", getModelName(module));
                        break;
                }
            }
            i++;
        }
        // 所有模块启动完成后, 结束刷新
        // LAUNCHER_MODEL.getSpring().refresh();
        logger.info("总模块数量: {}; 启动模块数量: {};", list.size(), i - 1);
        Date endDate = CodCommonDate.now();
        String diffTime =CodCommonDate.getTimeDifference(startDate, endDate);
        String time = CodCommonDate.formatDate(CodCommonDate.parseDate(diffTime, CodCommonDate.PATTERN_DIFF), "mm分:ss秒");
        if (isFinish){
            logger.info("codFrame 启动完成. 用时: {}", time);
        } else {
            logger.info("codFrame 启动失败. 用时: {}", time);
        }
    }

    /**
     * 获取模块名称
     * 有别名返回别名 没有返回name
     * @return 模块名称
     */
    private static String getModelName(CodModuleInitialize module){
        return StringUtils.isEmpty(module.alias()) ? module.name() : module.alias();
    }

    private void orderModule(int order, LinkedList list, CodModuleInitialize codModuleInitialize){
        // 插入排序
        List<Integer> list1 = new ArrayList<>();
        int i = 0;
        for (int o : list1){
            if (o < order){
                i++;
            }
        }
        list1.add(order);
        list.add(i, codModuleInitialize);
    }

    /**
     * 设置环境
     */
    private static void setEnv(String env){
        // 设置环境
        Map<String, Object> map = new HashMap<>(3);
        map.put("spring.profiles.active", env);
        map.put("spring.profiles.default", env);
        map.put("spring.liveBeansView.mbeanDomain", env);
        MapPropertySource mapPropertySource = new MapPropertySource("springMbean", map);
        LAUNCHER_MODEL.getSpring().getEnvironment().getPropertySources().addFirst(mapPropertySource);
    }

}
