package com.cn.xb.springbootmybatis.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 提供字符串和日期类型之间的转换
 *
 */
public class DateFmtUtils {
    private static Logger logger = LoggerFactory.getLogger(DateFmtUtils.class);

    public DateFmtUtils() {
    }

    /**
     * 根据给定的格式将日期转为字符串格式
     * @param date  需要转换的日期
     * @param format  转换使用的格式 如 yyyy-MM-dd
     * @return
     */
    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }

        return result;
    }

    /**
     * 将字符串类型的时间 转换为北京时间
     * @param strDate  引擎中的时间（字符串格式）
     * @return
     */
    public static String formatBpmDateToBJDate(String strDate) {
        String result = strDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        try {
            if (!StringUtils.isEmpty(result)) {
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                Date dt = sdf.parse(strDate);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                result = sdf.format(dt);
                result = result.replace("T", " ");
                result = result.replace("Z", "");
            } else {
                result = "";
            }
        } catch (ParseException var5) {
            logger.error("转换BPM时间为北京时间失败！", var5);
        }

        return result;
    }

    /**
     * 将字符串类型的时间 转换为Date类型,不加8小时
     * @param strDate  引擎中的字符串
     * @return
     */
    public static Date formatBpmDateToDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date dt = null;

        try {
            if (!StringUtils.isEmpty(strDate)) {
                dt = sdf.parse(strDate);
            }
        } catch (ParseException var4) {
            logger.error("转换BPM时间为日期类型失败！", var4);
        }

        return dt;
    }

    /**
     * 将指定字符串转换为Date类型
     * @param strDate  时间字符串
     * @param dateFmt  转换格式， 如 "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date formatDateString(String strDate, String dateFmt) {
        Date dt = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFmt);
            if (!StringUtils.isEmpty(strDate)) {
                dt = sdf.parse(strDate);
            }
        } catch (ParseException var4) {
            logger.error("转换时间为日期类型失败！", var4);
        }
        return dt;
    }

    /**
     * 时间转换一周日期
    */
    public static List<String> getDateToWeek(Date date){
        List<String> dateWeekList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = "";
        //flag用来存取与当天日期的相差数
        int flag = 0;
        for(int i=1;i<8;i++){
            //新建日历
            Calendar cal = Calendar.getInstance();
            //在日历中找到当前日期
            cal.setTime(date);
            //当前日期时本周第几天，默认按照西方惯例上周星期天为第一天
            flag = -cal.get(Calendar.DAY_OF_WEEK);
            //根据循环。当天与上周星期天和本周一到周五相差的天数
            cal.add(Calendar.DATE, flag+i);
            //转化格式
            time = sdf.format(cal.getTime());
            //存入list
            dateWeekList.add(time);
        }
        return dateWeekList;
    }
}
