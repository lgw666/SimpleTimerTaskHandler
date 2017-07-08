package com.gavinrowe.lgw.library;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;

/**
 * Author: Luo Guowen
 * Email: <a href="#">luoguowen123@qq.com</a>
 * Time: 2017/7/8
 * 定时任务处理器
 */

@SuppressWarnings("all")
public class SimpleTimerTaskHandler extends Handler {
    private static final String TAG = "SimpleTimerTaskHandler";

    // 存放需要执行的任务
    private SparseArray<SimpleTimerTask> mTasks = new SparseArray<>();

    private static SimpleTimerTaskHandler mSimpleTimerTaskHandler;

    private SimpleTimerTaskHandler() {
        super(Looper.getMainLooper());
    }

    public static SimpleTimerTaskHandler getInstance() {
        if (null == mSimpleTimerTaskHandler) {
            mSimpleTimerTaskHandler = new SimpleTimerTaskHandler();
        }
        return mSimpleTimerTaskHandler;
    }

    @Override
    public void handleMessage(Message msg) {
        int pos = msg.what;
        Log.i(TAG, "handleMessage: The task is " + pos);
        SimpleTimerTask currentTask = mTasks.get(pos);
        if (null == currentTask) {
            Log.e(TAG, "handleMessage: No task of " + pos + "，please create it first!");
        } else {
            currentTask.run();
            if (currentTask.isLoop()) {
                long loopInterval = currentTask.getLoopInterval();
                sendEmptyMessageDelayed(pos, loopInterval);
                Log.i(TAG, "handleMessage: The task of " + pos + " is a loop task, and it will be executed after " + loopInterval + " millis");
            } else {
                mTasks.remove(pos);
            }
        }
    }

    /**
     * 即时发送任务
     *
     * @param taskNum 任务编号
     * @param task    任务
     */
    public void sendTask(int taskNum, SimpleTimerTask task) {
        int msg = saveTask(taskNum, task);
        sendEmptyMessage(msg);
        Log.i(TAG, "sendTask: 当前任务类型为即时任务，取绝对值后的任务编号为" + msg);
    }

    /**
     * 延时发送任务
     *
     * @param taskNum     任务编号
     * @param task        任务
     * @param delayMillis 延时时间，单位毫秒
     */
    public void sendTaskDelayed(int taskNum, SimpleTimerTask task, long delayMillis) {
        int msg = saveTask(taskNum, task);
        sendEmptyMessageDelayed(msg, delayMillis);
        Log.i(TAG, "sendTaskDelayed: 当前任务类型为延时任务，取绝对值后的任务编号为" + msg);
    }

    /**
     * 保存要执行的任务
     */
    private int saveTask(int taskNum, SimpleTimerTask task) {
        Log.i(TAG, "saveTask: 保存要执行的任务编号 " + taskNum);
        int msg = Math.abs(taskNum);
        mTasks.put(msg, task);
        Log.i(TAG, "saveTask: 保存后的任务编号" + msg);
        return msg;
    }


    /**
     * 定时发送任务，精确到小时
     *
     * @param taskNum 任务编号
     * @param task    任务
     * @param hour    指定小时
     */
    public void sendTimerTask(int taskNum, SimpleTimerTask task, int hour) {
        int msg = saveTask(taskNum, task);
        sendEmptyMessageDelayed(msg, TimeUtils.getFirstSendTaskDelayedTime(hour));
        Log.i(TAG, "sendTimerTask: 当前任务类型为定时任务（精确到小时），取绝对值后的任务编号为" + msg + "，hour = " + hour);

    }

    /**
     * 定时发送任务，精确到分
     *
     * @param taskNum 任务编号
     * @param task    任务
     * @param hour    指定小时
     * @param minute  指定分钟
     */
    public void sendTimerTask(int taskNum, SimpleTimerTask task, int hour, int minute) {
        int msg = saveTask(taskNum, task);
        sendEmptyMessageDelayed(msg, TimeUtils.getFirstSendTaskDelayedTime(hour, minute));
        Log.i(TAG, "sendTimerTask: 当前任务类型为定时任务（精确到分），取绝对值后的任务编号为" + msg + "，hour = " + hour + "，minute = " + minute);
    }

    /**
     * 定时发送任务，精确到秒
     *
     * @param taskNum 任务编号
     * @param task    任务
     * @param hour    指定小时
     * @param minute  指定分钟
     * @param second  指定秒数
     */
    public void sendTimerTask(int taskNum, SimpleTimerTask task, int hour, int minute, int second) {
        int msg = saveTask(taskNum, task);
        sendEmptyMessageDelayed(msg, TimeUtils.getFirstSendTaskDelayedTime(hour, minute, second));
        Log.i(TAG, "sendTimerTask: 当前任务类型为定时任务（精确到秒），取绝对值后的任务编号为" + msg + "，hour = " + hour + "，minute = " + minute + "，second = " + second);
    }

}
