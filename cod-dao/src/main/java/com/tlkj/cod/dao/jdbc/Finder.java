/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.dao.jdbc;

import com.google.common.base.CaseFormat;
import com.tlkj.cod.dao.exception.CodDataViewException;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.DBConnectionPool;
import com.tlkj.cod.dao.view.CodDataView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 查询工具类
 * 支持 mysql oracle
 *
 * @author sourcod
 */
@Repository
public class Finder {

    private JdbcTemplate jdbcTemplate;

    public Finder() {

    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        DataSource dataSource = DBConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name());
        if (dataSource != null){
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }
    }

    /*
    @Autowired
    public Finder(DBConnectionPool dbConnectionPool) {
        jdbcTemplate = new JdbcTemplate(dbConnectionPool.getDataSource());
    }
    */

    public Finder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Finder(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 设置数据源
     * @param name
     * @return
     */
    public Query ds(String name) {
        return new Query(new JdbcTemplate(DBConnectionPool.getInstance().getDataSource(name)));
    }

    /**
     * 设置默认数据源
     * @param name
     * @return
     */
    public Finder dsf(String name) {
        this.jdbcTemplate = new JdbcTemplate(DBConnectionPool.getInstance().getDataSource(name));
        return this;
    }

    /**
     * 查询后还原
     * @param table
     * @return
     */
    public Query from(String table) {
        return new Query(jdbcTemplate).from(table);
    }

    /**
     * 指定数据源查询
     * @param table 表名
     * @param name  数据源名称
     * @return
     */
    public Query from(String table, String name) {
        DataSource dataSource = DBConnectionPool.getInstance().getDataSource(name);
        if (dataSource == null){
            return null;
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new Query(jdbcTemplate).from(table);
    }

    /**
     * 查询视图
     * TODO 待完善
     */
    public Query fromView(CodDataView view) {
        Query query;
        String table = view.getTable();
        if (StringUtils.isBlank(table)) {
            throw new CodDataViewException();
        }

        query = new Query(jdbcTemplate, 1).from(table);
        view.getJoins(query);
        view.getSelect(query);
        view.getWhere(query);
        if (query.dev) {
            System.out.println(query.createGenerator().toSQL());
        }
        return query;
    }


    public <T> List<T> all(Class<T> klass, String sql, Object... parameters) {
        BeanPropertyRowMapper<T> mapper = new BeanPropertyRowMapper<T>(klass);
        mapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query(sql, parameters, mapper);
    }

    public <T> T first(Class<T> klass, String sql, Object... parameters) {
        BeanPropertyRowMapper<T> mapper = new BeanPropertyRowMapper<T>(klass);
        mapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.queryForObject(sql, parameters, mapper);
    }

    public <T, P> T find(Class<T> klass, P id) {
        return find(klass, "id", id);
    }

    public <T, P> T find(Class<T> klass, String primaryKey, P id) {
        return find(klass, null, primaryKey, id);
    }

    public <T, P> T find(Class<T> klass, String table, String primaryKey, P id) {
        if (table == null) {
            table = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
                    klass.getSimpleName());
        }
        return from(table).where(primaryKey, id).first(klass);
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

        ConditionType(String op) {
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

        /**
         * 前缀
         * 1: 视图
         */
        private int prefix;
        private String table;
        private StringBuilder select = new StringBuilder();
        private StringBuilder join = new StringBuilder();
        private StringBuilder where = new StringBuilder();
        private StringBuilder groupBy = new StringBuilder();
        private StringBuilder having = new StringBuilder();
        private StringBuilder orderBy = new StringBuilder();
        private StringBuilder limit = new StringBuilder();

        private List<Object> parameters = new ArrayList<Object>();

        private boolean whereStarted = false;

		/*public Generator(String table) {
			this.table = table;
		}*/

        public Generator(String table, int prefix) {
            this.table = table;
            this.prefix = prefix;
        }

        public Object[] getParameters() {
            return parameters.toArray();
        }

        public String toCountSQL() {
            return "select count(*) from " + table + join + where + having;
        }

        public String toSQL() {
            if (prefix == 1) {
                return "select " + select + " from " + table + join + where
                        + groupBy + having + orderBy + limit;
            }
            return "select " + select + " from " + table + join + where
                    + groupBy + having + orderBy + limit;
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
    }

    public static class Query {
        private boolean dev = false;
        private final JdbcTemplate jdbcTemplate;
        private String table;
        private List<String> selects = new ArrayList<String>();
        private List<String> joins = new ArrayList<String>();

        private Expr where = new Expr();
        private Expr current;
        private int exprCount;
        private Operator op = Operator.AND;

        private List<String> groupBys = new ArrayList<String>();
        private List<String> havings = new ArrayList<String>();
        private List<String> orderBys = new ArrayList<String>();

        private Integer offset;
        private Integer rowCount;

        /**
         * 1: 查询
         */
        private int prefix;

        /**
         * 设置数据源
         * @return
         */
        public Query(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public Query(String name) {
            this.jdbcTemplate = new JdbcTemplate(DBConnectionPool.getInstance().getDataSource(name));
        }

        public Query(String name, int prefix) {
            this.jdbcTemplate = new JdbcTemplate(DBConnectionPool.getInstance().getDataSource(name));
            this.prefix = prefix;
        }

        /**
         * 查询视图
         *
         * @param jdbcTemplate
         * @param prefix
         */
        public Query(JdbcTemplate jdbcTemplate, int prefix) {
            this.jdbcTemplate = jdbcTemplate;
            this.prefix = prefix;
        }

        public Query dev(Boolean dev) {
            this.dev = dev;
            return this;
        }

        public Query from(String table) {
            this.table = table;
            return this;
        }

        public Query groupBy(String groupBy) {
            groupBys.add(groupBy);
            return this;
        }

        public Query having(String having) {
            havings.add(having);
            return this;
        }

        public Query limit(Integer rowCount) {
            this.offset = 0;
            this.rowCount = rowCount;
            return this;
        }

        public Query limit(Integer offset, Integer rowCount) {
            this.offset = offset - 1;
            this.rowCount = rowCount;
            return this;
        }

        public Query orderBy(String orderBy) {
            if (orderBy != null && !orderBy.isEmpty()) {
                orderBys.add(orderBy);
            }
            return this;
        }

        public Query select(String... columns) {
            selects.addAll(Arrays.asList(columns));
            return this;
        }

        public Query join(String join) {
            joins.add(join);
            return this;
        }

        public Query expr(boolean and) {
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

        public Query end() {
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

        private Query where(Condition c) {
            c.op = op;
            op = Operator.AND;
            if (current == null) {
                where.add(c);
            } else {
                current.add(c);
            }
            return this;
        }

        public Query or() {
            op = Operator.OR;
            return this;
        }

        /**
         * 获取别名
         *
         * @param name 表名
         * @return 转换后表名
         */
        private String getViewName(String name) {
            if (prefix == 1) {
                String[] fileds = name.split("\\.");
                if (fileds.length == 2) {
                    String filed = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, fileds[0]);
                    return filed + "." + fileds[1];
                }
            }
            return name;
        }

        public Query where(String sql) {
            return where(new Condition(ConditionType.SEGMENT, sql, null));
        }

        public Query where(String name, Object value) {
            where(new Condition(ConditionType.EQ, getViewName(name), value));
            return this;
        }

        public Query where(String name, Object value, Object... nameValues) {
            if (nameValues.length % 2 != 0) {
                throw new IllegalArgumentException(
                        "nameValues.length % 2 must be 0");
            }
            where(new Condition(ConditionType.EQ, getViewName(name), value));
            for (int i = 0; i < nameValues.length; i = i + 2) {
                where(new Condition(ConditionType.EQ, (String) nameValues[i],
                        nameValues[i + 1]));
            }
            return this;
        }

        public Query where(Map<String, Object> params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                where(new Condition(ConditionType.EQ, getViewName(entry.getKey()),
                        entry.getValue()));
            }
            return this;
        }

        public Query not(String name, Object value) {
            return where(new Condition(ConditionType.NOT_EQ, getViewName(name), value));
        }

        public Query in(String name, Object... values) {
            if (values.length == 0) {
                throw new IllegalArgumentException("values can't be empty");
            }
            return where(new Condition(ConditionType.IN, getViewName(name), values));
        }

        public Query notIn(String name, Object... values) {
            if (values.length == 0) {
                throw new IllegalArgumentException("values can't be empty");
            }
            return where(new Condition(ConditionType.NOT_IN, getViewName(name), values));
        }

        public Query like(String name, Object value) {
            return where(new Condition(ConditionType.LIKE, getViewName(name), value));
        }

        public Query notLike(String name, Object value) {
            return where(new Condition(ConditionType.NOT_LIKE, getViewName(name), value));
        }

        public Query between(String name, Object from, Object to) {
            return where(new Condition(ConditionType.BETWEEN, getViewName(name),
                    new Object[]{from, to}));
        }

        public Query notBetween(String name, Object from, Object to) {
            return where(new Condition(ConditionType.NOT_BETWEEN, getViewName(name),
                    new Object[]{from, to}));
        }

        public Query less(String name, Object value) {
            return where(new Condition(ConditionType.LESS, getViewName(name), value));
        }

        public Query lessOrEquals(String name, Object value) {
            return where(new Condition(ConditionType.LE, getViewName(name), value));
        }

        public Query great(String name, Object value) {
            return where(new Condition(ConditionType.GREAT, getViewName(name), value));
        }

        public Query greatOrEquals(String name, Object value) {
            return where(new Condition(ConditionType.GE, getViewName(name), value));
        }

        public Query isNull(String name, Object value) {
            return where(new Condition(ConditionType.NULL, getViewName(name), value));
        }

        public Query isNotNull(String name, Object value) {
            return where(new Condition(ConditionType.NOT_NULL, getViewName(name), value));
        }

        public <T> Pagination<T> paginate(Class<T> klass, int page) {
            return paginate(klass, page, Pagination.DEFAULT_PER_PAGE);
        }

        public <T> Pagination<T> paginate(Class<T> klass, String page) {
            int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
            int perPage = Pagination.DEFAULT_PER_PAGE;
            return paginate(klass, currentPage, perPage);
        }

        public <T> Pagination<T> paginate(Class<T> klass, String page, String pageSize) {
            int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
            int perPage = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) : Pagination.DEFAULT_PER_PAGE;
            return paginate(klass, currentPage, perPage);
        }

        public <T> Pagination<T> paginate(Class<T> klass, int page, int pageSize) {
            checkValid();

            limit((page - 1) * pageSize + 1, pageSize);

            Generator g = createGenerator();

            if (dev) {
                System.out.println(g.toSQL());
            }

            Integer count = jdbcTemplate.queryForObject(g.toCountSQL(),
                    Integer.class, g.getParameters());

            BeanPropertyRowMapper<T> mapper = new BeanPropertyRowMapper<T>(
                    klass);
            mapper.setPrimitivesDefaultedForNullValue(true);
            List<T> data = jdbcTemplate.query(g.toSQL(), g.getParameters(),
                    mapper);

            Pagination<T> pagination = new Pagination<T>(page, pageSize, count);
            pagination.setData(data);

            return pagination;
        }

        public <T> T first(Class<T> klass) {
            checkValid();

            Generator g = createGenerator();
            if (g.limit.length() == 0) {
                g.limit(0, 1);
            }
            List<T> data = doQuery(klass, g);

            if (data.size() == 0) {
                return null;
            }
            return data.get(0);
        }

        public <T> T firstForObject(Class<T> klass) {
            checkValid();

            Generator g = createGenerator();
            if (g.limit.length() == 0) {
                g.limit(0, 1);
            }
            if (dev) {
                System.out.println(g.toSQL());
            }
            return jdbcTemplate.queryForObject(g.toSQL(), klass, g.getParameters());
        }

        public <T> List<T> all(Class<T> klass) {
            checkValid();

            return doQuery(klass, createGenerator());
        }

        public List<Map<String, Object>> all() {
            checkValid();

            Generator g = createGenerator();
            return jdbcTemplate.queryForList(g.toSQL(), g.getParameters());
        }

        private <T> List<T> doQuery(Class<T> klass, Generator g) {
            BeanPropertyRowMapper<T> mapper = new BeanPropertyRowMapper<T>(klass);
            mapper.setPrimitivesDefaultedForNullValue(true);
            if (dev) {
                System.out.println(g.toSQL());
            }
            List<T> data = jdbcTemplate.query(g.toSQL(), g.getParameters(),
                    mapper);
            return data;
        }

        public Generator createGenerator() {
            Generator g = new Generator(table, prefix);
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



    /**
     * @param args
     */
    public static void main(String[] args) {
        Finder finder = new Finder();
        Query q = finder.from("user")
                .join("join site on site.id = user.site_id")
                .where("password", "oooo").or().in("type", 1, 2).expr(false)
                .where("name", "zzz").end().groupBy("test")
                .having("count(nid) > 1").orderBy("id desc").limit(2);
        System.out.println(q.createGenerator().toSQL());
    }

}
