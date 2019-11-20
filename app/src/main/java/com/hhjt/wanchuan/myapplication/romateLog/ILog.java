package com.hhjt.wanchuan.myapplication.romateLog;

/**
 * Created by Administrator
 * on 2019/11/1 10:54.
 * desc:
 */
public interface ILog {

    public void d(String tag, String msg);
    public void i(String tag, String msg);
    public void w(String tag, String msg);
    public void w(String tag, String msg, Throwable e);
    public void e(String tag, String msg);
    public void e(String tag, String msg, Throwable e);

    public void destory();
}