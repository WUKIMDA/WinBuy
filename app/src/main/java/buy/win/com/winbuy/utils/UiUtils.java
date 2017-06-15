package buy.win.com.winbuy.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;

/**
 * Created by BUTTON on 2017-05-25.
 */

public class UiUtils {

    private static Context sContext;
    private static Handler sHandler;
    private static int mainThreadId;

    /**
     * 初始化
     *
     * @param application
     */
    public static void init(Application application) {
        sContext = application.getApplicationContext();
        sHandler = new Handler();
        mainThreadId = android.os.Process.myTid();// 获取当前主线程id
    }

    /**
     * 返回上下文
     *
     * @return
     */
    public static Context getContext() {
        return sContext;
    }

    /**
     * 获得当前线程的id
     *
     * @return
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }

    /**
     * Handler.post(task)  没有判断是不是主线程
     *
     * @param task
     */
    public static void postTask(Runnable task) {
        sHandler.post(task);
    }

    /**
     * 获得handler
     *
     * @return
     */
    public static Handler getHandler() {
        return sHandler;
    }

    /**
     * 延迟提交任务
     * sHandler.postDelayed(task, time);
     * 没有判断是不是主线程
     */
    public static void postDelay(Runnable task, long time) {
        sHandler.postDelayed(task, time);
    }

    /*** 根据id获取字符串数组*/
    public static String[] getStringArray(int resId) {
        return sContext.getResources().getStringArray(resId);
    }

    /**
     * 判断当前是否运行在主线程
     */
    public static boolean isRunOnUiThread() {
        return getMainThreadId() == android.os.Process.myTid();
    }

    /*** 保证当前的操作运行在UI主线程*/
    public static void runOnUiThread(Runnable runnable) {
        if (isRunOnUiThread()) {
            runnable.run();
        } else {
            postTask(runnable);
        }
    }

    /*** 根据id获取字符串*/
    public static String getString(int id) {
        return sContext.getResources().getString(id);
    }

    /*** 根据id获取图片*/
    public static Drawable getDrawable(int id) {
        return sContext.getResources().getDrawable(id);
    }

    /*** 根据id获取颜色值 */
    public static int getColor(int id) {
        return sContext.getResources().getColor(id);
    }

    /**
     * 根据id获取尺寸
     */
    public static int getDimen(int id) {
        return sContext.getResources().getDimensionPixelSize(id);
    }

    /*** dp转px*/
    public static int dip2px(float dp) {
        float density = sContext.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    /*** px转dp*/
    public static float px2dip(float px) {
        float density = sContext.getResources().getDisplayMetrics().density;
        return px / density;
    }

    /*** 加载布局文件*/
    public static View inflate(int layoutId) {
        return View.inflate(sContext, layoutId, null);
    }

    /**
     * Log打印,d模式
     *
     * @param clazz
     * @param printStr
     */
    public static void logD(Class clazz, String printStr) {
        Log.d(clazz.getSimpleName(), clazz.getClass().getSimpleName() + " : " + printStr);
    }

    /**移除Runnable任务*/
    public static void removeTask(Runnable task) {
        sHandler.removeCallbacks(task);
    }

}
