package com.earth.shiro.config.util;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author helong
 * @version V1.0
 * @Title: BeanCopierUtil
 * @Package com.zy.ars.base.util
 * @company： 卓越教育
 * @Description: 复制转换工具类
 * @date 2019-08-05
 */
public class BeanCopierUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(BeanCopierUtil.class);

    private static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<String, BeanCopier>();

    /**
     * 转换对象集合数据
     *
     * @param srcObjList   数据源集合
     * @param srcObjClass  数据源class
     * @param destObjClass 目标class
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> srcObjList, Class<?> srcObjClass, Class<T> destObjClass) {
        if (CollectionUtils.isEmpty(srcObjList)) {
            return null;
        }
        List<T> destObjList = new ArrayList<>();
        BeanCopier copier = getBeanCopier(srcObjClass, destObjClass, false);
        for (Object srcObj : srcObjList) {
            T destObj = null;
            try {
                destObj = destObjClass.newInstance();
            } catch (InstantiationException e) {
                LOGGER.error("copyList throw InstantiationException", e);
            } catch (IllegalAccessException e) {
                LOGGER.error("copyList throw IllegalAccessException", e);
            }
            copier.copy(srcObj, destObj, null);
            destObjList.add(destObj);

        }
        return destObjList;
    }

    /**
     * 根据数据源和目标对象class获取BeanCopier
     *
     * @param srcObjClass
     * @param destObjClass
     * @param useConverter
     * @return
     */
    private static BeanCopier getBeanCopier(Class<?> srcObjClass, Class<?> destObjClass, boolean useConverter) {
        String key = genKey(srcObjClass, destObjClass, useConverter);
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObjClass, destObjClass, useConverter);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        return copier;
    }

    /**
     * 转换对象数据
     *
     * @param srcObj       数据源对象
     * @param srcObjClass  数据源class
     * @param destObjClass 目标class
     * @param <T>
     * @return
     */
    public static <T> T copy(Object srcObj, Class<?> srcObjClass, Class<T> destObjClass) {
        if (null == srcObj) {
            return null;
        }
        List<Object> list = new ArrayList<>();
        list.add(srcObj);
        List<T> resultList = copyList(list, srcObjClass, destObjClass);
        return CollectionUtils.isNotEmpty(resultList) ? resultList.get(0) : null;
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz, boolean useConverter) {
        return srcClazz.getName() + destClazz.getName() + useConverter;
    }


}
