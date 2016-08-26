package com.andy.a.demo.helper;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;


import cn.bestkeep.android.base.DMApplication;

import org.xutils.common.util.LogUtil;

public final class ResourcesHelper
{
    private static final ResourcesHelper INSTANCE = new ResourcesHelper();
    private static Context context;
    private static Resources resources;

    private ResourcesHelper()
    {
        context = DMApplication.getContext();
        resources = context.getResources();
    }


    private static final String DRAWABLE_RESOURCE_NAME_FORMAT = "%s_%s";
    private static final String STRING_RESOURCE_NAME_FORMAT = "ui_%s_%s";

    public static <T extends Enum<T>> int drawableIdentifier(Enum<T> t)
    {
        if (t == null)
        {
            return 0;
        }
        return drawableIdentifier(t.getClass(), t.name());
    }

    public static <T extends Enum<T>> int drawableIdentifier(Class<T> clazz, String value)
    {
        if (clazz == null)
        {
            return 0;
        }
        return drawableIdentifier(clazz.getSimpleName(), value);
    }

    public static <T extends Enum<T>> int drawableIdentifier(String prefix, T value)
    {
        if (value == null)
        {
            return 0;
        }
        return drawableIdentifier(prefix, value.name());
    }

    public static int drawableIdentifier(String prefix, String value)
    {
        if (prefix == null || value == null)
        {
            return 0;
        }
        return getIdentifier(String.format(DRAWABLE_RESOURCE_NAME_FORMAT, prefix, value), "drawable");
    }

    public static <T extends Enum<T>> int stringIdentifier(Enum<T> t)
    {
        if (t == null)
        {
            return 0;
        }
        return stringIdentifier(t.getClass(), t.name());
    }

    public static <T extends Enum<T>> int stringIdentifier(Class<T> clazz, String value)
    {
        if (clazz == null)
        {
            return 0;
        }
        return stringIdentifier(clazz.getSimpleName(), value);
    }

    public static <T extends Enum<T>> int stringIdentifier(String prefix, T value)
    {
        if (value == null)
        {
            return 0;
        }
        return stringIdentifier(prefix, value.name());
    }

    public static int stringIdentifier(String prefix, String value)
    {
        if (prefix == null || value == null)
        {
            return 0;
        }
        return getIdentifier(String.format(STRING_RESOURCE_NAME_FORMAT, prefix, value), "string");
    }

    private static int getIdentifier(String name, String defType)
    {
        try
        {
            return resources.getIdentifier(name.toLowerCase(), defType, context.getPackageName());
        } catch (Throwable e)
        {
            LogUtil.wtf(String.format("can not found resource:[name:%s, defType:%s] identifier.", name, defType));
            return 0;
        }
    }

    public static int getHeightPixels()
    {
        return resources.getDisplayMetrics().heightPixels;
    }

    public static int getWidthPixels()
    {
        return resources.getDisplayMetrics().widthPixels;
    }

    public static float getDensity()
    {
        return resources.getDisplayMetrics().density;
    }

    public static boolean getBoolean(int id)
    {
        return resources.getBoolean(id);
    }

    public static String getString(int id)
    {
        return resources.getString(id);
    }

    public static String getString(int id, Object... formatArgs)
    {
        return resources.getString(id, formatArgs);
    }

    public static int getInteger(int id)
    {
        return resources.getInteger(id);
    }

    public static String[] getStringArray(int id)
    {
        return resources.getStringArray(id);
    }

    public static Drawable getDrawable(int id)
    {
        return resources.getDrawable(id);
    }

    public static int getColor(int colorResId)
    {
        return getColor(colorResId, 0);
    }

    public static int getColor(int colorResId, int defaultColor)
    {
        try
        {
            return resources.getColor(colorResId);
        } catch (Throwable e)
        {
            return defaultColor;
        }
    }

    public static int getDimensionPixelSize(int sizeResId)
    {
        return getDimensionPixelSize(sizeResId, 0);
    }

    public static int getDimensionPixelSize(int sizeResId, int defaultSize)
    {
        try
        {
            return resources.getDimensionPixelSize(sizeResId);
        } catch (Throwable e)
        {
            return defaultSize;
        }
    }
}
