/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.filter;

import com.tlkj.cod.common.CodCommonNetWork;
import com.tlkj.cod.core.spring.SpringContextUtil;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.bo.CodFrameDictItemBo;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.core.SystemSetAllowDisable;
// import com.tlkj.cod.service.system.DictService;
// import com.tlkj.cod.service.system.SystemSetService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Desc 黑白名单过滤器
 *
 * @author sourcod
 * @version 1.0
 * @className AllowDisableFilter
 * @date 2018/12/26 5:10 PM
 */
public class AllowDisableFilter extends GeneralResponse implements Filter {

    /**
     * 黑名单list
     */
    //private static List<CodFrameDictItemBo> blackList = null;

    private static String[] excludedPageArray;

    /**
     * 匹配所有
     */
    private static String ALL_IP = "0.0.0.0";

    /*private static SystemSetService systemSetService = null;

    private static DictService dictService = null;*/

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludedPages = filterConfig.getInitParameter("excludedPages");
        if (StringUtils.isNotBlank(excludedPages)){
            excludedPageArray = excludedPages.split(",");
        }

        /*if (systemSetService == null){
            systemSetService = (SystemSetService) SpringContextUtil.getBean("systemSetServiceImpl");
        }

        if (dictService == null){
            dictService = (DictService) SpringContextUtil.getBean("dictServiceImpl");
        }

        SystemSetAllowDisable allowDisable = SystemModel.getInstance().getAllowDisable();

        if (allowDisable == null){
            allowDisable = new SystemSetAllowDisable();
            allowDisable.setWhiteList(dictService.getItemByType("white-set").stream().map(CodFrameDictItemBo::getValue).collect(Collectors.toList()));
            allowDisable.setBlackList(dictService.getItemByType("black-set").stream().map(CodFrameDictItemBo::getValue).collect(Collectors.toList()));
            allowDisable.setAllowDisable(systemSetService.getSetValue("allow_disable"));
            SystemModel.getInstance().setAllowDisable(allowDisable);
        }*/
        /*if (blackList == null){
            blackList = dictService.getItemByType("black-set");
        }
        if (whiteList == null){
            whiteList =
        }*/
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        boolean isExcludedPage = false;
        for (String page : excludedPageArray) {
            if (httpServletRequest.getPathInfo().contains(page)) {
                isExcludedPage = true;
                break;
            }
        }
        if (isExcludedPage) {
            chain.doFilter(request, response);
        } else {
            String realIp = CodCommonNetWork.getIpAddress(httpServletRequest);
            String bw = SystemModel.getInstance().getAllowDisable().getAllowDisable();
            if (StringUtils.isNotBlank(bw) && !"0".equals(bw)){

                // 黑名单
                boolean isBlack = false;
                if ("1".equals(bw) || "black".equals(bw) || "disable".equals(bw)){
                    for (String ipList : SystemModel.getInstance().getAllowDisable().getBlackList()){
                        String[] ips = ipList.split(",");
                        for (String ip : ips){
                            if (ip.contains(ALL_IP) || CodCommonNetWork.isInRange(realIp, ip)){
                                isBlack = true;
                                break;
                            }
                        }
                    }
                    // 禁止访问
                    if (isBlack){
                        response.getWriter().print(super.fail(StatusCode.IP_DISABLE_CODE));
                        return;
                    }
                }

                // 白名单
                boolean isWhite = true;
                if ("2".equals(bw) || "white".equals(bw) || "allow".equals(bw)){
                    for (String ipList : SystemModel.getInstance().getAllowDisable().getWhiteList()){
                        String[] ips = ipList.split(",");
                        for (String ip : ips){
                            if (ip.contains(ALL_IP) || CodCommonNetWork.isInRange(realIp, ip)){
                                isWhite = false;
                                break;
                            }
                        }
                    }
                    // 禁止访问
                    if (isWhite){
                        response.getWriter().print(super.fail(StatusCode.IP_DISABLE_CODE));
                        return;
                    }
                }
            }
            // 继续向下走
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
