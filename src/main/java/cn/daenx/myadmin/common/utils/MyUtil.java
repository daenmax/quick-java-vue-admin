package cn.daenx.myadmin.common.utils;

import cn.daenx.myadmin.common.exception.MyException;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class MyUtil {
    /**
     * 是否开启试用
     */
    public static boolean isTestUse = true;
    /**
     * 试用结束时间
     */
    public static String testUseTime = "2026-01-01 22:00:00";

    public static void handleTestUse() {
        if (!isTestUse) {
            return;
        }
        Date date = MyDateUtil.strToDate(testUseTime);
        String timestamp = MyDateUtil.Date2stamp(date);
        long nowTimestamp = System.currentTimeMillis() / 1000;
        if (nowTimestamp > Long.parseLong(timestamp)) {
            throw new MyException("未知错误");
        }
    }

    /**
     * 拼接List<对象>中指定字段
     *
     * @param collection
     * @param function
     * @param flag       拼接符号
     * @param <E>
     * @return
     */
    public static <E> String join(Collection<E> collection, Function<E, String> function, String flag) {
        if (CollUtil.isEmpty(collection)) {
            return "";
        }
        return collection.stream().map(function).filter(Objects::nonNull).collect(Collectors.joining(flag));
    }

    /**
     * 拼接List<对象>中指定字段
     *
     * @param collection
     * @param function
     * @param <E>
     * @return
     */
    public static <E> List<String> joinToList(Collection<E> collection, Function<E, String> function) {
        if (CollUtil.isEmpty(collection)) {
            return new ArrayList<>();
        }
        return collection.stream().map(function).filter(Objects::nonNull).collect(Collectors.toList());
    }



    public static String readTxt(String path) {
        try {
            String content = "";
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                content = content + line;
            }
            scanner.close();
            return content;
        } catch (FileNotFoundException e) {
            System.err.println("文件未找到");
        }
        return null;
    }

    public static String getStrByBetween(String str, String start, String end) {
        if (ObjectUtil.isEmpty(str)) {
            return str;
        }
        String text1 = null;
        String text2 = null;
        int startIndex = str.indexOf(start);
        if (startIndex > -1) {
            text1 = str.substring(startIndex + start.length());
            int endIndex = text1.indexOf(end);
            if (endIndex > -1) {
                text2 = text1.substring(0, endIndex);
                return text2;
            }
        }
        return str;
    }

    public static String getStrByReg(String str, String reg) {
        if (ObjectUtil.isEmpty(str)) {
            return str;
        }
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group();
        }
        return str;
    }


    private static String repeat(String str, int repeatCount) {
        String repeatedStr = "";
        for (int i = 0; i < repeatCount; i++) {
            repeatedStr += str;
        }
        return repeatedStr;
    }

    /**
     * 数据脱敏（中国）
     *
     * @param type  数据类型，0=姓名，1=手机号，2=身份证号码，3=银行卡号，4=电子邮箱，5=地址信息，6=IP地址
     * @param value 待脱敏的数据值
     * @return
     */
    public static String masked(Integer type, String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        String maskedValue = value;
        switch (type) {
            case 0: // 姓名
                maskedValue = value.replaceAll("([\\u4e00-\\u9fa5])(.*)", "$1" + repeat("*", value.length() - 1));
                break;
            case 1: // 手机号
                maskedValue = value.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                break;
            case 2: // 身份证号码
                maskedValue = value.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1**********$2");
                break;
            case 3: // 银行卡号
                maskedValue = value.replaceAll("(\\d{4})\\d+(\\d{4})", "$1 **** **** $2");
                break;
            case 4: // 电子邮箱
                maskedValue = value.replaceAll("(\\w{1})\\w*@(.*)", "$1****@$2");
                break;
            case 5: // 地址信息
                maskedValue = value.replaceAll("([\\u4e00-\\u9fa5])(.*)", "$1" + repeat("*", value.length() - 1));
                break;
            case 6: // IP地址
                maskedValue = value.replaceAll("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})", "$1.$2.*.*");
                break;
            default:
                break;
        }
        return maskedValue;
    }
}
