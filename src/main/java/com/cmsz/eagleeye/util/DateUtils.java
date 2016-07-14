package com.cmsz.eagleeye.util;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 格式化年月
     */
    private final static DateFormat FORMATDATE_MONTH = new SimpleDateFormat("yyyyMM");

    /**
     * 格式化年月日
     */
    private final static DateFormat FORMATDATE_DAY = new SimpleDateFormat("yyyyMMdd");

    /**
     * 默认一天
     */
    private final static int DAY = 1;

    /**
     * 获取连个日期集合的时间汇集
     * 
     * @param thisDates
     * @param beforeDates
     * @return
     */
    public static List<Integer> listDate(List<DateTime> thisDates,
            List<DateTime> beforeDates) {
        List<Integer> days = new ArrayList<>();
        int size = thisDates.size();
        // 如果是一个月内
        if (size == 2
                && thisDates.get(0).getMonthOfYear() == thisDates.get(1).getMonthOfYear()) {
            int startDate = thisDates.get(0).getDayOfMonth();
            int endDate = thisDates.get(1).getDayOfMonth();
            for (int j = startDate; j <= endDate; j++) {
                days.add(j);
            }
        } else {
            // 封装日期
            for (int i = 0; i < thisDates.size(); i++) {
                // 本期月最大日期
                int thisMonthMaxDay =
                        thisDates.get(i).dayOfMonth().withMaximumValue().getDayOfMonth();
                // 上期月最大日期
                int beforeMonthMaxDay =
                        beforeDates.get(i).dayOfMonth().withMaximumValue()
                                .getDayOfMonth();

                if (thisMonthMaxDay >= beforeMonthMaxDay) {
                    // 第一个日期
                    if (i == 0) {
                        int startDay = thisDates.get(i).getDayOfMonth();
                        for (int j = startDay; j <= thisMonthMaxDay; j++) {
                            days.add(j);
                        }
                        // 最后一个日期
                    } else if (i == thisDates.size() - 1) {
                        int endDay = thisDates.get(i).getDayOfMonth();
                        for (int j = 1; j <= endDay; j++) {
                            days.add(j);
                        }
                    } else {
                        for (int j = 1; j <= thisMonthMaxDay; j++) {
                            days.add(j);
                        }
                    }
                } else {
                    if (i == 0) {
                        int startDay = thisDates.get(i).getDayOfMonth();
                        for (int j = startDay; j <= beforeMonthMaxDay; j++) {
                            days.add(j);
                        }
                    } else if (i == thisDates.size() - 1) {
                        int endDay = thisDates.get(i).getDayOfMonth();
                        for (int j = 1; j <= endDay; j++) {
                            days.add(j);
                        }
                    } else {
                        for (int j = 1; j <= beforeMonthMaxDay; j++) {
                            days.add(j);
                        }
                    }
                }
            }
        }
        return days;
    }

    /**
     * 按小时"-"
     * 
     * @param listMap
     * @return
     */
    public static void dateFillByTime(List<Map<String, Object>> listMap, String date,
            String bizType) {
        String[] hours =
                {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                        "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
                        "23"};
        List<String> hourList = new ArrayList<>();
        for (int i = 0; i < hours.length; i++) {
            hourList.add(hours[i]);
        }
        Set<String> keys = null;
        // 没有数据
        if (listMap.size() == 0) {
            for (int i = 0; i < hourList.size(); i++) {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("settleDate", date);
                dataMap.put("bizType", bizType);
                dataMap.put("settleTime", hourList.get(i));
                dataMap.put("cntSucces", "-");
                listMap.add(dataMap);
            }
        } else {
            for (int i = 0; i < hourList.size(); i++) {
                for (Map<String, Object> map : listMap) {
                    if (keys == null) {
                        keys = map.keySet();
                    }
                    /*
                     * if (date == null) { // 20141201 date = (String)
                     * map.get("settleDate"); } if (bizType == null) { // 1:天猫 bizType =
                     * (String) map.get("bizType"); }
                     */
                    String formatTime = (String) map.get("settleTime");
                    if (hourList.contains(formatTime)) {
                        hourList.remove(formatTime);
                    }
                }
            }
            // List<Map<String, Object>> fillDate = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < hourList.size(); i++) {
                Map<String, Object> nMap = new HashMap<String, Object>();
                for (String key : keys) {
                    if (key.equals("settleTime")) {
                        nMap.put(key, hourList.get(i).trim());
                    } else if (key.equals("settleDate")) {
                        nMap.put(key, date);
                    } else if (key.equals("bizType")) {
                        nMap.put(key, bizType);
                    } else {
                        nMap.put(key, "-");
                    }
                }
                listMap.add(nMap);
            }
        }
        return;
    }

    /**
     * 将字符串格式化为月份
     * 
     * @param strDate
     * @return
     */
    public static Date parseMonth(String strDate) {
        if (ToolUtils.isEmpty(strDate)) {
            return null;
        }
        try {
            if (strDate.length() > 6) {
                strDate = strDate.substring(0, 6);
            }
            Date date = FORMATDATE_MONTH.parse(strDate);
            return date;
        } catch (ParseException e) {
            logger.error(
                    "load DateUtils 加载parseMonth方法.. parseException..，参数：" + strDate, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }
        return null;
    }


    /**
     * 将字符串格式化为日期
     * 
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate) {
        if (ToolUtils.isEmpty(strDate)) {
            return null;
        }
        Date date = null;
        try {
            // 长度大于6 就是年月日
            if (strDate.length() > 6) {
                date = FORMATDATE_DAY.parse(strDate);
            } else {
                date = FORMATDATE_MONTH.parse(strDate);
            }
            return date;
        } catch (ParseException e) {
            logger.error(
                    "load DateUtils 加载formatDate方法.. parseException..，参数：" + strDate, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }
        return null;
    }

    /**
     * 计算指定日期到betweenDays这个值之间的天数
     * 
     * @param strDate
     * @param betweenDays
     * @return
     */

    public static List<Object> dateList(String strDate, int betweenDays) {
        Date date = parseDate(strDate);
        DateTime dateTime = new DateTime(date);
        // strDate在这个月的日期
        int days = dateTime.getDayOfMonth();
        // 将开始日期 和结束日期的每一天添加到 times
        List<Object> times = new ArrayList<>();
        for (int i = 0; i <= betweenDays; i++) {
            times.add(days + i);
        }
        return times;

    }

    /**
     * 计算俩个日期的之间的差值
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getBetweenDays(String startTime, String endTime) {
        Date date1 = parseDate(startTime);
        Date date2 = parseDate(endTime);
        DateTime dateTime1 = new DateTime(date1);
        DateTime dateTime2 = new DateTime(date2);
        // 计算两个日期间隔的天数
        return Days.daysBetween(dateTime1, dateTime2).getDays();
    }

    /**
     * 计算上一个日期的开始时间
     * 
     * @param strDate
     * @param days
     * @return
     */
    public static String getBeforeStartDate(String strDate, int days) {
        Date date = parseDate(strDate);
        DateTime dateTime = new DateTime(date).minusDays(DAY + days);
        return FORMATDATE_DAY.format(dateTime.toDate());

    }

    /**
     * 计算上一个日期的结束时间
     * 
     * @param strDate
     * @return
     */
    public static String getBeforeEndDate(String strDate) {
        Date date = parseDate(strDate);
        DateTime dateTime = new DateTime(date).minusDays(DAY);
        return FORMATDATE_DAY.format(dateTime.toDate());
    }

    /**
     * 获取上个月
     * 
     * @param month
     * @return
     */
    public static String getBeforeMonth(String month) {
        Date date = parseDate(month);
        DateTime dateMonth = new DateTime(date).minusMonths(1);
        return FORMATDATE_MONTH.format(dateMonth.toDate());
    }

    /**
     * 获取上个月的同一天
     * 
     * @param strDate
     * @return
     */
    public static String getBeforeMonthOfDay(String strDate) {
        Date date = parseDate(strDate);
        DateTime dateTime = new DateTime(date).minusMonths(1);
        return FORMATDATE_DAY.format(dateTime.toDate());
    }


    /**
     * 获取当前年月的最后一天的年月日
     * 
     * @param month
     * @return
     */
    public static String getMaxDayOfMonthAndYear(String time) {
        Date date = parseDate(time);
        DateTime dateMonth = new DateTime(date);
        dateMonth = dateMonth.dayOfMonth().withMaximumValue();
        return FORMATDATE_DAY.format(dateMonth.toDate());
    }

    /**
     * 格式化String类型日期
     * 
     * @param strDate 需要格式化的日期 如：20140505
     * @param returnFormatDate 需要转成什么样的格式 如：yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String formatdateToString(String strDate, String returnFormatDate)
            throws ParseException {
        String format = "";
        if (strDate.contains("-")) {
            format = "yyyy-MM-dd HH:mm:ss";
            format = (strDate.length() < format.length()) ? "yyyy-MM-dd" : format;
        } else if (strDate.contains("/")) {
            format = "yyyy/MM/dd HH:mm:ss";
            format = (strDate.length() < format.length()) ? "yyyy/MM/dd" : format;
        } else {
            format = "yyyyMMddHHmmss";
            format = (strDate.length() < format.length()) ? "yyyyMMdd" : format;
        }
        DateFormat startDf = new SimpleDateFormat(format);
        DateFormat endDf = new SimpleDateFormat(returnFormatDate);
        Date date = startDf.parse(strDate);
        return endDf.format(date);
    }

}
