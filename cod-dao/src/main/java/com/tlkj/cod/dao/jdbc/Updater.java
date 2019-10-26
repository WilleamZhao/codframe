/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.jdbc;

import com.google.common.base.CaseFormat;
import com.tlkj.cod.common.CodCommonClass;
import com.tlkj.cod.common.CodCommonModelConvert;
import com.tlkj.cod.dao.exception.CodDataModelConvertException;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 更新工具类
 * Desc 不涉及回滚, 回滚需要自己实现或者集成spring事物控制
 *
 * @author sourcod
 * @version 1.0
 * @className Updater
 * @date 2018/7/8 下午9:05
 */
@Repository
public class Updater {

    private JdbcTemplate jdbcTemplate;

    private Map<String, DataSource> map = new ConcurrentHashMap<>();

    /**
     * 默认自动提交
     */
    private boolean isAutoCommit = true;

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name());
        // CodDaoConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name());
        if (dataSource != null) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }
    }

    public Updater() {

    }

    /*public Updater() {

    }*/

    /**
     * 设置自动提交
     * @param name 数据源名称
     */
    public void setNotAutoCommit(String name){
        DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(name);
        try {
            dataSource.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置自动提交
     */
    public Updater setNotAutoCommit(){
        this.isAutoCommit = false;
        DataSource dataSource = this.getJdbcTemplate().getDataSource();
        try {
            dataSource.getConnection().setAutoCommit(isAutoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }



    /**
     * 设置数据源
     * @param name 数据源名称
     * @return
     */
    public Updater to(String name) {
        return to(CodDaoConnectionPool.getInstance().getDataSource(name));
    }

    public Updater to(DataSource dataSource) {
        return new Updater(dataSource);
    }

    /**
     * 设置默认数据源
     * @param name 数据源名称
     */
    public Updater toDefault(String name) {
        this.jdbcTemplate = new JdbcTemplate(CodDaoConnectionPool.getInstance().getDataSource(name));
        return this;
    }

    /**
     * 设置编码方式
     * utf8mb4 解决4字节汉字保存问题
     * 命令会将 character_set_client、character_set_connection、character_set_results 3个会话字符集相关变量均设置为 utf8mb4，以保证写入或者读出的数据使用 utf8mb4 字符集进行解释。
     * @param characterEncoding 编码方式
     */
    public void setEncoding(String characterEncoding) {
        execute("set names " + characterEncoding);
    }

    public Updater(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Updater(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 插入
     */
    public Update insert(String table) {
        return new Update(getJdbcTemplate(), 1).from(table);
    }

    public Update insert(String table, String name) {
        DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(name);
        if (dataSource == null){
            return null;
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new Update(jdbcTemplate, 1).from(table);
    }

    public Update insert(Object object) {
        return new Update(getJdbcTemplate(), 1, object);
    }

    public Update insert(String table, Object object) {
        return new Update(getJdbcTemplate(), 1, object).from(table);
    }

    /**
     * 删除
     */
    public Update delete(String table) {
        return new Update(getJdbcTemplate(), 2).from(table);
    }

    public Update delete(String table, String name) {
        DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(name);
        if (dataSource == null){
            return null;
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new Update(jdbcTemplate, 2).from(table);
    }

    /**
     * 更新
     */
    public Update update(String table) {
        return new Update(getJdbcTemplate(), 0).from(table);
    }

    public Update update(String table, String name) {
        DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(name);
        if (dataSource == null){
            return null;
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new Update(jdbcTemplate, 0).from(table);
    }

    public Update update(Object object) {
        return new Update(getJdbcTemplate(), 0, object);
    }

    public Update update(String table, Object object) {
        return new Update(getJdbcTemplate(), 0, object).from(table);
    }

    /**
     * 执行更新
     */
    private int update(String sql, Object[] params) {
        return getJdbcTemplate().update(sql, params);
    }

    public int update(String sql, Object[] params, Integer[] types) {
        return getJdbcTemplate().update(sql, params, types);
    }

    /**
     * 批量更新
     */
    public int[] batchUpdate(String... sql) {
        return getJdbcTemplate().batchUpdate(sql);
    }

    /**
     * 批量更新
     */
    public int[] batchUpdate(String sql, List<Object[]> batchArgs){
        return getJdbcTemplate().batchUpdate(sql, batchArgs);
    }

    /**
     * 批量更新
     */
    public int[] batchUpdate(String sql, BatchPreparedStatementSetter statementSetter){
        return getJdbcTemplate().batchUpdate(sql, statementSetter);
    }

    public void execute(String sql, PreparedStatementCallback callback) {
        getJdbcTemplate().execute(sql, callback);
    }

    public void execute(String sql) {
        getJdbcTemplate().execute(sql);
    }

    /**
     * 获取默认JDBC模版
     */
    private JdbcTemplate getJdbcTemplate() {
        return getJdbcTemplate("");
    }

    /**
     * 获取指定JDBC模版
     * @param name
     * @return
     */
    private JdbcTemplate getJdbcTemplate(String name) {
        return StringUtils.isNotBlank(name) ? new JdbcTemplate(CodDaoConnectionPool.getInstance().getDataSource(name)) : jdbcTemplate;
    }

    /**
     * TODO 执行文件
     * 类似Mybatis
     * @return
     */
    public Object executeFile(String url, Map map){
        try {
            InputStream in = new FileInputStream(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // this.jdbcTemplate.execute();
        return null;
    }

    private static <T> String getTableName(Class<T> klass) {
        return getTableName(klass, null);
    }

    private static <T> String getTableName(Class<T> klass, String table) {
        if (StringUtils.isEmpty(table)) {
            try {
                Object tableName = klass.getField("TABLE_NAME").get(klass.newInstance());
                if (tableName != null){
                    table = tableName.toString();
                    if (StringUtils.isEmpty(table)){
                        table = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, klass.getSimpleName());
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
                System.err.println("获取表名失败");
                e.printStackTrace();
            }
        }
        return table;
    }

    private String getSetSql(String[] filedAndValue) {
        List<String> setSql = new ArrayList<>();
        String filed = "";
        String value = "";
        for (int i = 0; i < filedAndValue.length; i++) {
            if (i % 2 != 0) {
                filed = filedAndValue[i];
            } else {
                value = filedAndValue[i];
            }
            if (StringUtils.isNotEmpty(filed) && StringUtils.isNotEmpty(value)) {
                setSql.add(filed + " = " + value);
                filed = "";
                value = "";
            }
        }
        return StringUtils.join(setSql, " , ");
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    private static enum ConditionType {
        SEGMENT(null), EQ("="), NOT_EQ("!="),
        /* in, like */
        IN(null), NOT_IN(null), LIKE("like"), NOT_LIKE("not like"),
        /* between */
        BETWEEN(null), NOT_BETWEEN(null),
        /* <, <=, >, >= */
        LESS("<"), LE("<="), GREAT(">"), GE(">="),
        /* null/not null */
        NULL(null), NOT_NULL(null);

        private ConditionType(String op) {
            this.op = op;
        }

        public String op;
    }

    private static enum Operator {
        AND, OR
    }

    private static class Condition {
        Operator op;
        final ConditionType type;
        final String column;
        final Object value;

        public Condition(ConditionType type, String column, Object value) {
            this(Operator.AND, type, column, value);
        }

        public Condition(Operator op, ConditionType type, String column,
                         Object value) {
            this.op = op;
            this.type = type;
            this.column = column;
            this.value = value;
        }

        public Object[] toValues() {
            return (Object[]) value;
        }

        public void toSQL(Generator g) {
            if (type.op != null) {
                g.append(column).append(" ").append(type.op).append(" ")
                        .append("?");
                g.add(value);
                return;
            }
            switch (type) {
                case SEGMENT:
                    g.append(column);
                    break;
                case IN:
                case NOT_IN:
                    g.append(column);
                    if (type == ConditionType.NOT_IN) {
                        g.append(" not");
                    }
                    g.append(" in ").append("(");
                    for (Object v : toValues()) {
                        g.append("?, ");
                        g.add(v);
                    }
                    g.strip(2);
                    g.append(")");
                    break;
                case BETWEEN:
                case NOT_BETWEEN:
                    g.append(column);
                    if (type == ConditionType.NOT_BETWEEN) {
                        g.append(" not");
                    }
                    g.append(" between ? and ?");
                    g.add(toValues());
                    break;
                case NULL:
                    g.append(column).append(" is null");
                    break;
                case NOT_NULL:
                    g.append(column).append(" is not null");
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private static class Expr {
        Operator op;
        List<Expr> exprs = new ArrayList<Expr>(0);
        final List<Condition> conditions = new ArrayList<Condition>();

        public Expr() {
            this(Operator.AND);
        }

        public Expr(Operator op) {
            this.op = op;
        }

        public void add(Condition c) {
            conditions.add(c);
        }

        public void add(Expr expr) {
            exprs.add(expr);
        }

        public void toSQL(Generator g) {
            if (!exprs.isEmpty()) {
                g.where(op);
                int size = exprs.size();
                if (size > 1) {
                    g.append("(");
                }
                for (int i = 0; i < size; i++) {
                    Expr expr = exprs.get(i);
                    if (i > 0) {
                        g.where(expr.op);
                    }
                    expr.toSQL(g);
                }
                if (size > 1) {
                    g.append(")");
                }
            }
            if (!conditions.isEmpty()) {
                if (!exprs.isEmpty()) {
                    g.where(op);
                }
                int size = conditions.size();
                if (size > 1) {
                    g.append("(");
                }
                for (int i = 0; i < size; i++) {
                    Condition c = conditions.get(i);
                    if (i > 0) {
                        g.append(" ").append(c.op.name().toLowerCase())
                                .append(" ");
                    }
                    c.toSQL(g);
                }
                if (size > 1) {
                    g.append(")");
                }
            }
        }
    }

    private static class Generator {
        private String table;

        /**
         * 前缀
         * 1: 插入
         * 0: 更新
         * 2: 删除
         */
        private int prefix;
        private StringBuilder set = new StringBuilder();
        private StringBuilder names = new StringBuilder();
        private StringBuilder values = new StringBuilder();
        private StringBuilder select = new StringBuilder();
        private StringBuilder join = new StringBuilder();
        private StringBuilder where = new StringBuilder();
        private StringBuilder groupBy = new StringBuilder();
        private StringBuilder having = new StringBuilder();
        private StringBuilder orderBy = new StringBuilder();
        private StringBuilder limit = new StringBuilder();

        private List<Object> parameters = new ArrayList<Object>();

        private boolean whereStarted = false;

        public Generator(String table, int prefix) {
            this.table = table;
            this.prefix = prefix;
        }

        public Object[] getParameters() {
            return parameters.toArray();
        }

        public String toCountSQL() {
            return "select count(*) from " + table + join + where + groupBy
                    + having;
        }

        public String toSQL() {
            String sql = "";
            switch (prefix){
                case 0:
                    sql = "update " + table + " set " + set + join + where + groupBy + having + orderBy + limit;
                    break;
                case 1:
                    sql = "insert into " + table + " " + names + " values " + values;
                    break;
                case 2:
                    sql = "delete from " + table + " " + where;
                    break;
                default:

                    break;
            }
            return sql;
        }

        public void selects(List<String> selects) {
            if (selects.isEmpty()) {
                select.append("*");
                return;
            }

            joinTo(selects, select, ", ", null);
        }

        public void joins(List<String> joins) {
            joinTo(joins, join, null, "\n");
        }

        private void joinTo(List<String> list, StringBuilder to,
                            String padding, String prefix) {
            if (!list.isEmpty() && prefix != null) {
                to.append(prefix);
            }
            for (int i = 0; i < list.size(); i++) {
                if (i > 0 && padding != null) {
                    to.append(padding);
                }
                to.append(list.get(i));
            }
        }

        public void groupBy(List<String> groupBys) {
            if (groupBys.isEmpty()) {
                return;
            }
            joinTo(groupBys, groupBy, ", ", "\ngroup by ");
        }

        public void having(List<String> havings) {
            joinTo(havings, having, ", ", "\nhaving ");
        }

        public void orderBy(List<String> orderBys) {
            joinTo(orderBys, orderBy, ", ", "\norder by ");
        }

        public void limit(Integer offset, Integer rowCount) {
            if (offset == null || rowCount == null) {
                return;
            }
            limit.append("\nlimit ").append(offset).append(", ")
                    .append(rowCount);
        }

        public void strip(int size) {
            where.setLength(where.length() - size);
        }

        public StringBuilder append(String s) {
            if (!whereStarted) {
                where.append("\nwhere ");
                whereStarted = true;
            }
            return where.append(s);
        }

        public void add(Object obj) {
            parameters.add(obj);
        }

        public void add(Object[] objs) {
            parameters.addAll(Arrays.asList(objs));
        }

        public void where(Operator op) {
            if (whereStarted) {
                append(" ").append(op.name().toLowerCase()).append(" ");
            }
        }

        public void set(List<String> names, List<String> values) {
            if (names.isEmpty()) {
                return;
            }
            this.names.append(" ( ");
            this.values.append(" ( ");
            for (int i = 0; i < names.size(); i++) {
                if (prefix == 1) {
                    if (i < names.size() - 1) {
                        this.names.append(names.get(i)).append(", ");
                        this.values.append(values.get(i)).append(", ");
                    } else {
                        this.names.append(names.get(i));
                        this.values.append(values.get(i));
                    }
                } else {
                    if (i < names.size() - 1) {
                        set.append(names.get(i)).append(" = ").append(values.get(i)).append(", ");
                    } else {
                        set.append(names.get(i)).append(" = ").append(values.get(i));
                    }
                }
            }
            this.names.append(" ) ");
            this.values.append(" ) ");
        }
    }

    public static class Update {
        private final JdbcTemplate jdbcTemplate;
        private boolean dev = false;
        private String table;
        private List<String> setName = new ArrayList<String>();
        private List<String> setValue = new ArrayList<String>();
        private List<String> selects = new ArrayList<String>();
        private List<String> joins = new ArrayList<String>();

        private Expr where = new Expr();
        private Expr current;
        private int exprCount;
        private Operator op = Operator.AND;

        private List<String> groupBys = new ArrayList<String>();
        private List<String> havings = new ArrayList<String>();
        private List<String> orderBys = new ArrayList<String>();
        private Object updateObj;

        private Integer offset;
        private Integer rowCount;
        private int prefix;

        private boolean autoCommit = true;

        public Update(JdbcTemplate jdbcTemplate, int prefix) {
            this.jdbcTemplate = jdbcTemplate;
            this.prefix = prefix;
        }

        public Update(JdbcTemplate jdbcTemplate, int prefix, Object o) {
            this.jdbcTemplate = jdbcTemplate;
            this.updateObj = o;
            // this.prefix = prefix;
            this.table = getTableName(o.getClass());
            getFiledsInfo();
        }

        public Update dev(Boolean dev) {
            this.dev = dev;
            return this;
        }

        public Update from(String table) {
            this.table = table;
            return this;
        }

        public Update groupBy(String groupBy) {
            groupBys.add(groupBy);
            return this;
        }

        public Update having(String having) {
            havings.add(having);
            return this;
        }

        public Update limit(Integer rowCount) {
            this.offset = 0;
            this.rowCount = rowCount;
            return this;
        }

        public Update limit(Integer offset, Integer rowCount) {
            this.offset = offset - 1;
            this.rowCount = rowCount;
            return this;
        }

        public Update orderBy(String orderBy) {
            if (orderBy != null && !orderBy.isEmpty()) {
                orderBys.add(orderBy);
            }
            return this;
        }

        public Update select(String... columns) {
            selects.addAll(Arrays.asList(columns));
            return this;
        }

        public Update join(String join) {
            joins.add(join);
            return this;
        }

        public Update expr(boolean and) {
            if (current != null) {
                throw new IllegalStateException("must end current expr");
            }
            if (exprCount == 0 && !where.conditions.isEmpty()) {
                Expr expr = new Expr();
                expr.add(where);
                where = expr;
            }
            exprCount++;
            current = new Expr(and ? Operator.AND : Operator.OR);
            return this;
        }

        public Update end() {
            if (current == null) {
                throw new IllegalStateException("call expr first");
            }
            where.add(current);
            current = null;
            return this;
        }

        private void checkValid() {
            if (current != null) {
                throw new IllegalStateException("expr didn't end");
            }
        }

        private Update where(Condition c) {
            c.op = op;
            op = Operator.AND;
            if (current == null) {
                where.add(c);
            } else {
                current.add(c);
            }
            return this;
        }

        public Update or() {
            op = Operator.OR;
            return this;
        }

        public Update where(String sql) {
            return where(new Condition(ConditionType.SEGMENT, sql, null));
        }

        public Update where(String name, Object value) {
            where(new Condition(ConditionType.EQ, name, value));
            return this;
        }

        public Update where(String name, Object value, Object... nameValues) {
            if (nameValues.length % 2 != 0) {
                throw new IllegalArgumentException(
                        "nameValues.length % 2 must be 0");
            }
            where(new Condition(ConditionType.EQ, name, value));
            for (int i = 0; i < nameValues.length; i = i + 2) {
                where(new Condition(ConditionType.EQ, (String) nameValues[i],
                        nameValues[i + 1]));
            }
            return this;
        }

        public Update where(Map<String, Object> params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                where(new Condition(ConditionType.EQ, entry.getKey(),
                        entry.getValue()));
            }
            return this;
        }

        public Update not(String name, Object value) {
            return where(new Condition(ConditionType.NOT_EQ, name, value));
        }

        public Update in(String name, Object... values) {
            if (values.length == 0) {
                throw new IllegalArgumentException("values can't be empty");
            }
            return where(new Condition(ConditionType.IN, name, values));
        }

        public Update notIn(String name, Object... values) {
            if (values.length == 0) {
                throw new IllegalArgumentException("values can't be empty");
            }
            return where(new Condition(ConditionType.NOT_IN, name, values));
        }

        public Update like(String name, Object value) {
            return where(new Condition(ConditionType.LIKE, name, value));
        }

        public Update notLike(String name, Object value) {
            return where(new Condition(ConditionType.NOT_LIKE, name, value));
        }

        public Update between(String name, Object from, Object to) {
            return where(new Condition(ConditionType.BETWEEN, name,
                    new Object[]{from, to}));
        }

        public Update notBetween(String name, Object from, Object to) {
            return where(new Condition(ConditionType.NOT_BETWEEN, name,
                    new Object[]{from, to}));
        }

        public Update less(String name, Object value) {
            return where(new Condition(ConditionType.LESS, name, value));
        }

        public Update lessOrEquals(String name, Object value) {
            return where(new Condition(ConditionType.LE, name, value));
        }

        public Update great(String name, Object value) {
            return where(new Condition(ConditionType.GREAT, name, value));
        }

        public Update greatOrEquals(String name, Object value) {
            return where(new Condition(ConditionType.GE, name, value));
        }

        public Update isNull(String name, Object value) {
            return where(new Condition(ConditionType.NULL, name, value));
        }

        public Update isNotNull(String name, Object value) {
            return where(new Condition(ConditionType.NOT_NULL, name, value));
        }

        public Update set(String name, Integer value) {
            set(name, value, Integer.class);
            return this;
        }

        public Update setId() {
            this.setId("id");
            return this;
        }

        public Update setId(String name) {
            set(name, Updater.getUUID(), String.class);
            return this;
        }

        public Update set(String name, String value) {
            set(name, value, String.class);
            return this;
        }

        public Update set(String name, Object value, Class klass) {
            setName.add(name);
            setValue.add(setValue(value, klass));
            return this;
        }

        public Update set(String name, Object value, String typeName) {
            setName.add(name);
            setValue.add(setValue(value, typeName));
            return this;
        }

        /**
         * 设置类型
         * @param o
         * @param klass
         * @return
         */
        public Update set(Object o, Class klass) {
            if (!CodCommonModelConvert.class.isAssignableFrom(o.getClass())){
                // 不是子类
                return this;
            }
            Field[] fields = o.getClass().getDeclaredFields();
            // 3. 设置值
            for (Field field : fields) {
                // 4. 私有的 && 非static && 非final
                if (Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())){
                    Object value = getFieldValueByName(field.getName(), o);
                    if (value == null){
                        continue;
                    }
                    // 获取字段类型 class
                    Class aClass = field.getType();
                    set(field.getName(), value, aClass);
                }
            }
            return this;
        }

        /**
         * 框架约定
         * 更新时间
         */
        public Update updateTime(){
            set("update_time", "now()", Void.class);
            return this;
        }

        /**
         * 关闭自动提交
         * @return
         */
        public Update closeAutoCommit(){
            this.autoCommit = false;
            return this;
        }

        public int update() {
            checkValid();
            return doUpdate(createGenerator());
        }

        private int doUpdate(Generator g) {
            if (dev) {
                System.out.println(g.toSQL());
            }
            int rows = jdbcTemplate.update(g.toSQL(), g.getParameters());
            /*if (autoCommit){
                Connection connection = null;
                try {
                    connection = getConnection();
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }*/
            return rows;
        }

        /**
         * 提交
         */
        public void commit() {
            try {
                getConnection().commit();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        /**
         * 回滚
         */
        public void rollback() {
            try {
                getConnection().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        /**
         * 关闭
         */
        public void close() {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * 获取连接
         */
        private Connection getConnection() throws SQLException {
            return this.jdbcTemplate.getDataSource().getConnection();
        }

        private String setValue(Object value, Class klass) {
            return setValue(value, klass != null ? klass.getCanonicalName() : String.class.toString());
        }

        /**
         * 设置不同类型sql语句
         *
         * @param value
         * @param klassName
         * @return
         */
        private String setValue(Object value, String klassName) {
            String v = null;
            switch (klassName) {
                case "java.lang.String":
                    v = "'" + value + "'";
                    break;
                case "java.lang.Integer":
                    v = value != null ? value.toString() : "";
                    break;
                case "java.sql.Date":
                    v = "'" + value + "'";
                    break;
                case "java.sql.Time":
                    v = "'" + value + "'";
                    break;
                case "java.sql.Timestamp":
                    v = "'" + value + "'";
                    break;
                case "java.sql.SQLType":
                    v = value != null ? value.toString() : "";
                    break;
                case "java.lang.Void":
                    v = value != null ? value.toString() : "";
                    break;
                case "":
                    break;
                default:
                    // v = value != null ? value.toString() : "";
                    break;
            }
            return v;
        }


        /**
         * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
         */
        private void getFiledsInfo() {
            Field[] fields = this.updateObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if ("id".equals(field.getName())){
                        Object id = getFieldValueByName(field.getName(), this.updateObj);
                        if (id != null){
                            this.prefix = StringUtils.isNotEmpty(id.toString()) ? 0 : 1;
                        } else {
                            this.prefix = 1;
                        }
                    }
                    if (field.get(this.updateObj) != null) {
                        set(field.getName(), getFieldValueByName(field.getName(), this.updateObj), field.getType());
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        /**
         * 根据属性名获取属性值
         */
        private Object getFieldValueByName(String fieldName, Object o) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, o.getClass());
                Method rM = pd.getReadMethod();
                return rM.invoke(o);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        Generator createGenerator() {
            Generator g = new Generator(table, prefix);
            g.set(setName, setValue);
            g.selects(selects);
            g.joins(joins);
            where.toSQL(g);
            g.groupBy(groupBys);
            g.having(havings);
            g.orderBy(orderBys);
            g.limit(offset, rowCount);
            return g;
        }
    }


    public static void main(String[] args) {
        /*Updater updater = new Updater();
        *//**
         * 1. 自己拼接 修改
         * select 等不起作用
         * update user set name = 'sourcod', password = 123456, age = '21', sex = '1'
         * where (id = ? and name = ? and age = ? and password like ?)
         *//*
        String jointInsertSql = updater.update("user").set("name", "sourcod", Date.class).set("password", 123456).set("age", "21").set("sex", "1").where("id", 1).where("name", "sourcod").where("age", "21").like("password", "123456").select("a = 2").createGenerator().toSQL();
        System.out.println(jointInsertSql);

        *//**
         * 2.自己拼接 插入
         * where等条件语句不起作用
         * insert into user  ( name, password, age, sex )  values  ( 'willeamZhao', '123456', '21', '1' )
         *//*
        String jointUpdateSql = updater.insert("user").set("name", "willeamZhao", Timestamp.class).set("password", "123456").set("age", "21").set("sex", "1").where("id", 1).createGenerator().toSQL();
        System.out.println(jointUpdateSql);

        *//**
         * 3. 自动映射修改
         * 以下2种方式一样
         * update wx_user_d_o set city = '北京', group_id = '1'
         * where (id = ? and id = ?)
         *//*
        WxUserDO wxUserDO = new WxUserDO();
        wxUserDO.setCity("北京");
        wxUserDO.setGroup_id("1");
        String mappingUpdateSql1 = updater.update(wxUserDO).from("wx_user").where("id", "1").where("id", "1").createGenerator().toSQL();
        String mappingUpdateSql2 = updater.update("wx_user", wxUserDO).where("id", "1").where("id", "1").createGenerator().toSQL();
        System.out.println(mappingUpdateSql1);
        System.out.println(mappingUpdateSql2);

        *//**
         * update wx_user_d_o set city = '北京', group_id = '1'
         * where (id = ? and id = ?)
         *//*
        String mappingUpdateSql3 = updater.update(wxUserDO).where("id", "1").where("id", "2").createGenerator().toSQL();
        System.out.println(mappingUpdateSql3);

        *//**
         * 4. 自动映射插入
         * 以下2种方式一样
         * insert into wx_user  ( city, group_id )  values  ( '北京', '1' )
         *//*
        String mappingInsertSql1 = updater.insert(wxUserDO).from("wx_user").createGenerator().toSQL();
        String mappingInsertSql2 = updater.insert("wx_user", wxUserDO).createGenerator().toSQL();
        System.out.println(mappingInsertSql1);
        System.out.println(mappingInsertSql2);

        *//**
         * insert into wx_user_d_o  ( city, group_id )  values  ( '北京', '1' )
         *//*
        String mappingInsertSql3 = updater.insert(wxUserDO).createGenerator().toSQL();
        System.out.println(mappingInsertSql3);

        *//**
         * delete from history_stations where openid = 123
         *//*
        String deleteSql = updater.delete("history_stations").where("openid", "123").createGenerator().toSQL();
        System.out.println(deleteSql);*/
    }

}
