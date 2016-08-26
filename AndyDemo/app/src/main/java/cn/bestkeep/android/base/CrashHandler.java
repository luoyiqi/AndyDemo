package cn.bestkeep.android.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;


import com.andy.a.demo.BuildConfig;

import org.xutils.common.util.LogUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final CrashHandler INSTANCE = new CrashHandler();

    private Context context;
    private Thread.UncaughtExceptionHandler defaultHandler;
    private String deviceInfo;
    private Map<String, String> deviceInfoMap;
    private OnUncaughtExceptionCallBack callBack;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context, OnUncaughtExceptionCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && defaultHandler != null) {
            defaultHandler.uncaughtException(thread, ex);
            return;
        }
        LogUtil.w(ex);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LogUtil.w(e);
        }
        if (callBack != null) {
            callBack.onUncaughtException();
            return;
        }
        try {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Throwable e) {
            LogUtil.w(e);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        showToast();
//        if (ex instanceof IllegalArgumentException)
//        {
//            return true;
//        }
        if (!BuildConfig.DEBUG) {
            saveCrashInfo2File(ex);
        }
        return true;
    }

    private void saveCrashInfo2File(Throwable ex) {
        try {
            String message = getDeviceInfo() + "\n" + ex.getStackTrace();
            LogUtil.w(message);
            //FileUtil.save(FileHelper.createCrashFile(), message);
        } catch (Exception e) {
            LogUtil.w(e);
        }
    }

    private synchronized String getDeviceInfo() {
        if (deviceInfo != null) {
            return deviceInfo;
        }
        if (deviceInfoMap == null) {
            collectDeviceInfo();
        }
        StringBuffer info = new StringBuffer();
        for (Map.Entry<String, String> deviceInfoEntry : deviceInfoMap.entrySet()) {
            info.append(deviceInfoEntry.getKey())
                    .append("=")
                    .append(deviceInfoEntry.getValue())
                    .append("\n");
        }
        return deviceInfo = info.toString();
    }


    private void collectDeviceInfo() {
        try {
            deviceInfoMap = new HashMap<>();
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                deviceInfoMap.put("versionName", versionName);
                deviceInfoMap.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.w("an error occurred when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                deviceInfoMap.put(field.getName(), field.get(null).toString());
                LogUtil.d(field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                LogUtil.w("an error occurred when collect crash info", e);
            }
        }
    }

    private void showToast() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
    }

    interface OnUncaughtExceptionCallBack {
        void onUncaughtException();
    }
}
