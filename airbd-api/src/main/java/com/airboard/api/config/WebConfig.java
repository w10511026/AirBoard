package com.airboard.api.config;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description WEB mvc 通用配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer, JsonSerializer<Json> {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserResolver());
    }

    @Bean
    public CurrentUserResolver currentUserResolver() {
        return new CurrentUserResolver();
    }

    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(gsonHttpMessageConverter());
    }

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        gsonHttpMessageConverter.setGson(gson);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        gsonHttpMessageConverter.setSupportedMediaTypes(list);
        return gsonHttpMessageConverter;
    }

    @Override
    public JsonElement serialize(Json json, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonParser parser = new JsonParser();
        return parser.parse(json.value());
    }
}