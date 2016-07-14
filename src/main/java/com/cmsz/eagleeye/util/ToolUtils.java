package com.cmsz.eagleeye.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmsz.eagleeye.constant.Constants;


/**
 * @Description 工具类
 * @author ck-huyong
 * @date 2014年6月18日
 */
public class ToolUtils {

    private static final String YEAR = "年";
    private static final String SEASON = "季度";
    private static Logger logger = LoggerFactory.getLogger(ToolUtils.class);

    /**
     * @Description 将long转化成Calendar
     * @param longTime
     * @return
     */
    public static Calendar longConvertCalendar(long longTime) {

        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(longTime);

        return date;
    }

    /**
     * @Description 将long转化成Date
     * @param longTime
     * @return
     */
    public static Date longConvertDate(long longTime) {

        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(longTime);

        return date.getTime();
    }

    /**
     * @Description 字符串转换到时间格式
     * @param dateStr 需要转换的字符串
     * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date StringToDate(String dateStr, String formatStr) {
        logger.info("开始加载（StringToDate）...字符串转换时间格式，参数，dateStr：" + dateStr
                + ",formatStr:" + formatStr);
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("load ToolUtils 加载StringToDate方法.. parseException..，参数："
                    + dateStr, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }
        logger.info("加载完成（StringToDate）...结果：" + date);
        return date;
    }

    /**
     * @Description 字符串转换到时间格式,默认格式 yyyy-MM-dd
     * @param dateStr 需要转换的字符串
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date StringToDate2(String dateStr) {
        logger.info("开始加载（StringToDate2）...字符串转换默认时间格式,参数dateStr：" + dateStr);
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("load ToolUtils 加载StringToDate2方法.. parseException..，参数："
                    + dateStr, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }
        logger.info("加载完成（StringToDate2）...返回结果：" + date);
        return date;
    }

    /**
     * @Description 日期转化成相应格式日期,默认格式 yyyy-MM-dd
     * @param date 需要转换的时间
     * @param formatStr 转换的格式
     * @return 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date dateToFormatDate(Date date, String formatStr) {
        logger.info("开始加载(dateToFormatDate)...日期类型转换，参数date：" + date + ",formatStr:"
                + formatStr);
        if (date == null) {
            return null;
        }
        if (formatStr == null || formatStr.equals("")) {
            formatStr = "yyyy-MM-dd";
        }
        DateFormat sdf = new SimpleDateFormat(formatStr);
        String strDate = sdf.format(date);
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            logger.error("load ToolUtils 加载dateToFormatDate方法.. parseException..，日期参数："
                    + date + ",日期格式：" + formatStr, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }
        logger.info("加载完成(dateToFormatDate)...返回结果：" + date);
        return date;
    }

    /**
     * @Description 日期转化成相应格式日期,默认格式 yyyy-MM-dd
     * @param dateStr 需要转换的字符串
     * @return Date 返回转换后的时间(字符串类型)
     * @throws ParseException 转换异常
     */
    public static String dateToFormatStrDate(Date date, String formatStr) {
        logger.info("开始加载(dateToFormatStrDate)......日期类型转换字符串格式,参数date：" + date
                + ",formatStr:" + formatStr);
        String str = "";
        if (date == null) {
            return null;
        }
        if (formatStr == null || formatStr.equals("")) {
            formatStr = "yyyy-MM-dd";
        }
        DateFormat sdf = new SimpleDateFormat(formatStr);
        str = sdf.format(date);
        logger.info("开始完成(dateToFormatStrDate)......结果：" + str);
        return str;
    }

    /**
     * @Description 将QueryString转化map
     * @param request
     * @return
     */
    public static Map<String, Object> convertMap(HttpServletRequest request)
            throws Exception {
        Map<String, Object> queryMap = null;
        if (request.getQueryString() != null) {
            String queryStr = URLDecoder.decode(request.getQueryString(), "UTF-8");
            if (queryStr != null && !"".equals(queryStr)) {
                queryMap = new HashMap<String, Object>();
                String[] strArr = queryStr.split("&");
                for (String s : strArr) {
                    queryMap.put(s.substring(0, s.indexOf("=")),
                            s.substring(s.indexOf("=") + 1));
                }
            }
        }
        return queryMap;
    }

    /**
     * @Description 字符串转换数字
     * @author ck-huyong
     * @param number 出错返回0
     */
    public static int toInt(String number) {
        logger.info("开始加载(toInt).....字符串转换成数字,参数number：" + number);
        if (isEmpty(number)) {
            logger.error("<ToolUtils.toInt({}", number, ")>,return 0");
            return 0;
        }
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            logger.error("<ToolUtils.toInt({}", number, ")>,return 0", e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
            return 0;
        }
    }

    /**
     * @Description 判断非空
     * @author ck-huyong
     * @param obj
     * @return 如果为空返回true
     */
    public static boolean isEmpty(Object obj) {
        logger.info("开始加载（isEmpty）..判断是否为空，参数obj：" + obj);
        if (null == obj || "null".equals(obj)) {
            return true;
        }

        if (obj instanceof String) {
            return stringIsEmpty((String) obj);
        } else if (obj instanceof List) {
            return listIsEmpty((List<?>) obj);
        } else if (obj instanceof StringBuffer) {
            return stringBufferIsEmpty((StringBuffer) obj);
        }
        return false;
    }

    private static boolean stringIsEmpty(String str) {
        if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    private static boolean listIsEmpty(List<?> list) {
        if (list.isEmpty() || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 得到系统当前时间并且根据参数返回系统当前时间的前多少天或者多少个小时
     * 
     * @param differDay 天
     * @param hour 小时
     * @return
     * @author lfq
     */
    public static String getDefindDate(int differDay, int hour) {
        logger.info("开始加载（getDefindDate）....根据参数获取当前时间的前多少天或者多少小时，参数differDay："
                + differDay + ",hour:" + hour);
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.DAY_OF_MONTH, currentDate.get(Calendar.DAY_OF_MONTH)
                + differDay);
        currentDate.set(Calendar.HOUR_OF_DAY, hour);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(currentDate.getTime());
        logger.info("加载完成（getDefindDate）....返回结果" + dateStr);
        return dateStr;
    }

    private static boolean stringBufferIsEmpty(StringBuffer sb) {
        if (sb.toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * @Description String Format Long
     * @Title: stringFormatLong
     * @param value
     * @return: long
     */
    public static long stringFormatLong(String value) {
        logger.info("开始加载（stringFormatLong）...将字符串转换成Long，参数value：" + value);
        if (isEmpty(value)) {
            logger.info("<ToolUtils.stringFormatLong ({})>,return 0", value);
            return 0;
        }
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            logger.info("<ToolUtils.stringFormatLong exception, message:>,参数：" + value,
                    e.getMessage());
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
            return 0;
        }
    }

    /**
     * @Description 计算日期差
     * @param startDate
     * @param endDate
     * @param dateFormat
     * @param dateType
     * @return int
     */
    public static int differenceDate(String startDate, String endDate, String dateFormat,
            String dateType) {
        logger.info("开始加载（differenceDate）...计算日期差，参数startDate：" + startDate + ",endDate:"
                + endDate + ",dateFormat:" + dateFormat + ",dateType:" + dateType);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        int result = 0;
        int month = 0;
        try {
            c1.setTime(sdf.parse(startDate));
            c2.setTime(sdf.parse(endDate));
        } catch (ParseException e) {
            logger.error("load date ParseException... 日期类型转换出差，参数：" + startDate
                    + " and  " + endDate, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }
        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        if (Constants.DATETYPE_DAY.equals(dateType)) {
            long startT = fromDateStringToLong(startDate, dateFormat);
            long endT = fromDateStringToLong(endDate, dateFormat);

            long ss = (endT - startT) / (1000); // 共计秒数
            int hh = (int) ss / 3600; // 共计小时数
            result = hh / 24 + 1; // 共计天数
        } else if (Constants.DATETYPE_MONTH.equals(dateType)) { // 计算月份
            month = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);
            result = year * 12 + month + 1;
        } else if (Constants.DATETYPE_QUARTER.equals(dateType)) { // 计算月份
            month = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);
            result = year * 12 + month + 1;
        } else { // 计算年
            result = year + 1;
        }
        logger.info("加载完成（differenceDate）...日期差相差：" + result);
        return result;
    }

    /**
     * @Description 转换日期格式类型
     * @param inVal 日期
     * @param dateFormat 日期格式
     * @return
     */
    public static long fromDateStringToLong(String inVal, String dateFormat) {
        logger.info("开始加载（fromDateStringToLong）....将字符串日期转换成Long,参数inVal：" + inVal
                + ",dateFormat:" + dateFormat);
        // 此方法计算时间毫秒
        long l;
        Date date = null; // 定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat(dateFormat);
        try {
            date = inputFormat.parse(inVal); // 将字符型转换成日期型
        } catch (Exception e) {
            logger.error("load date exception... 参数：" + inVal, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }
        // 返回毫秒数
        l = date.getTime();
        logger.info("加载完成（fromDateStringToLong）....返回结果：" + l);
        return l;
    }

    /**
     * @Description 根据所属季度获取季度开始月份
     * @param querter 所属季度
     * @return 季度开始月份
     */
    public static String fromStartQuerter(String querter) {
        logger.info("开始加载（fromStartQuerter）....根据所属季度获取季度开始月份,参数querter：" + querter);
        String str = "";
        if (Constants.QUERTER_ONE.equals(querter)) { // 一季度1-3
            str = "-01";
        } else if (Constants.QUERTER_TWO.equals(querter)) { // 二季度4-6
            str = "-04";
        } else if (Constants.QUERTER_THREE.equals(querter)) { // 三季度7-9
            str = "-07";
        } else { // 四季度10-12
            str = "-10";
        }
        logger.info("加载完成（fromStartQuerter）....返回结果：" + str);
        return str;
    }

    /**
     * @Description 根据所属季度获取季度结束月份
     * @param querter 所属季度
     * @return 季度开始月份
     */
    public static String fromEndQuerter(String querter) {
        logger.info("开始加载（fromEndQuerter）....根据所属季度获取季度开始月份,参数querter：" + querter);
        String str = "";
        if (Constants.QUERTER_ONE.equals(querter)) { // 一季度1-3
            str = "-03";
        } else if (Constants.QUERTER_TWO.equals(querter)) { // 二季度4-6
            str = "-06";
        } else if (Constants.QUERTER_THREE.equals(querter)) { // 三季度7-9
            str = "-09";
        } else { // 四季度10-12
            str = "-12";
        }
        logger.info("加载完成（fromEndQuerter）....返回结果：" + str);
        return str;
    }

    /**
     * @Description 将字符串格式的日期转化成年月日格式类型的日期
     * @param strDate 日期
     * @param dateType 当前日期格式
     * @param resDateType 输出的日期格式
     * @return
     */
    public static String getChangeDate(String strDate, String dateType, String resDateType) {
        logger.info("开始加载（getChangeDate）....将字符串格式的日期转化成年月日格式类型的日期，参数strDate：" + strDate
                + ",dateType:" + dateType + ",resDateType:" + resDateType);
        Date date = null;
        String str = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat(dateType);
        SimpleDateFormat sdf = new SimpleDateFormat(resDateType);
        try {
            date = inputFormat.parse(strDate);
        } catch (ParseException e) {
            logger.error("load getChangeDate parseException... 参数1:" + strDate + "参数2:"
                    + dateType + "参数3:" + resDateType, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str1 = sw.toString();
            logger.error("错误信息详情:" + str1);
        }
        str = sdf.format(date);
        logger.info("加载结束（getChangeDate）...转换结果：" + str);
        return str;
    }

    /**
     * @Description 重组查询代码条件
     * @param property
     * @return string
     */
    public static String assembleProperty(String property) {
        logger.info("开始加载(assembleProperty)....... 重新组装查询代码条件,参数：" + property);
        if (!"".equals(property) && property != null) {
            String[] str = property.split(",");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length; i++) {
                if (i == str.length - 1) {
                    sb.append("'" + str[i] + "'");
                } else {
                    sb.append("'" + str[i] + "',");
                }
            }
            logger.info("加载结束(assembleProperty)......重组结果：" + sb.toString());
            return sb.toString();
        } else {
            logger.info("加载结束(assembleProperty)......重组结果：" + null);
            return null;
        }
    }

    /**
     * 获取某月最后一天日期
     * 
     * @author fb-yuguangda
     * @param date
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getLastDate(String date) {
        logger.info("开始加载(getLastDate)...获取月份的最后一天，参数date：" + date);
        String lastDate = null;
        Date dates = null;
        try {
            dates = new SimpleDateFormat("yyyy-MM").parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dates);
            calendar.add(calendar.MONTH, 1);
            lastDate = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
            lastDate = lastDate + "-01";
            dates = new SimpleDateFormat("yyyy-MM-dd").parse(lastDate);
            calendar.setTime(dates);
            calendar.add(calendar.DATE, -1);
            lastDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        } catch (ParseException e) {
            logger.error("change getLastDate parseException...类型转换出错，参数：" + date, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }
        logger.info("加载完成(getLastDate)...结果：" + lastDate);
        return lastDate;
    }

    /**
     * @Description 计算表格总共多少列
     * @author ck-huyong
     * @Date 2014年8月1日
     * @param assessId 考核对象 Id
     * @param dateType 查询日期类型
     * @param days 时间相隔天数
     * @param dateRows 次次级标题挎列数
     * @param row 起始列数
     * @return 表格总共多少列
     */
    public static int CalculationRows(String assessId, String dateType, int days,
            int dateRows, int row) {
        logger.info("开始加载(CalculationRows)....计算PDF导出表格列数，参数, 考核对象 Id:" + assessId
                + ",查询日期类型:" + dateType + ",时间相隔天数:" + days + ",次次级标题挎列数:" + dateRows
                + ",起始列数:" + row + ".");
        int rows = 0;
        if (Constants.DATETYPE_DAY.equals(dateType)) {
            rows = row + days * dateRows;
        } else if (Constants.DATETYPE_QUARTER.equals(dateType)) {
            rows = row + (days / 3 * dateRows);
        } else {
            rows = row + days * dateRows;
        }
        logger.info("加载完成(CalculationRows)...表格列数为：" + rows);
        return rows;
    }

    /**
     * @Description 查询出两个时间之间的所有日期
     * @author ck-huyong
     * @date 2014年8月1日
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param dateType 日期类型
     * @param dateFormat 当前日期格式
     * @param newDateFormat 输出日期格式
     * @param startQuarter 开始季度
     * @param endQuarter 结束季度
     * @return
     */
    public static List<String> secondaryTitle(String startDate, String endDate,
            String dateType, String dateFormat, String newDateFormat,
            String startQuarter, String endQuarter) {
        logger.info("开始加载(secondaryTitle)....查询出两个时间之间的所有日期,参数，开始日期:" + startDate
                + "，结束日期:" + endDate + "，日期类型:" + dateType + "，当前日期格式:" + dateFormat
                + "，输出日期格式:" + newDateFormat + "，开始季度:" + startQuarter + "，结束季度:"
                + endQuarter + "，");
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        SimpleDateFormat sfResult = new SimpleDateFormat(newDateFormat);
        if (dateType.equals(Constants.DATETYPE_QUARTER)) {
            dateList = changerQuerter(startDate, endDate, startQuarter, endQuarter);
        } else {
            Date startDateNews = null;
            Date endDateNews = null;
            try {
                startDateNews = sf.parse(startDate);
                endDateNews = sf.parse(endDate);
            } catch (ParseException e) {
                logger.error("change secondaryTitle parseException...时间格式转换出错，开始日期："
                        + startDate + ",结束日期：" + endDate, e);
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                logger.error("错误信息详情:" + str);
            }
            Calendar caStartDate = Calendar.getInstance();
            caStartDate.setTime(startDateNews);

            Calendar caEndDate = Calendar.getInstance();
            caEndDate.setTime(endDateNews);

            caStartDate.compareTo(caEndDate);
            while (true) {
                dateList.add(sfResult.format(caStartDate.getTime()));
                if (caStartDate.compareTo(caEndDate) == 0) {
                    break;
                } else if (caStartDate.compareTo(caEndDate) < 0) {
                    if (dateType.equals(Constants.DATETYPE_DAY)) {
                        caStartDate.add(Calendar.DAY_OF_MONTH, 1);
                    } else if (dateType.equals(Constants.DATETYPE_MONTH)) {
                        caStartDate.add(Calendar.MONTH, 1);
                    } else if (dateType.equals(Constants.DATETYPE_YEAR)) {
                        caStartDate.add(Calendar.YEAR, 1);
                    }
                }
            }
        }
        logger.info("加载完成(secondaryTitle)....返回结果:" + dateList);
        return dateList;
    }

    /**
     * @Description 查出两个季度之间的所有季度
     * @author ck-huyong
     * @Date 2014年8月1日
     * @param startDate 开始年份
     * @param endDate 结束年份
     * @param startQuarter 开始季度
     * @param endQuarter 结束季度
     * @return
     */
    public static List<String> changerQuerter(String startDate, String endDate,
            String startQuarter, String endQuarter) {
        logger.info("开始加载(changerQuerter)....查出两个季度之间的所有季度,参数，startDate:" + startDate
                + "，endDate:" + endDate + "，startQuarter:" + startQuarter
                + "，endQuarter:" + endQuarter);
        List<String> querterList = new ArrayList<String>();
        String startSeason = startQuarter;
        String endSeason = endQuarter;
        int startMonth = Integer.parseInt(startQuarter) * 3 - 2;
        int endMonth = Integer.parseInt(endQuarter) * 3 - 2;
        if (startMonth < 10) {
            startSeason = "0" + startMonth;
        } else {
            startSeason = startMonth + "";
        }

        if (endMonth < 10) {
            endSeason = "0" + endMonth;
        } else {
            endSeason = endMonth + "";
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        Date startDateNew = null;
        Date endDateNew = null;
        try {
            startDateNew = sf.parse(startDate + startSeason);
            endDateNew = sf.parse(endDate + endSeason);
        } catch (ParseException e) {
            logger.error("change changerQuerter parseException...时间格式转换出错，开始日期："
                    + startDate + startSeason + ",结束日期：" + endDate + endSeason, e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            logger.error("错误信息详情:" + str);
        }

        Calendar caStartDate = Calendar.getInstance();
        caStartDate.setTime(startDateNew);

        Calendar caEndDate = Calendar.getInstance();
        caEndDate.setTime(endDateNew);
        String str = "";
        while (true) {
            int months = caStartDate.get(Calendar.MONTH);
            if (months == 0) {
                str = "一";
            } else if (months == 3) {
                str = "二";
            } else if (months == 6) {
                str = "三";
            } else if (months == 9) {
                str = "四";
            }
            querterList.add(caStartDate.get(Calendar.YEAR) + YEAR + str + SEASON);
            if (caStartDate.compareTo(caEndDate) == 0) {
                break;
            } else if ((caStartDate.compareTo(caEndDate)) < 0) {
                caStartDate.add(Calendar.MONTH, 3);
            }
        }
        logger.info("加载完成(changerQuerter)....返回结果:" + querterList);
        return querterList;
    }


    /**
     * 根据 orderKey对集合进行排序
     * 
     * @param listMap 集合
     * @param orderKey 排序字段
     */
    public static void sortMap(List<Map<String, Object>> listMap, final String orderKey) {
        Collections.sort(listMap, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> asc, Map<String, Object> desc) {
                // 根据key取出对应的值
                System.out.println(asc.get(orderKey));
                String ascstr = String.valueOf(asc.get(orderKey));
                String descstr = String.valueOf(desc.get(orderKey));
                // 升序排序
                return ascstr.compareTo(descstr);
            }
        });

    }

    /**
     * 将String转换成list集合
     * 
     * @param strArray
     * @return
     */
    public static List<Object> getListForsplitArray(String strArray) {
        List<Object> result = new ArrayList<Object>();
        if (strArray == null) {
            return null;
        } else if (strArray.contains(",")) {
            result.addAll(Arrays.asList(strArray.split(",")));
        } else if (strArray.equals("-1")) {
            return null;
        } else {
            result.add(strArray);
        }
        return result;
    }

    /**
     * 判断字符串是否是数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        // 判断传递来的是否是数字 
        int begin = 0;
        boolean once = true;
        if (str == null || str.trim().equals("")) {
            return false;
        }
        str = str.trim();
        if (str.startsWith("+") || str.startsWith("-")) {
            if (str.length() == 1) {
                // "+" "-"
                return false;
            }
            begin = 1;
        }
        for (int i = begin; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                if (str.charAt(i) == '.' && once) {
                    // '.' can only once
                    once = false;
                } else {
                    return false;
                }
            }
        }
        if (str.length() == (begin + 1) && !once) {
            // "." "+." "-."
            return false;
        }
        return true;
    }

    public static List<String> getDateList(String startDate, String endDate) {
        List<String> dateTime = new ArrayList<String>();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date sDate = null;
        Date eDate = null;
        try {
            sDate = df.parse(startDate);
            eDate = df.parse(endDate);
            long day = (eDate.getTime() - sDate.getTime()) / (24 * 60 * 60 * 1000);
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i <= day; i++) {
                calendar.setTime(sDate);
                calendar.add(Calendar.DAY_OF_MONTH, +i);
                String strDate = df.format(calendar.getTime());
                dateTime.add(strDate);
            }
        } catch (ParseException e) {
            logger.error("日期转换解析异常...", e.getMessage());
            e.printStackTrace();
        }
        return dateTime;
    }

    public static List<String> getTimeList() {
        List<String> dateTime = new ArrayList<String>();
        String prefix = "0";
        String result = "";
        for (int i = 0; i < 24; i++) {
            if (i / 10 < 1) {
                result = prefix + i;
            } else {
                result = i + "";
            }
            dateTime.add(result);
        }
        return dateTime;
    }
    /**
     * 获取集合对象中某个属性值的集合
     * 
     * @param list 集合
     * @param filed 属性
     * @return
     */
    public static List getFiledList(List list, String filed) {
        if (ToolUtils.isEmpty(list)) return null;
        List filedList = new ArrayList();
        try {

            for (Object obj : list) {
                Class clazz = obj.getClass();// 获取集合中的对象类型
                Field[] fds = clazz.getDeclaredFields();// 获取他的字段数组
                for (Field field : fds) {// 遍历该数组
                    String fdname = field.getName();// 得到字段名，

                    Method method = clazz.getMethod("get" + change(fdname), null);// 根据字段名找到对应的get方法，null表示无参数

                    if (null != method && filed.equals(fdname)) {
                        Object val = method.invoke(obj, null);
                        filedList.add(val);
                    }

                }
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return filedList;
    }

    /**
     * @param src 源字符串
     * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
     */
    public static String change(String src) {
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * bigDemic 两个数相除
     * 
     * @param source
     * @param target
     * @param len 保留两位小数
     * @return
     */
    public static BigDecimal divide(Integer source, Integer target, int len) {
        BigDecimal proSuccDecimal = new BigDecimal(source * 100);
        BigDecimal proAllDecimal = null;
        if (target == 0) {
            target = 1;
            proAllDecimal = new BigDecimal(target);
        } else {
            proAllDecimal = new BigDecimal(target);
        }
        BigDecimal succRate =
                proSuccDecimal.divide(proAllDecimal, len, BigDecimal.ROUND_HALF_UP);
        return succRate;
    }

    /**
     * 导出Excel绝对路径模板
     * 
     * @param request
     * @param templateFileName
     * @return
     * 
     */
    public static String getTemplate(HttpServletRequest request, String templateFileName) {

        String filePathName =
                request.getSession().getServletContext().getRealPath(File.separator);
        filePathName =
                filePathName + "WEB-INF" + File.separatorChar + "classes"
                        + File.separatorChar + "inspection" + File.separatorChar
                        + templateFileName;
        return filePathName;
    }

    /**
     * 将一个 JavaBean 对象转化为一个 Map
     * 
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static Map<String, Object> convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 模糊匹配查询时的特殊字符转义处理
     * 
     * @param param
     * @return
     */
    public static String replace$(String param) {
        /* A{1},[A-Z] */
        final String[] chars = {"%", "_"};
        for (String str : chars) {
            if (!ToolUtils.isEmpty(param) && param.contains(str)) {
                param = param.replace(str, "\\" + str);
            }
        }
        return param;
    }

}
