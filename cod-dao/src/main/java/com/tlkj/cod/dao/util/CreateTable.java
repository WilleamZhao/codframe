package com.tlkj.cod.dao.util;

import com.sun.jna.platform.win32.Netapi32Util;
import com.tlkj.cod.dao.annotation.CodDaoColumn;
import com.tlkj.cod.dao.annotation.CodDaoId;
import com.tlkj.cod.dao.annotation.CodDaoTable;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc 创建表sql
 *
 * @author sourcod
 * @version 1.0
 * @className CreateTable
 * @date 2019/5/28 8:31 AM
 */
public class CreateTable {

    public static Map<String, String> javaProperty2SqlColumnMap = new HashMap<>();

    static {
        javaProperty2SqlColumnMap.put("Integer", "int");
        javaProperty2SqlColumnMap.put("int", "int");
        javaProperty2SqlColumnMap.put("Short", "tinyint");
        javaProperty2SqlColumnMap.put("Long", "bigint");
        javaProperty2SqlColumnMap.put("BigDecimal", "decimal(19,2)");
        javaProperty2SqlColumnMap.put("Double", "double precision not null");
        javaProperty2SqlColumnMap.put("Float", "float");
        javaProperty2SqlColumnMap.put("Boolean", "bit");
        javaProperty2SqlColumnMap.put("Timestamp", "datetime");
        javaProperty2SqlColumnMap.put("String", "VARCHAR(255)");
    }

    public static String createTable(Class obj){
        return createTable(obj, "");
    }

    /**
     * 创建表
     * @param obj       类
     * @param tableName 表名 不传默认类名
     * @return 建表 sql 语句
     */
    public static String createTable(Class obj, String tableName){
        StringBuilder sb = new StringBuilder();

        CodDaoTable codDaoTable = obj.getClass().getAnnotation(CodDaoTable.class);
        String tableCharset = "";
        String tableComment = "";
        String tableEngine = "";
        boolean isDrop = false;
        if (codDaoTable != null){
            if (StringUtils.isBlank(tableName)) {
                tableName = codDaoTable.name();
            }
            tableCharset = codDaoTable.charset();
            tableComment = codDaoTable.comment();
            tableEngine = codDaoTable.engine();
            isDrop = codDaoTable.drop();
        }

        // 未传表明默认用类名
        if (StringUtils.isBlank(tableName)) {
            tableName = obj.getClass().getSimpleName();
        }

        // 换行符
        String line = System.getProperty("line.separator");

        if (isDrop){
            sb.append(line).append("drop table if exists  ").append(tableName).append(";").append(line);
        }

        sb.append("create table ").append(tableName).append(" ( ").append(line);

        Field[] fields = obj.getDeclaredFields();
        boolean firstId = obj.getAnnotationsByType(CodDaoId.class).length == 0;

        // 是否设置过ID, 验证多个ID
        boolean isSetId = false;
        for (Field f : fields) {

            // 参数
            String param = null;
            // 列名
            String column = null;
            // 默认值
            String defaultValue = "";
            // 是否允许为空
            boolean isNull = false;
            // 备注
            String comment = "";

            // 静态的
            boolean isStatic = Modifier.isStatic(f.getModifiers());
            if (isStatic){
                continue;
            }

            CodDaoColumn codDaoColumn = f.getAnnotation(CodDaoColumn.class);
            CodDaoId codDaoId = f.getAnnotation(CodDaoId.class);
            if (codDaoColumn != null){
                param = codDaoColumn.type();
                if (codDaoId != null){
                    if (!isSetId){
                        column = codDaoId.name();
                        firstId = true;
                        isSetId = true;
                    }
                } else {
                    column = codDaoColumn.name();
                }
                isNull = codDaoColumn.isNull();
                defaultValue = codDaoColumn.def();
                comment = codDaoColumn.comment();
            }
            sb.append(" ");
            if (StringUtils.isBlank(column)){
                column = f.getName();
                sb.append(column).append(" ");
            } else {
                sb.append(column).append(" ");
            }

            // 序列化ID
            if ("serialVersionUID".equals(column)) {
                continue;
            }

            // 一般第一个是主键
            if (StringUtils.isBlank(param)){
                param = f.getType().getSimpleName();
                sb.append(javaProperty2SqlColumnMap.get(param));
            } else {
                sb.append(param);
            }

            sb.append(" ");

            if (StringUtils.isNotBlank(defaultValue)){
                sb.append("default").append(" ").append(defaultValue).append(" ");
            }

            if (!isNull){
                sb.append(" not null ");
            }

            if (StringUtils.isNotBlank(comment)){
                sb.append(" comment ").append("'").append(comment).append("'");
            }

            if (firstId) {
                sb.append(" PRIMARY KEY");
                firstId = false;
            }
            sb.append(",").append(line);
        }
        // 去掉最后一个逗号
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")").append(line);

        if (StringUtils.isNotBlank(tableComment)){
            sb.append(" COMMENT ").append(tableComment).append(line);
        }

        if (StringUtils.isNotBlank(tableEngine)){
            sb.append(" ENGINE = ").append(tableEngine);
        }

        if (StringUtils.isNotBlank(tableCharset)){
            sb.append(" CHARSET = ").append(tableCharset);
        }

        sb.append(";");

        System.out.println(sb.toString());

        return sb.toString();
    }

    public static void main(String[] args) {
        createTable(Netapi32Util.User.class, null);
    }

}
