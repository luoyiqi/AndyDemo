package cn.bestkeep.android.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class StringUtil
{
    private static final String MONEY_FORMAT = ",##0.00";

    private static final String DEFAULT_DELIMITER = ";";

    public static String formatMoney(double value)
    {
        DecimalFormat format = new DecimalFormat(MONEY_FORMAT);
        return format.format(value);
    }

    public static boolean isEmpty(String value)
    {
        return value == null || "".equals(value);
    }

    public static boolean isTrimEmpty(String value)
    {
        return isEmpty(trim(value));
    }

    public static String trim(String value)
    {
        return trim(value, "");
    }

    public static String trim(String value, String defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        return value.trim();
    }

    public static String toString(double value)
    {
        return toString(new Double(value));
    }

    public static String toString(Double value)
    {
        if (value == null || value.isInfinite() || value.isNaN())
        {
            return "";
        }
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.toPlainString();
    }

    public static <E> String merge(Collection<E> collection)
    {
        return merge(collection, DEFAULT_DELIMITER);
    }

    public static <E> String merge(E[] array, String delimiter)
    {
        return merge(covert(array), DEFAULT_DELIMITER);
    }

    private static <E> List<E> covert(E[] array)
    {
        List<E> list = new ArrayList<>();
        for (E e : array)
        {
            list.add(e);
        }
        return list;
    }


    public static <E> String merge(Collection<E> collection, String delimiter)
    {
        if (CollectionUtil.isEmpty(collection))
        {
            return "";
        }
        if (delimiter == null)
        {
            delimiter = DEFAULT_DELIMITER;
        }
        StringBuffer sb = new StringBuffer();
        for (E e : collection)
        {
            sb.append(ObjectUtil.toString(e))
                    .append(delimiter);
        }
        if (delimiter.length() > 0)
        {
            return sb.substring(0, sb.length() - delimiter.length());
        }
        return sb.toString();
    }

    public static int length(String value)
    {
        return trim(value).length();
    }


}

