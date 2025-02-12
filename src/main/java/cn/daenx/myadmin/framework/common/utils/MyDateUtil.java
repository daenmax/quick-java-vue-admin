package cn.daenx.myadmin.framework.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtil {
    /**
     * 10位时间戳转Date类型
     *
     * @param timeStamp
     * @return
     */
    public static Date stamp2Date(String timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(sdf.format(Long.parseLong(timeStamp) * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date类型转10位时间戳
     *
     * @param date
     * @return
     */
    public static String Date2stamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = null;
        try {
            timeStamp = String.valueOf(sdf.parse(sdf.format(date)).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    /**
     * 时间文本转Date
     *
     * @param strDate yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date strToDate(String strDate) {
        if (!"".equals(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
        return new Date();
    }

    /**
     * Date转时间文本，例如转成yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

}
