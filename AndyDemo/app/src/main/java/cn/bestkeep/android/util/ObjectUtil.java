package cn.bestkeep.android.util;


public abstract class ObjectUtil
{
    public static int hashCode(Object o, int defaultHashCode)
    {
        return o == null ? defaultHashCode : o.hashCode();
    }

    public static <E> boolean equals(E o1, E o2)
    {
        if (o1 == o2)
        {
            return true;
        }
        if (o1 == null || o2 == null)
        {
            return false;
        }
        return o1.equals(o2);
    }

    public static String toString(Object object)
    {
        return object == null ? "" : object.toString();
    }
}
