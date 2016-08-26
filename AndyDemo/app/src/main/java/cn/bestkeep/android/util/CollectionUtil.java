package cn.bestkeep.android.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CollectionUtil
{

    public static <E> List<E> list(Collection<E> collection)
    {
        List<E> list = new ArrayList<>();
        list.addAll(collection);
        return list;
    }

    public static <E> boolean isEmpty(Collection<E> collection)
    {
        return collection == null || collection.isEmpty();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map)
    {
        return map == null || map.isEmpty();
    }

    public static <K, V> Map<K, V> safe(Map<K, V> map)
    {
        return map == null ? new HashMap<K, V>() : map;
    }

    public static <E> List<E> safe(List<E> list)
    {
        return list == null ? new ArrayList<E>() : list;
    }
}
