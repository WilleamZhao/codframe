package com.tlkj.cod.common.constant;

/**
 * Desc 颜色常量
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonAnsiConstant
 * @date 2019/5/5 5:23 PM
 */
public enum  CodCommonAnsiConstant {

    /**
     * 前景色
     */
    DEFAULT("39"),

    /**
     * 黑色
     */
    BLACK("30"),

    /**
     * 红色
     */
    RED("31"),

    /**
     * 绿色
     */
    GREEN("32"),

    /**
     * 黄色
     */
    YELLOW("33"),

    /**
     * 蓝色
     */
    BLUE("34"),

    /**
     * 品红色
     */
    MAGENTA("35"),

    /**
     * 青色
     */
    CYAN("36"),

    /**
     * 白色
     */
    WHITE("37"),

    /**
     * 背景色
     */
    BRIGHT_DEFAULT("49"),

    /**
     * 黑色
     */
    BRIGHT_BLACK("40"),


    /**
     * 红色
     */
    BRIGHT_RED("41"),

    /**
     * 绿色
     */
    BRIGHT_GREEN("42"),

    /**
     * 黄色
     */
    BRIGHT_YELLOW("43"),

    /**
     * 蓝色
     */
    BRIGHT_BLUE("44"),

    /**
     * 品红色
     */
    BRIGHT_MAGENTA("45"),

    /**
     * 青色
     */
    BRIGHT_CYAN("46"),

    /**
     * 白色
     */
    BRIGHT_WHITE("47");

    private final String code;

    CodCommonAnsiConstant(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }

    /*private static final String BRIGHT_BLACK = "90";

    private static final String BRIGHT_RED = "91";

    private static final String BRIGHT_GREEN = "92";

    private static final String BRIGHT_YELLOW = "93";

    private static final String BRIGHT_BLUE = "94";

    private static final String BRIGHT_MAGENTA = "95";

    private static final String BRIGHT_CYAN ="96";

    private static final String BRIGHT_WHITE = "97";*/

}
