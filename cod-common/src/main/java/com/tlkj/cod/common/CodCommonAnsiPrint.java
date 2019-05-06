package com.tlkj.cod.common;

import com.tlkj.cod.common.constant.CodCommonAnsiConstant;

/**
 * Desc 打印Ansi彩色信息
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonAnsiPrint
 * @date 2019/5/5 5:09 PM
 */
public class CodCommonAnsiPrint {

    private static final String ENCODE_START = "\033[";
    private static final String ENCODE_END = "m";
    private static final String ENCODE_JOIN = ";";
    private static final String RESET = "0;" + CodCommonAnsiConstant.DEFAULT;

    /**
     * Create a new ANSI string from the specified elements. Any {@link CodCommonAnsiConstant}s will
     * be encoded as required.
     * @param elements the elements to encode
     * @return a string of the encoded elements
     */
    public static String toString(Object... elements) {
        StringBuilder sb = new StringBuilder();
        buildEnabled(sb, elements);
        return sb.toString();
    }

    private static void buildEnabled(StringBuilder sb, Object[] elements) {
        boolean writingAnsi = false;
        boolean containsEncoding = false;
        for (Object element : elements) {
            if (element instanceof CodCommonAnsiConstant) {
                containsEncoding = true;
                if (!writingAnsi) {
                    sb.append(ENCODE_START);
                    writingAnsi = true;
                }
                else {
                    sb.append(ENCODE_JOIN);
                }
            }
            else {
                if (writingAnsi) {
                    sb.append(ENCODE_END);
                    writingAnsi = false;
                }
            }
            sb.append(element.toString());
        }
        if (containsEncoding) {
            sb.append(writingAnsi ? ENCODE_JOIN : ENCODE_START);
            sb.append(RESET);
            sb.append(ENCODE_END);
        }
    }

    public static void main(String[] args) {
        // System.out.println("Hello \u001b[1;42m red world! \u001b[0m");
        /*
        System.out.println("\033[30;4m" + "我滴个颜什" + "\033[0m" + "1");
        System.out.println("\033[31;4m" + "我滴个颜什" + "\033[0m" + "2");
        System.out.println("\033[32;4m" + "我滴个颜什" + "\033[0m" + "3");
        System.out.println("\033[33;4m" + "我滴个颜什" + "\033[0m" + "4");
        System.out.println("\033[34;4m" + "我滴个颜什" + "\033[0m" + "5");
        System.out.println("\033[35;4m" + "我滴个颜什" + "\033[0m" + "6");
        System.out.println("\033[36;4m" + "我滴个颜什" + "\033[0m" + "7");
        System.out.println("\033[37;4m" + "我滴个颜什" + "\033[0m" + "8");
        System.out.println("\033[40;31;1m" + "我滴个颜什" + "\033[0m" + "9");
        System.out.println("\033[41;32;4m" + "我滴个颜什" + "\033[0m" + "10");
        System.out.println("\033[42;33;4m" + "我滴个颜什" + "\033[0m" + "11");
        System.out.println("\033[43;34;4m" + "我滴个颜什" + "\033[0m" + "12");
        System.out.println("\033[44;35;4m" + "我滴个颜什" + "\033[0m" + "13");
        System.out.println("\033[45;36;4m" + "我滴个颜什" + "\033[0m" + "14");
        System.out.println("\033[46;37;4m" + "我滴个颜什" + "\033[0m" + "15");
        System.out.println("\033[47;4m" + "我滴个颜什" + "\033[0m"  + "16");
        */
        String a = CodCommonAnsiPrint.toString(CodCommonAnsiConstant.BLUE, "蓝色", CodCommonAnsiConstant.GREEN, "绿色");
        System.out.println(a);
        String b = CodCommonAnsiPrint.toString(CodCommonAnsiConstant.BLUE, CodCommonAnsiConstant.BRIGHT_CYAN, "蓝色字青色背景");
        System.out.println(b);
        String c = CodCommonAnsiPrint.toString(CodCommonAnsiConstant.WHITE, CodCommonAnsiConstant.BRIGHT_BLACK, "黑色背景白色字");
        System.out.println(c);
        String d = CodCommonAnsiPrint.toString(CodCommonAnsiConstant.BLACK, CodCommonAnsiConstant.BRIGHT_WHITE, "黑色字白色背景");
        System.out.println(d);
    }
}
