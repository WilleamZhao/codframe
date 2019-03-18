/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.dao.view;

import com.google.common.base.CaseFormat;
import com.tlkj.cod.dao.annotation.Column;
import com.tlkj.cod.dao.annotation.Join;
import com.tlkj.cod.dao.annotation.Table;
import com.tlkj.cod.dao.annotation.Where;
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
     * 别名
     */
    private static String alias = "";

    /**
     * 获取表名
     * @return
     */
    public String getTable() {
        Table table = this.getClass().getDeclaredAnnotation(Table.class);
        Field tempField = null;
        if (table == null){
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields){
                table = field.getAnnotation(Table.class);
                if (table != null){
                    tempField = field;
                    break;
                }
            }
        }
        String tableStr = "";
        String name = "";
        // String alias = "";
        if (table != null){
            name = table.name();
            alias = StringUtils.isNotBlank(table.alias()) ? table.alias() : getTableAlias(name);
            try {
                if (tempField != null && StringUtils.isBlank(name)){
                    String value = tempField.get(this).toString();
                    if (StringUtils.isNotBlank(value)){
                        name = value;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            tableStr = name + " " + alias;
        }

        CodDataView codDataView = null;
        if (StringUtils.isBlank(tableStr)){
            codDataView = (CodDataView) this;
        }
        return StringUtils.isBlank(tableStr) ? getTableByModel(codDataView) : tableStr;
    }

    private String getTableByModel(CodDataView view){
        return view.getTableModel().getName() + " " + view.getTableModel().getAlias();
    }

    /**
     * 根据注解获取join
     */
    public void getJoins(Finder.Query query) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields){
            Join join = field.getAnnotation(Join.class);
            if (join != null){
                String table = join.table();
                String alias = join.alias();
                String joinStr = join.join();
                String[] ons = join.on();
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
            Join join = method.getAnnotation(Join.class);
            if (join != null){
                String table = join.table();
                String alias = join.alias();
                String joinStr = join.join();
                String[] ons = join.on();
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
    public String getJoinsByModel(CodDataView view) {
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
            Column column = field.getAnnotation(Column.class);
            if (column != null){
                sb.append(getTableAlias(column.tName()))
                        .append(".")
                        .append(column.cName())
                        .append(" as ")
                        .append(StringUtils.isNotBlank(column.aliasName()) ? column.aliasName() : field.getName());
                query.select(sb.toString());
                sb.setLength(0);
            }
        }
    }

    public void getSelectByModel(Finder.Query query, CodDataView view){
        Field[] fields = view.getClass().getDeclaredFields();

        StringBuffer stringBuffer = new StringBuffer();
        for (Field field : fields){
            Column column = field.getAnnotation(Column.class);
            if (column!=null){
                stringBuffer.append(getTableAlias(column.tName()))
                        .append(".")
                        .append(column.cName())
                        .append(" as ")
                        .append(StringUtils.isNotBlank(column.aliasName()) ? column.aliasName() : field.getName());
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
            Where where = field.getAnnotation(Where.class);
            if (where != null){
                for (String s : where.value()){
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
