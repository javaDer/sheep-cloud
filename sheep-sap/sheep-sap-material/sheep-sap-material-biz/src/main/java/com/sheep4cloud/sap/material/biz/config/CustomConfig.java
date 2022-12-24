package com.sheep4cloud.sap.material.biz.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class CustomConfig {
    private static final ThreadLocal<ObjectMapper> om = new ThreadLocal<ObjectMapper>() {
        @Override
        protected ObjectMapper initialValue() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            //配置true标识mapper接受数组作为object对象反序列化
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            //反序列化时,遇到未知属性时是否引起结果失败
            objectMapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, false);
            //获取getter方法需setter方法，否则
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            //是否将允许使用非双引号属性名字（这种形式在Javascript中被允许，但是）
            objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            //该特性可以运行接受所有引号引起来的字符，使用‘反斜杠\’机制
            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            //允许出现单引号
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            //允许jacksonUtilsON字符串包含非引号控制字符（值小于32的ASCII字符
            objectMapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
            //排序
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
            objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            //指定默认时区东八区
            //忽略空Bean转jacksonUtilson的错误
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return objectMapper;
        }
    };

    public static ObjectMapper getObjectMapper() {
        return om.get();
    }
}
