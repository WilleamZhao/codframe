/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.security.util;

import com.tlkj.cod.core.model.dto.CodCorePermissionItemDto;
import com.tlkj.cod.core.model.enums.PermissionCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Desc 权限Util
 *
 * @author sourcod
 * @version 1.0
 * @className PermissionUtil
 * @date 2018/12/13 7:04 PM
 */
public class PermissionUtil {

    /**
     * 判断是否有权限
     * @param permissionNum 权限数(大权限)
     * @param num           权限数(要判断的权限)
     * @return 是否有权限
     */
    public static boolean hasPermission(int permissionNum, int num){
        return (permissionNum & num) != 0;
    }

    /**
     * 判断是否有权限
     * @param permissionNum 权限数
     * @return 是否有权限
     */
    public static boolean hasPermission(int permissionNum, String permissionName){
        int num = getPermissionNum(permissionName);
        if (num == 0){
            return false;
        }
        return (permissionNum & num) != 0;
    }

    /**
     * 添加权限
     * @param permissionNum  现在的权限
     * @param permissionName 要添加的权限
     * @return 添加后的权限
     */
    public static int addPermission(int permissionNum, String permissionName){
        int num = getPermissionNum(permissionName);
        return permissionNum | num;
    }

    /**
     * 删除权限
     * @param permissionNum  现在的权限
     * @param permissionName 要删除的权限
     * @return 删除后的权限
     */
    public static int delPermission(int permissionNum, String permissionName){
        int num = getPermissionNum(permissionName);
        return permissionNum & (~num);
    }

    /**
     * 获取权限
     * @param permissionName 权限名称
     * @return 权限数
     */
    public static int getPermissionNum(String permissionName){
        int num = 0;
        try {
            num = PermissionCode.valueOf(permissionName.toUpperCase()).getPermissionNum();
        } catch (IllegalArgumentException e){
            System.err.println("权限不存在");
            return 0;
        }
        return num;
    }

    /**
     * 获取权限名称
     * @param permissionName 权限名称
     * @return 权限名称
     */
    public static String getPermissionName(String permissionName){
        String name;
        try {
            name = PermissionCode.valueOf(permissionName.toUpperCase()).getPermissionName();
        } catch (IllegalArgumentException e){
            System.err.println("权限不存在");
            return "";
        }
        return name;
    }

    /**
     * 获取权限名称
     * @param permissionName 权限名称
     * @return 权限列表
     */
    public static String getName(String permissionName){
        String name;
        try {
            name = PermissionCode.valueOf(permissionName.toUpperCase()).name();
        } catch (IllegalArgumentException e){
            System.err.println("权限不存在");
            return "";
        }
        return name;
    }

    /**
     * 获取权限列表
     * @param permissionNum 权限
     * @param type          权限类型: 0: 基础权限; 1: 自定义权限;
     * @return 权限列表
     */
    public static List<CodCorePermissionItemDto> getPermission(int permissionNum, int type){
        List<CodCorePermissionItemDto> maps = new ArrayList<>();
        for (PermissionCode code : PermissionCode.values()){
            if (hasPermission(permissionNum, code.name()) && code.getPermissionType() == type){
                CodCorePermissionItemDto codFramePermissionDto = new CodCorePermissionItemDto();
                codFramePermissionDto.setName(code.getPermissionName());
                codFramePermissionDto.setNum(code.getPermissionNum());
                codFramePermissionDto.setCode(code.name().toLowerCase());
                codFramePermissionDto.setState(true);
                maps.add(codFramePermissionDto);
            }
        }
        return maps;
    }

    /**
     * 获取权限列表
     * @param permissionNum 权限
     * @return 权限列表
     */
    public static List<CodCorePermissionItemDto> getPermission(int permissionNum){
        return getPermission(permissionNum, 0);
    }

    /**
     * 获取全部权限
     * @return 权限列表
     */
    public static List<CodCorePermissionItemDto> getAllPermission(){
        return getPermission(getMaxPermission());
    }


    /**
     * 标识已有权限
     * @return 标识后权限
     */
    public static List<CodCorePermissionItemDto> intersect(List<CodCorePermissionItemDto> bos){
        for (CodCorePermissionItemDto bo : bos){
            if (hasPermission(getMaxPermission(), bo.getNum())){
                bo.setState(true);
            }
        }
        return bos;
    }

    /**
     * 转换权限
     * @param permissionNum 权限数
     * @return 转换后对象
     */
    public static List<CodCorePermissionItemDto> convertPermission(int permissionNum){
        List<CodCorePermissionItemDto> list = new ArrayList<>();
        // 获取最大权限
        for (CodCorePermissionItemDto code : getPermission(getMaxPermission())){
            if (hasPermission(permissionNum, code.getNum())){
                code.setState(true);
                list.add(code);
            } else {
                code.setState(false);
                list.add(code);
            }
        }
        return list;
    }



    /**
     * 获取最大权限
     * @return 最大权限数
     */
    private static int getMaxPermission(){
         return Arrays.stream(PermissionCode.values()).filter(permissionCode -> (permissionCode.getPermissionType() == 0)).mapToInt(PermissionCode::getPermissionNum).sum();
    }
}
