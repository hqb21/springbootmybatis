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

    //验证是否为正确的时间
    public static boolean validate(String dateStr) {
        String msg ="";
        String[] data = new String[3];
        boolean flag = true; // 若不符合规则将值改为false
        String year = "[0-9]{4}";// 年
        String month = "[0-9]||0[0-9]||1[12]";// 月
        String day = "[0-9]||[0-2][0-9]||3[01]";// 天
        int YEAR = 0;
        String str = dateStr;// 输入的字符串
        data = str.split("[-/.+]");
        // 最基本的检查格式 begin
        if (!data[0].matches(year)) {
            msg = "年不对";
            flag = false;
        }
        if (!data[1].matches(month)) {
            msg = "月不对";
            flag = false;
        }
        if (!data[2].matches(day)) {
            msg = "日不对";
            flag = false;
        }
        // end
        YEAR = Integer.valueOf(data[0]);
        boolean run = run(YEAR);// run 为true是闰年否则是 非闰年
        if (run) {// 闰年
            if (data[1].matches("0[2]||2")) {// 这里是闰年的2月
                if (!data[2].matches("0[1-9]||[1-9]||1[0-9]||2[0-9]")) {
                    flag = false;
                    msg = "2月份的天数不对";
                }
            }
        } else {// 非闰年
            if (data[1].matches("0[2]||2")) {// 这里是平年的2月
                if (!data[2].matches("0[1-9]||[1-9]||1[0-9]||2[0-8]")) {
                    flag = false;
                    msg = "2月份的天数不对";
                }
            }
        }

        // 下面判断除了2月份的大小月天数
        if (data[1].matches("0[13578]||[13578]||1[02]")) {// 这里是大月
            if (!data[2].matches("0[1-9]||[1-9]||[12][0-9]||3[01]")) {
                flag = false;
                msg = data[2] + " 天数不对";
            }
        } else if (data[1].matches("0[469]||[469]||11")) {// 这里是小月
            if (!data[2].matches("0[1-9]||[1-9]||[12][0-9]||30")) {
                flag = false;
                msg = data[2] + " 天数不对";
            }
        }

        if (flag) {
            msg = "日期格式正确";
        }
        System.out.println(msg);
        return flag;
    }

    //检查是否是闰年
    public static boolean run(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 是闰年
            return true;
        } else {
            return false;
        }
    }
}
