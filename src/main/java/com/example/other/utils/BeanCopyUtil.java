package com.example.other.utils;

import com.example.other.StringDemo;
import com.example.other.StringDemo1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huangqi
 * @Package com.example.other.utils
 * @Description: 对象转换 采用cglib
 * @date 2019-11-01 11:37
 */
@Slf4j
public class BeanCopyUtil {

    private static ThreadLocal<String[]> ignoresThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String[]> optionalThreadLocal = new ThreadLocal<>();

    /**
     * 复制源对象属性值
     *
     * @param src
     * @param tar
     * @param <T>
     * @return
     */
    public static <T> T copy(Object src, Class<T> tar) {
        try {
            if (src != null) {
                T t = tar.newInstance();
                BeanCopier beanCopier = BeanCopier.create(src.getClass(), tar, false);
                beanCopier.copy(src, t, null);
                return t;
            }
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 复制源对象属性值 可选忽略的属性
     *
     * @param src
     * @param tar
     * @param ignores
     * @param <T>
     * @return
     */
    public static <T> T copyIgnore(Object src, Class<T> tar, String... ignores) {
        if (src != null) {
            ignoresThreadLocal.set(ignores);
            T copy = copy(src, tar, new IgnoreFieldCovert());
            return copy;
        }
        return null;
    }

    /**
     * 复制源对象属性值 可选只选的属性
     *
     * @param src
     * @param tar
     * @param options
     * @param <T>
     * @return
     */
    public static <T> T copyOptional(Object src, Class<T> tar, String... options) {
        if (src != null) {
            optionalThreadLocal.set(options);
            T copy = copy(src, tar, new OptionalFieldCovert());
            return copy;
        }
        return null;
    }

    /**
     * 复制源对象属性值 自定义转换规则
     *
     * @param src
     * @param tar
     * @param converter
     * @param <T>
     * @return
     */
    public static <T> T copy(Object src, Class<T> tar, Converter converter) {
        try {
            if (src != null) {
                T t = tar.newInstance();
                BeanCopier beanCopier = BeanCopier.create(src.getClass(), tar, true);
                beanCopier.copy(src, t, converter);
                return t;
            }
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 复制源列表对象属性值
     *
     * @param src
     * @param tar
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> src, Class<T> tar) {
        List<T> vList = new ArrayList<>();

        if (src != null && src.size() > 0) {
            src.forEach(s -> {
                T covert = copy(s, tar);
                vList.add(covert);
            });
            return vList;
        }

        return null;
    }

    /**
     * 复制源列表对象属性值 可选只选的属性
     *
     * @param src
     * @param tar
     * @param ignores
     * @param <T>
     * @return
     */
    public static <T> List<T> copyListIgnore(List<?> src, Class<T> tar, String... ignores) {
        List<T> vList = new ArrayList<>();

        if (src != null && src.size() > 0) {
            src.forEach(s -> {
                T covert = copyIgnore(s, tar, ignores);
                vList.add(covert);
            });
            return vList;
        }

        return null;
    }

    /**
     * 复制源列表对象属性值 可选只选的属性
     *
     * @param src
     * @param tar
     * @param options
     * @param <T>
     * @return
     */
    public static <T> List<T> copyListOptional(List<?> src, Class<T> tar, String... options) {
        List<T> vList = new ArrayList<>();

        if (src != null && src.size() > 0) {
            src.forEach(s -> {
                T covert = copyOptional(s, tar, options);
                vList.add(covert);
            });
            return vList;
        }

        return null;
    }

    /**
     * 复制源列表对象属性值 自定义转换规则
     *
     * @param src
     * @param tar
     * @param converter
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> src, Class<T> tar, Converter converter) {
        List<T> vList = new ArrayList<>();

        if (src != null && src.size() > 0) {
            src.forEach(s -> {
                T covert = copy(s, tar, converter);
                vList.add(covert);
            });
            return vList;
        }

        return null;
    }


    /**
     * 复制对象属性到map中
     * <p>
     * 注意：返回的Map为BeanMap
     *
     * @param src
     * @return
     */
    public static Map<String, Object> copyToMap(Object src) {
        if (src != null) {
            BeanMap beanMap = BeanMap.create(src);
            return beanMap;
        }
        return null;
    }


    /**
     * Date 转成 LocalDateTime
     */
    public static class ToLocalDateTimeCovert implements Converter {
        /**
         * @param src        source对象属性值
         * @param tarClass   target对象对应类
         * @param methodName targetClass里属性对应set方法名,eg.setId
         * @return
         */

        @Override
        public Object convert(Object src, Class tarClass, Object methodName) {
            if (tarClass.equals(LocalDateTime.class)) {
                return LocalDateTime.ofInstant(((Date) src).toInstant(), ZoneId.systemDefault());
            }
            return src;
        }
    }

    /**
     * 忽略字段
     */
    public static class IgnoreFieldCovert implements Converter {
        /**
         * @param src        source对象属性值
         * @param tarClass   target对象对应类
         * @param methodName targetClass里属性对应set方法名,eg.setId
         * @return
         */

        @Override
        public Object convert(Object src, Class tarClass, Object methodName) {
            String[] ignores = ignoresThreadLocal.get();
            if (ignores == null) {
                return src;
            }
            for (String field : ignores) {
                if (((String) methodName).replace("set", "").equalsIgnoreCase(field)) {
                    return null;
                }
            }
            return src;
        }
    }

    /**
     * 只选字段
     */
    public static class OptionalFieldCovert implements Converter {
        /**
         * @param src        source对象属性值
         * @param tarClass   target对象对应类
         * @param methodName targetClass里属性对应set方法名,eg.setId
         * @return
         */

        @Override
        public Object convert(Object src, Class tarClass, Object methodName) {
            String[] ignores = optionalThreadLocal.get();
            if (ignores == null) {
                return src;
            }
            for (String field : ignores) {
                if (((String) methodName).replace("set", "").equalsIgnoreCase(field)) {
                    return src;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        StringDemo demo = new StringDemo();
        demo.setName("123");
        demo.setTime(new Date());

        List list = new ArrayList();
        list.add("123");
        demo.setList(list);

        List list2 = new ArrayList();
        list2.add(demo);
        StringDemo1 demo1 = BeanCopyUtil.copyIgnore(demo, StringDemo1.class, "name");
        System.out.println(demo1.toString());

        StringDemo1 demo2 = BeanCopyUtil.copyOptional(demo, StringDemo1.class, "name");
        System.out.println(demo2.toString());

        Map demo3 = BeanCopyUtil.copyToMap(demo);
        System.out.println(demo3.toString());


    }

}
