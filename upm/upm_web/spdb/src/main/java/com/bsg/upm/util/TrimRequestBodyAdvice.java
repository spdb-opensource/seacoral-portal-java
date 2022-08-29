package com.bsg.upm.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@ControllerAdvice
public class TrimRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        InputStream body = inputMessage.getBody();
        String jsonStr = IOUtils.toString(body, "UTF-8");
        if (jsonStr.startsWith("[")) {
            JSONArray jsonArr = JSONArray.parseArray(jsonStr);
            trimJsonObj(jsonArr);
            return new MappingJacksonInputMessage(IOUtils.toInputStream(jsonArr.toJSONString(), "UTF-8"),
                    inputMessage.getHeaders());
        } else {
            JSONObject jsonObj = JSONObject.parseObject(jsonStr);
            trimJsonObj(jsonObj);
            return new MappingJacksonInputMessage(IOUtils.toInputStream(jsonObj.toJSONString(), "UTF-8"),
                    inputMessage.getHeaders());
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    private void trimJsonObj(Object jsonObj) {
        if (jsonObj instanceof JSONArray) {
            JSONArray objArray = (JSONArray) jsonObj;
            for (int i = 0; i < objArray.size(); i++) {
                trimJsonObj(objArray.get(i));
            }
        } else if (jsonObj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) jsonObj;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                Object object = entry.getValue();
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    trimJsonObj(objArray);
                } else if (object instanceof JSONObject) {
                    trimJsonObj((JSONObject) object);
                } else if (object instanceof String) {
                    jsonObject.put(entry.getKey(), StringUtils.trim((String) object));
                }
            }
        }
    }
}
