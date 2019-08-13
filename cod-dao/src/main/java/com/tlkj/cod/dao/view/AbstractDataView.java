/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.view;

import com.google.common.base.CaseFormat;
import com.tlkj.cod.dao.annotation.CodDaoViewColumn;
import com.tlkj.cod.dao.annotation.CodDaoViewJoin;
import com.tlkj.cod.dao.annotation.CodDaoViewTable;
import com.tlkj.cod.dao.annotation.CodDaoViewWhere;
import com.tlkj.cod.dao.exception.CodDataViewException;
import com.tlkj.cod.dao.jdbc.Finder;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Desc 视图Dao抽象类
 *
 * @author sourcod
 * @version 1.0
 * @className AbstractDataView
 * @date 2019/1/4 8:31 PM
 */
public abstract class AbstractDataView {

    /**
     * 获取表名
     * @return
     */
    public String getTable() {
        CodDaoViewTable codDaoViewTable = this.getClass().getDeclaredAnnotation(CodDaoViewTable.class);

        // 表名
        String name = "";
        // 别名
        String alias = "";
        // 表名
        String tableStr = "";

        // 注解为空取类名
        if (codDaoViewTable == null){
            name = getTableByModel(this.getClass());
            alias = getTableAlias(name);
        }

        if (codDaoViewTable != null){
            name = codDaoViewTable.name();

            if (StringUtils.isBlank(name)){
                String value = getTableByModel(this.getClass());
                if (StringUtils.isNotBlank(value)){
                    name = value;
                }
            }
            alias = StringUtils.isNotBlank(codDaoViewTable.alias()) ? codDaoViewTable.alias() : getTableAlias(name);
        }
        tableStr = name + " " + alias;
        return tableStr;
    }

    /**
     * 根据类名映射表名
     * @param
     * @return
     */
    private String getTableByModel(Class zlsss){
        return zlsss.getSimpleName();
    }

    /**
     * 根据注解获取join
     */
    public void getJoins(Finder.Query query) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields){
            CodDaoViewJoin codDaoViewJoin = field.getAnnotation(CodDaoViewJoin.class);
            if (codDaoViewJoin != null){
                String table = codDaoViewJoin.table();
                String alias = codDaoViewJoin.alias();
                String joinStr = codDaoViewJoin.join();
                String[] ons = codDaoViewJoin.on();
                // 判断on是否规范
                if (!isOn(ons)){
                    throw new CodDataViewException();
                }
                query.join(sb.append(" ").append(joinStr).append(" ")
                        .append(table).append(" ")
                        .append(StringUtils.isNotBlank(alias) ? alias : getTableAlias(table)).append(getOn(ons)).toString());
                sb.setLength(0);
            }
        }

        Method[] methods = this.getClass().getDeclaredMethods();

        for (Method method : methods){
            CodDaoViewJoin codDaoViewJoin = method.getAnnotation(CodDaoViewJoin.class);
            if (codDaoViewJoin != null){
                String table = codDaoViewJoin.table();
                String alias = codDaoViewJoin.alias();
                String joinStr = codDaoViewJoin.join();
                String[] ons = codDaoViewJoin.on();
                // 判断on是否规范
                if (!isOn(ons)){
                    throw new CodDataViewException();
                }
                query.join(sb.append(" ").append(joinStr).append(" ")
                        .append(table).append(" ")
                        .append(StringUtils.isNotBlank(alias) ? alias : getTableAlias(table)).append(getOn(ons)).toString());
                sb.setLength(0);
            }
        }

    }

    /**
     * 根据Model获取joins
     */
    public String getJoinsByModel(CodDaoDataView view) {
        StringBuilder sb = new StringBuilder();
        for (JoinModel joinModel : view.getJoinModels()){
            String[] ons =  joinModel.getOn();
            // 判断on是否规范
            if (!isOn(ons)){
                throw new CodDataViewException();
            }
            sb.append(joinModel.getJoin()).append(" ")
                    .append(joinModel.getTable()).append(" ")
                    .append(joinModel.getAlias()).append(getOn(ons));
        }
        return sb.toString();
    }

    public void getSelect(Finder.Query query){
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields){
            CodDaoViewColumn codDaoViewColumn = field.getAnnotation(CodDaoViewColumn.class);
            if (codDaoViewColumn != null){
                sb.append(getTableAlias(codDaoViewColumn.tName()))
                        .append(".")
                        .append(codDaoViewColumn.cName())
                        .append(" as ")
                        .append(StringUtils.isNotBlank(codDaoViewColumn.aliasName()) ? codDaoViewColumn.aliasName() : field.getName());
                query.select(sb.toString());
                sb.setLength(0);
            }
        }
    }

    public void getSelectByModel(Finder.Query query, CodDaoDataView view){
        Field[] fields = view.getClass().getDeclaredFields();

        StringBuffer stringBuffer = new StringBuffer();
        for (Field field : fields){
            CodDaoViewColumn codDaoViewColumn = field.getAnnotation(CodDaoViewColumn.class);
            if (codDaoViewColumn !=null){
                stringBuffer.append(getTableAlias(codDaoViewColumn.tName()))
                        .append(".")
                        .append(codDaoViewColumn.cName())
                        .append(" as ")
                        .append(StringUtils.isNotBlank(codDaoViewColumn.aliasName()) ? codDaoViewColumn.aliasName() : field.getName());
                query.select(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    /**
     * 获取where条件
     */
    public void getWhere(Finder.Query query){
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields){
            CodDaoViewWhere codDaoViewWhere = field.getAnnotation(CodDaoViewWhere.class);
            if (codDaoViewWhere != null){
                for (String s : codDaoViewWhere.value()){
                    query.where(s);
                }
            }
        }
    }

    /**
     * 获取on拼接
     * @param ons ons
     * @return 拼接好字符串
     */
    private String getOn(String[] ons){
        String table = "";
        String field = "";
        String value = "";

        StringBuffer sb = new StringBuffer();

        if (ons.length == 1){
            String[] tempValue = ons[0].split("=");
            sb.append(" on ");
            int i = 0;
            for (String temp : tempValue){
                String[] temps = temp.trim().split("\\.");
                table = temps[0];
                field = temps[1];
                sb.append(getTableAlias(table)).append(".").append(field);
                if (i % 2 == 0){
                    sb.append(" = ");
                }
                i++;
            }
        } else {
            sb.append(" on ");
            int i = 0;
            for (String on : ons){
                String[] tempOns = on.trim().split("\\.");
                table = tempOns[0];
                field = tempOns[1];
                sb.append(getTableAlias(table)).append(".").append(field);
                if (i % 2 == 0){
                    sb.append(" = ");
                }
                i++;
            }
        }
        // String on = " on " + getTableAlias(table) + "." + field + " = " + value[1];
        return sb.toString();
    }

    /**
     * on参数必须是2个
     * @param ons on参数
     * @return 是否正确
     */
    private boolean isOn(String[] ons){
        return ons.length <= 2;
    }

    /**
     * 获取表别名
     * @param tableName 表名
     * @return 别名
     */
    private String getTableAlias(String tableName){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);
    }
}
