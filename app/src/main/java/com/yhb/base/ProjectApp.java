package com.yhb.base;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.pgyersdk.crash.PgyCrashManager;
import com.yhb.bean.response.User;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ProjectApp extends Application {

    private static ProjectApp mContext;
    private static List<FragmentActivity> mActivitys;
    private static Toast mToast;
    private static Handler mHandler;
    private static User mCurrentUser;

    /**
     * 当前是否是debug模式
     */
    public static boolean isRelease = true;

    @SuppressWarnings("static-access")
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mActivitys = new ArrayList<FragmentActivity>();
        mHandler = new Handler();
        x.Ext.init(this);
        Bmob.initialize(this, "1da3f86d370aefdc4e211944965af099");
        // 回调：通过框架调我们写的实现类的对象
        // 告诉框架调那个对象
        PgyCrashManager.register(this);
        Thread.currentThread().setDefaultUncaughtExceptionHandler(new CrashHandler(this));
    }

    public static ProjectApp getInstance() {
        return mContext;
    }

    public static List<FragmentActivity> getmActivitys() {
        return mActivitys;
    }

    public static void finish() {
        // 把所有的activity finish
        for (Activity activity : mActivitys) {
            activity.finish();
        }
        // 结束进程
        // Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static Toast getToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.TOP + Gravity.CENTER_HORIZONTAL, 0, 50);
            return mToast;
        }
        mToast.setText(content);
        return mToast;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static User getCurrentUser() {
        return mCurrentUser;
    }

    public static void setCurrentUser(User mCurrentUser) {
        ProjectApp.mCurrentUser = mCurrentUser;
    }


}
