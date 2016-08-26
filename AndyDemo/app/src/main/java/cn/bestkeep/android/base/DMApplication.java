package cn.bestkeep.android.base;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

public class DMApplication extends Application
        implements CrashHandler.OnUncaughtExceptionCallBack
{
    protected DMActivityLifecycleCallbacks activityLifecycleCallbacks = new DMActivityLifecycleCallbacks();
    private static List<Activity> activities = new LinkedList<>();
    private static Activity activeActivity;
    protected static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this;
        activities.clear();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);

        ((Runnable) () -> {
            CrashHandler.getInstance().init(context, DMApplication.this);
            x.Ext.init(DMApplication.this);
            x.Ext.setDebug(true);
            asyncInit(context);
        }).run();
    }

    /*
    * in new Thread
    * */
    protected void asyncInit(Context context)
    {

    }

    public static Context getContext()
    {
        return context;
    }

    public static PackageInfo getPackageInfo()
    {
        try
        {
            return getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e)
        {
            LogUtil.w("can not found package info.", e);
        }
        return null;
    }

    public static int versionCode()
    {
        return getPackageInfo().versionCode;
    }

    public static String versionName()
    {
        return getPackageInfo().versionName;
    }

    public static Activity getActiveActivity()
    {
        return activeActivity;
    }

    public static int getActivityListSize()
    {
        return activities == null ? 0 : activities.size();
    }

    public void addActivity(Activity activity)
    {
        activities.add(activity);
    }

    public void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    public void setActiveActivity(Activity activity)
    {
        activeActivity = activity;
    }

    public static void exit(boolean killProcess)
    {
        try
        {
            for (Activity activity : activities)
            {
                if (activity != null && !activity.isFinishing())
                    activity.finish();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (killProcess)
            {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }
    }

    @Override
    public void onUncaughtException()
    {
        LogUtil.i("exit on uncaught exception");
        exit(true);
    }

    /*
    * Activity Lifecycle Callback invoke Methods
    * */
    protected void onCreatedActivity(Activity activity, Bundle savedInstanceState)
    {
        addActivity(activity);
    }

    protected void onStartedActivity(Activity activity)
    {
    }

    protected void onResumedActivity(Activity activity)
    {
        setActiveActivity(activity);
    }

    protected void onPausedActivity(Activity activity)
    {
    }

    protected void onStoppedActivity(Activity activity)
    {
    }

    protected void onSaveInstanceStateActivity(Activity activity, Bundle outState)
    {
    }

    protected void onDestroyedActivity(Activity activity)
    {
        removeActivity(activity);
        System.gc();
    }


    /*
    * Activity Lifecycle Callback
    * */
    private class DMActivityLifecycleCallbacks implements ActivityLifecycleCallbacks
    {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState)
        {
            LogUtil.d(String.format("Activity:'%s' created.", activity.getClass().getSimpleName()));
            onCreatedActivity(activity, savedInstanceState);
        }

        @Override
        public void onActivityStarted(Activity activity)
        {
            LogUtil.d(String.format("Activity:'%s' started.", activity.getClass().getSimpleName()));
            onStartedActivity(activity);
        }

        @Override
        public void onActivityResumed(Activity activity)
        {
            LogUtil.d(String.format("Activity:'%s' resumed.", activity.getClass().getSimpleName()));
            onResumedActivity(activity);
        }

        @Override
        public void onActivityPaused(Activity activity)
        {
            LogUtil.d(String.format("Activity:'%s' paused.", activity.getClass().getSimpleName()));
            onPausedActivity(activity);
        }

        @Override
        public void onActivityStopped(Activity activity)
        {
            LogUtil.d(String.format("Activity:'%s' stopped.", activity.getClass().getSimpleName()));
            onStoppedActivity(activity);
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState)
        {
            LogUtil.d(String.format("Activity:'%s' save instance state.", activity.getClass().getSimpleName()));
            onSaveInstanceStateActivity(activity, outState);
        }

        @Override
        public void onActivityDestroyed(Activity activity)
        {
            LogUtil.d(String.format("Activity:'%s' destroyed.", activity.getClass().getSimpleName()));
            onDestroyedActivity(activity);
        }
    }
}
