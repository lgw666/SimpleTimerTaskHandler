package com.gavinrowe.lgw.library;

/**
 * Author: Luo Guowen
 * Email: <a href="#">luoguowen123@qq.com</a>
 * Time: 2017/7/8
 * 定时任务
 */

@SuppressWarnings("all")
public abstract class SimpleTimerTask {
    /**
     * 标志
     * 判断是否为循环任务
     */
    private boolean mIsLoop;

    /**
     * 循环间隔
     * 单位：毫秒
     */
    private long mLoopInterval;

    /**
     * 普通任务构造
     * 默认循环标志 mIsLoop = false
     * 默认循环间隔 mLoopInterval = 0
     */
    public SimpleTimerTask() {
    }

    /**
     * 循环任务构造
     * 此构造执行时会设置循环标志 mIsLoop = true;
     *
     * @param loopInterval 循环间隔
     */
    public SimpleTimerTask(long loopInterval) {
        mIsLoop = true;
        mLoopInterval = Math.abs(loopInterval);
    }

    /**
     * 将要执行的业务
     */
    public abstract void run();

    /**
     * @return 是否为循环任务的标志
     */
    public boolean isLoop() {
        return mIsLoop;
    }

    /**
     * @return 循环间隔
     */
    public long getLoopInterval() {
        return mLoopInterval;
    }
}
