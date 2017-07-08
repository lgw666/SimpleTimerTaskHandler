package com.gavinrowe.lgw.library;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Author: Luo Guowen
 * Email: <a href="#">luoguowen123@qq.com</a>
 * Time: 2017/7/8
 * 工具类，处理时间
 */
@SuppressWarnings("all")
public final class TimeUtils {
    private static final String TAG = "TimeUtils";
    /**
     * 时间格式器
     * 最小单位为秒
     * 最大单位为月
     */
    private static final SimpleDateFormat mFormatterMonthToSecond = new SimpleDateFormat("HH/dd HH:mm:ss", Locale.getDefault());


    /**
     * 获取执行任务前的时间到目标时间的时间差
     * 公式：targetTime - firstTime
     *
     * @param firstTime  执行任务前的时间
     * @param targetTime 目标时间
     * @return 时间差
     */
    private static long getDifferBetweenTime(long firstTime, long targetTime) {
        long differTime = targetTime - firstTime;
        Log.i(TAG, "getDifferBetweenTime: 当前时间到目标时间的时间差：" + differTime);
        return differTime;
    }

    /**
     * 获取第一次执行任务的延迟时间，精确到小时
     *
     * @param hour 选择的小时
     * @return 第一次执行任务的延迟时间
     */
    public static long getFirstSendTaskDelayedTime(int hour) {
        long currentTime = System.currentTimeMillis();
        long firstSendTaskTime = getDifferBetweenTime(currentTime, getTodayTargetTime(hour));

        Log.d(TAG,
                "getFirstSendTaskTimeToHour，第一次执行任务的时间: "
                        + mFormatterMonthToSecond.format(currentTime + firstSendTaskTime)
                        + "，到该时间还差：" + firstSendTaskTime);
        return firstSendTaskTime;
    }

    /**
     * 获取第一次执行任务的延迟时间，精确到分
     *
     * @param hour   选择的小时
     * @param minute 选择的分钟
     * @return 第一次执行任务的延迟时间
     */
    public static long getFirstSendTaskDelayedTime(int hour, int minute) {
        long currentTime = System.currentTimeMillis();
        long firstSendTaskTime = getDifferBetweenTime(currentTime, getTodayTargetTime(hour, minute));

        Log.d(TAG,
                "getFirstSendTaskTimeToHour，第一次执行任务的时间: "
                        + mFormatterMonthToSecond.format(currentTime + firstSendTaskTime)
                        + "，到该时间还差：" + firstSendTaskTime);
        return firstSendTaskTime;
    }

    /**
     * 获取第一次执行任务的延迟时间，精确到秒
     *
     * @param hour   选择的小时
     * @param minute 选择的分钟
     * @param second 选择的秒数
     * @return 第一次执行任务的延迟时间
     */
    public static long getFirstSendTaskDelayedTime(int hour, int minute, int second) {
        long currentTime = System.currentTimeMillis();
        long firstSendTaskTime = getDifferBetweenTime(currentTime, getTodayTargetTime(hour, minute, second));

        Log.d(TAG,
                "getFirstSendTaskTimeToHour，第一次执行任务的时间: "
                        + mFormatterMonthToSecond.format(currentTime + firstSendTaskTime)
                        + "，到该时间还差：" + firstSendTaskTime);
        return firstSendTaskTime;
    }

    /**
     * 初始化Calendat
     *
     * @return
     */
    private static Calendar getCalendar(int hour) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 获取某一小时时刻的时间戳，以当天为基准
     *
     * @param hour 指定的小时，若小于0，日期会-1，若大于24会+1
     * @return 当天某一小时时刻的时间戳
     */
    private static long getTodayTargetTime(int hour) {
        Calendar cal = getCalendar(hour);
        long time = cal.getTimeInMillis();
        Log.i(TAG, "getTodayTargetTime，当天第: " + hour + "小时时间戳：" + time);
        return time;
    }

    /**
     * 获取某一小时某一分钟时刻的时间戳，以当天为基准
     *
     * @param hour   指定的小时，若小于0，日期会-1，若大于24会+1
     * @param minute 指定的分钟，若小于0，小时会-1，若大于60会+1
     * @return 当天某一小时时刻的时间戳某一分钟时刻的时间戳
     */
    private static long getTodayTargetTime(int hour, int minute) {
        Calendar cal = getCalendar(hour);
        cal.set(Calendar.MINUTE, minute);
        long time = cal.getTimeInMillis();
        Log.i(TAG, "getTodayTargetTime，当天第: " + hour + "小时" + minute + "分时间戳：" + time);
        return time;
    }

    /**
     * 获取某一小时某一分钟某一秒时刻的时间戳，以当天为基准
     *
     * @param hour   指定的小时，若小于0，日期会-1，若大于24会+1
     * @param minute 指定的分钟，若小于0，小时会-1，若大于60会+1
     * @param second 指定的秒数，若小于0，分钟会-1，若大于60会+1
     * @return 当天某一小时时刻的时间戳某一分钟时刻的时间戳
     */
    private static long getTodayTargetTime(int hour, int minute, int second) {
        Calendar cal = getCalendar(hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        long time = cal.getTimeInMillis();
        Log.i(TAG, "getTodayTargetTime，当天第: " + hour + "小时" + minute + "分" + second + "秒时间戳：" + time);
        return time;
    }


}
