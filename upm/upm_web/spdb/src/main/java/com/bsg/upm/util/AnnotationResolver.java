package com.bsg.upm.util;

import java.lang.reflect.Field;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 该类的作用可以把方法上的参数绑定到注解的变量中,注解的语法#{变量名}
 * 能解析类似#{task}或者#{task.taskName}或者{task.project.projectName}
 */
public class AnnotationResolver {

    private static AnnotationResolver resolver;

    public static AnnotationResolver newInstance() {

        if (resolver == null) {
            return resolver = new AnnotationResolver();
        } else {
            return resolver;
        }

    }

    /**
     * 解析注解上的值
     * 
     * @param pjp
     * @param str
     *            需要解析的字符串
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public Object resolver(ProceedingJoinPoint pjp, String str)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        if (str == null) {
            return null;
        }
        if (str.matches("#\\{\\D*\\}")) {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            String[] names = methodSignature.getParameterNames();
            Object[] args = pjp.getArgs();
            str = str.replaceAll("#\\{", "").replaceAll("\\}", "");
            if (str.contains(".")) {
                String[] strs = str.split("\\.");
                for (int i = 0; i < names.length; i++) {
                    if (strs[0].equals(names[i])) {
                        Object value = args[i];
                        for (int j = 1; j < strs.length; j++) {
                            value = getValue(value, strs[j]);
                        }
                        return value;
                    }
                }
            } else {
                for (int i = 0; i < names.length; i++) {
                    if (str.equals(names[i])) {
                        return args[i];
                    }
                }
            }
        }
        return str;
    }

    public static Object getValue(Object o, String fieldName)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        Field field = o.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(o);
    }
    
    

}
