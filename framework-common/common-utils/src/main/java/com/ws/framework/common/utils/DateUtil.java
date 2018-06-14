package com.ws.framework.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 * 
 */
public class DateUtil implements DateFormat
{

    /**
     * 
     * getToday:(取得当天日期). <br/>
     * 
     * @param format eg：yyyy-MM-dd
     * @return String
     * @since V1.0.0
     */
    public static String getToday(String format)
    {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return fmt.print(dt);
    }

    /**
     * 
     * getCurrentTime:(取得当前时间). <br/>
     * 
     * @param format eg:yyyy-MM-dd HH:mm:ss
     * @return String
     * @since V1.0.0
     */
    public static String getCurrentTime(String format)
    {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return fmt.print(dt);
    }

    /**
     * 
     * getCurrentDateTime:(取得当前日期). <br/>
     * 
     * @return Date
     * @since V1.0.0
     */
    public static Date getCurrentDateTime()
    {
        DateTime dt = new DateTime();
        return dt.toDate();
    }

    /**
     * 
     * date2String:(日期转指定格式的字符). <br/>
     * 
     * @param date
     * @param _iType
     * @return String
     * @since V1.0.0
     */
    public static String dateToString(Date date, String pattern)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
    
    /**
     * 
     * date2String:(日期转指定格式的字符). <br/>
     * 
     * @param date
     * @param _iType
     * @return String
     * @since V1.0.0
     */
    public static String toString(Date date, String pattern)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 
     * string2Date:(日期字符转Date). <br/>
     * @param dateString
     * @return java.util.Date
     * @since V1.0.0
     */
    public final static Date stringToDate(String dateString)
    {
        if (dateString == null || dateString.trim().length() == 0)
        {
            return new Date(0);
        }

        try
        {
            String strFormat = "";
            switch (dateString.length())
            {
                case 6: // yymmdd
                    strFormat = "yyMMdd";
                    break;
                case 8: // yyyymmdd
                    strFormat = "yyyyMMdd";
                    break;
                case 10: // yyyy-mm-dd
                    strFormat = "yyyy-MM-dd";
                    break;
                case 14:
                    strFormat = "yyyyMMddHHmmss";
                    break;
                case 16:
                    strFormat = "yyyy-MM-dd HH:mm";
                    break;
                default:
                    strFormat = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
            Date timeDate = simpleDateFormat.parse(dateString);
            return timeDate;
        }
        catch (Exception e)
        {
            return new Date(0);
        }
    }

    /**
     *
     * string2Date:(日期字符转Date). <br/>
     * @param dateString
     * @param pattern 格式
     * @return java.util.Date
     * @since V1.0.0
     */
    public final static Date toDate(String dateString,String pattern)
    {
        if (dateString == null || dateString.trim().length() == 0)
        {
            return new Date(0);
        }
        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date timeDate = simpleDateFormat.parse(dateString);
            return timeDate;
        }
        catch (Exception e)
        {
            return new Date(0);
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isDate(String strDate)
    {
        if (StringUtils.isEmpty(strDate))
        {
            return false;
        }
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 
     * getDateTimeOffset:(基于日期时间进行的偏移计算). <br/>
     * 
     * @param dateString 日期字符串
     * @param offset 偏移量，正数为天数相加，负数为天数相减
     * @param format 格式化输出
     * @param type 偏移计算类型
     * @return String
     * @since V1.0.0
     */
    public static String getDateTimeOffset(String dateString, int offset, String format, int type)
    {
        if (null == dateString || "".equals(dateString))
        {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        DateTime dt = DateTime.parse(dateString, fmt);

        switch (type)
        {
            // 年
            case 1:
                dt = dt.plusYears(offset);
                break;
            // 月
            case 2:
                dt = dt.plusMonths(offset);
                break;
            // 周
            case 3:
                dt = dt.plusWeeks(offset);
                // 日
            case 4:
                dt = dt.plusDays(offset);
                break;
            // 时
            case 5:
                dt = dt.plusHours(offset);
                break;
            // 分
            case 6:
                dt = dt.plusMinutes(offset);
                break;
            // 秒
            case 7:
                dt = dt.plusSeconds(offset);
                break;
            // 毫秒
            case 8:
                dt = dt.plusMillis(offset);
                break;
            default:
                break;
        }

        return fmt.print(dt);
    }

    /**
     * 
     * isLeap:(判读是否为闰年). <br/>
     * 
     * @param dateString
     * @return boolean
     * @since V1.0.0
     */
    public static boolean isLeap(String dateString)
    {
        if (null == dateString || "".equals(dateString))
        {
            return false;
        }
        DateTime dt = DateTime.parse(dateString);
        return dt.year().isLeap();
    }

    /**
     * 
     * getDayOfYear:(取得日期为本年的第几天). <br/>
     * 
     * @param dateString
     * @return int
     * @since V1.0.0
     */
    public static int getDayOfYear(String dateString)
    {
        if (null == dateString || "".equals(dateString))
        {
            return 0;
        }
        DateTime dt = DateTime.parse(dateString);
        return dt.getDayOfYear();
    }

    /**
     * 
     * getDayOfYear:(取得日期为本周的第几天). <br/>
     * 
     * @param dateString
     * @return int
     * @since V1.0.0
     */
    public static int getDayOfWeek(String dateString)
    {
        if (null == dateString || "".equals(dateString))
        {
            return 0;
        }
        DateTime dt = DateTime.parse(dateString);
        return dt.getDayOfWeek();
    }

    /**
     * 
     * getDayOfWeekText:(取得日期为星期几). <br/>
     * 
     * @param dateString
     * @return String
     * @since V1.0.0
     */
    public static String getDayOfWeekText(String dateString)
    {
        if (null == dateString || "".equals(dateString))
        {
            return null;
        }
        DateTime dt = DateTime.parse(dateString);
        return dt.dayOfWeek().getAsText();
    }

    /**
     * 
     * getWeekOfWeekyear:(取得日期为本年的第几周). <br/>
     * 
     * @param dateString
     * @return int
     * @since V1.0.0
     */
    public static int getWeekOfWeekyear(String dateString)
    {
        if (null == dateString || "".equals(dateString))
        {
            return 0;
        }
        DateTime dt = DateTime.parse(dateString);
        return dt.getWeekOfWeekyear();
    }
    
  
   

    /**
     * 
     * getMonthMaxDay:(取得月day里面最大的值). <br/>
     * 
     * @param dateString
     * @return int
     * @since V1.0.0
     */
    public static int getMonthMaxDay(String dateString)
    {
        if (null == dateString || "".equals(dateString))
        {
            return 0;
        }
        DateTime dt = DateTime.parse(dateString);
        return dt.dayOfMonth().getMaximumValue();
    }

    /**
     * 
     * getDaysBetween:(计算两个日期间的天数). <br/>
     * 
     * @param beginDate
     * @param endDate
     * @return int
     * @since V1.0.0
     */
    public static int getDaysBetween(String beginDate, String endDate)
    {
        LocalDate beginDT = new LocalDate(beginDate);
        LocalDate endDT = new LocalDate(endDate);
        return Days.daysBetween(beginDT, endDT).getDays();
    }

    /**
     * 
     * compare:(两个日期进行大小比较). <br/>
     * 
     * @param date1
     * @param date2
     * @return int -1：date1 < date2；0：date1 = date2；1：date1 > date2
     * @since V1.0.0
     */
    public static int compare(String date1, String date2)
    {
        int comRes = 0;
        DateTime dt1 = DateTime.parse(date1);
        DateTime dt2 = DateTime.parse(date2);
        if (dt1.isEqual(dt2))
        {
            // date1 = date2
            comRes = 0;
        }
        else if (dt1.isBefore(dt2))
        {
            // date1 < date2
            comRes = -1;
        }
        else
        {
            // date1 > date2
            comRes = 1;
        }
        return comRes;
    }
    
    /**
     * 比较两个时间的大小
     * @param dateTime1
     * @param datetime2
     * @return date1<data2 return -1 ,date1 = data2 return 0,date1>data2 return 1
     */
    public static int compareTime(Date dateTime1, Date datetime2) {
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(dateTime1);
		c2.setTime(datetime2);
		return c1.compareTo(c2);
	}

    /**
     * 比较两个日期的大小
     * @param date1
     * @param date2
     * @return date1<data2 return -1 ,date1 = data2 return 0,date1>data2 return 1
     */
	public static int compareDate(Date date1, Date date2) {
		date1 = toDate(toString(date1, YEAR_MONTH_DAY), YEAR_MONTH_DAY);
		date2 = toDate(toString(date2, YEAR_MONTH_DAY), YEAR_MONTH_DAY);
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(date1);
		c2.setTime(date2);

		return c1.compareTo(c2);
	}

    public static Date now()
    {
        return new GregorianCalendar().getTime();
    }

    /**
     * 根据生日计算年龄
     * 
     * @param birthday
     * @return
     */
    public static int getCurrentAgeByBirthday(Date birthday)
    {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday))
        {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (age != 0 && monthNow <= monthBirth)
        {
            if (monthNow == monthBirth)
            {
                if (dayOfMonthNow <= dayOfMonthBirth)
                {
                    age--;
                }
            }
            else
            {

                age--;
            }
        }
        return age;
    }

    public static Date nextDays(Date date, int nextDays)
    {
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(date);
        GregorianCalendar cloneCalendar = (GregorianCalendar) c1.clone();
        cloneCalendar.add(Calendar.DATE, nextDays);
        return cloneCalendar.getTime();
    }

    public static Date nextYears(Date date, int nextYear)
    {
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(date);
        GregorianCalendar cloneCalendar = (GregorianCalendar) c1.clone();
        cloneCalendar.add(Calendar.YEAR, nextYear);
        return cloneCalendar.getTime();
    }

    public static Date nextMonths(Date date, int nextMonth)
    {
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(date);
        GregorianCalendar cloneCalendar = (GregorianCalendar) c1.clone();
        cloneCalendar.add(Calendar.MONTH, nextMonth);
        return cloneCalendar.getTime();
    }
    
    /**
     * 根据日期获取该日期对应的星期
     * @author zhuzhengjie
     * @return [星期一:1,星期二:2,...,星期六:6,星期天:7]
     */
    public static int getWeekDayByDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //转换成星期一=1,星期二=2...星期六=6,星期天=7
        if(dayOfWeek==1){
        	return WEEK_7;
        }else{
        	return dayOfWeek-1;
        }
    }

    /**
     * 获取中文星期
     * @return 中文星期
     */
    public static String getDayOfWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return DAYNAMES[dayOfWeek - 1];
    }

    /**
     * 获取中文星期
     * @return 中文星期
     */
    public static String getDayOfWeek()
    {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return DAYNAMES[dayOfWeek - 1];
    }
}
