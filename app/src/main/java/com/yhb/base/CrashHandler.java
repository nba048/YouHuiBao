package com.yhb.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedback;
import com.yhb.mvp.view.activity.ErrorActivity;
import com.yhb.mvp.view.activity.LoadActivity;
import com.yhb.mvp.view.activity.MainActivity;

import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {
    ProjectApp mApp;

    public CrashHandler(ProjectApp application) {
        this.mApp = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        PgyCrashManager.reportCaughtException(mApp, new RuntimeException(ex));
//        ExceptoinHandler.handleException(ex);
        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(mApp, "请反馈崩溃操作，我们会在下次更新中修复！", Toast.LENGTH_LONG).show();
                Looper.loop();
                PgyFeedback.getInstance().showDialog(mApp);
                Looper.loop();
            }
        }.start();
        try {
            Intent intent = new Intent(mApp, ErrorActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(mApp, 100, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
            AlarmManager alarmManager = (AlarmManager) mApp.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 2000, pendingIntent);
            Thread.currentThread().sleep(2000);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mApp, e);
        }
        mApp.finish();

    }
}
