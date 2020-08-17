package com.earth.shiro.config.util;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by csy
 */
public class BeanUtils {

    public static void copyProperties(Object dest, Object orig) {
        try {
            PropertyUtils.copyProperties(dest, orig);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void springCopyProperties(Object dest, Object orig) {
        try {
            org.springframework.beans.BeanUtils.copyProperties(orig, dest);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static <K, V> Map<K, V> toMap(List<V> list, String keyName) {
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_MAP;
        }

        Map<K, V> map = Maps.newHashMap();
        for (V v : list) {
            try {
                map.put((K) PropertyUtils.getProperty(v, keyName), v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static <K, V> Map<K, V> toMap(List<V> list, Function<V,K> function) {
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_MAP;
        }

        Map<K, V> map = Maps.newHashMap();
        for (V v : list) {
            try {
                map.put(function.apply(v), v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static <T> List<T> removeNullAndEmpty(List<? extends T> oldList) {
        // 临时集合
        if (oldList==null || oldList.isEmpty()){
            return new ArrayList<>();
        }
        List<T> listTemp = new ArrayList(oldList.size());
        for (int i = 0;i < oldList.size(); i++) {
            // 保存不为空的元素
            if (oldList.get(i) != null && !oldList.get(i).equals("")) {
                listTemp.add(oldList.get(i));
            }
        }
        return listTemp;
    }


    public static <V> List<V> toList(List<?> list, String keyName) {
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }

        List<V> newList = new ArrayList<>(list.size());
        for (Object o : list) {
            try {
                Object v = PropertyUtils.getProperty(o, keyName);
                if(v != null ){
                    newList.add((V)v );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newList;
    }

    public static <K, V> Map<K, List<V>> toGroup(List<V> list, String keyName) {
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_MAP;
        }

        Map<K, List<V>> map = Maps.newHashMap();
        for (V v : list) {
            try {
                K k  = (K) PropertyUtils.getProperty(v, keyName);
                List<V> l = map.get(k);
                if(l == null ){
                    l = new ArrayList<>(5);
                }
                l.add(v);
                map.put( k , l );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static <K, V> Map<K, List<V>> toLinkGroup(List<V> list, String keyName) {
        if (list == null || list.size() == 0) {
            return Maps.newLinkedHashMap();
        }

        Map<K, List<V>> map = Maps.newLinkedHashMap();
        for (V v : list) {
            try {
                K k  = (K) PropertyUtils.getProperty(v, keyName);
                List<V> l = map.get(k);
                if(l == null ){
                    l = new ArrayList<>(5);
                }
                l.add(v);
                map.put( k , l );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }


    public static <K, V> Map<K, List<V>> toGroup(List<V> list, Function<V,K> function)  {

        if (list == null || list.size() == 0) {
            return Collections.EMPTY_MAP;
        }
        Map<K, List<V>> map = Maps.newHashMap();
        for (V v : list) {
            try {
                K k  = (K) function.apply(v);
                List<V> l = map.get(k);
                if(l == null ){
                    l = new ArrayList<>(5);
                }
                l.add(v);
                map.put( k , l );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
