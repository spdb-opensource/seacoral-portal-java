package com.bsg.upm.util;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bsg.upm.annotation.UnderlineToCamel;

public class UnderlineToCamelArgumentResolver implements HandlerMethodArgumentResolver {  
   
    @Override  
    public boolean supportsParameter(MethodParameter methodParameter) {  
        return methodParameter.hasParameterAnnotation(UnderlineToCamel.class);  
    }  
  
    @Override  
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,  
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {  
        return handleParameterNames(parameter, webRequest);  
    }  
  
    private Object handleParameterNames(MethodParameter parameter, NativeWebRequest webRequest) {  
        Object obj = BeanUtils.instantiate(parameter.getParameterType());  
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);  
        Iterator<String> paramNames = webRequest.getParameterNames();  
        Field[] fields = obj.getClass().getDeclaredFields();
        while (paramNames.hasNext()) {  
            String paramName = paramNames.next();  
            Object o = webRequest.getParameter(paramName);  
            paramName = underLineToCamel(paramName);
            for (Field field : fields) {
                if (field.getName().equals(paramName)) {
                    wrapper.setPropertyValue(paramName, o);  
                }
            }
        }  
        return obj;  
    }  
  
    private String underLineToCamel(String source) {  
        Matcher matcher = Pattern.compile("_(\\w)").matcher(source);  
        StringBuffer sb = new StringBuffer();  
        while (matcher.find()) {  
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());  
        }  
        matcher.appendTail(sb);  
        return sb.toString();  
    }

}
