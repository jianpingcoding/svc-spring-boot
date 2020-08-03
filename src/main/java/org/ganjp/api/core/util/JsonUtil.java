package org.ganjp.api.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.extern.slf4j.Slf4j;
import org.ganjp.api.core.exception.JsonProcessException;
import org.ganjp.api.core.web.response.error.ErrorData;
import org.ganjp.api.core.web.response.error.ErrorDataBuilder;
import org.ganjp.api.core.web.response.error.ErrorDataBuilderMixin;
import org.ganjp.api.core.web.response.error.ErrorDataMixin;
import org.springframework.boot.jackson.JsonComponentModule;

import java.text.SimpleDateFormat;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper;
    private static ObjectMapper objectMapperIncludeNulls;

    static {
        objectMapper = objectMapper();
        objectMapperIncludeNulls = objectMapperIncludeNulls();
    }

    private static ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        setDefaultObjectMapperProperties(mapper);
        return mapper;
    }

    private static ObjectMapper objectMapperIncludeNulls() {
        ObjectMapper mapper = new ObjectMapper();
        registerModules(mapper);
        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(FAIL_ON_EMPTY_BEANS)
                .enable(ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        return mapper;
    }

    public static String toJson(Object object) {
        return toJson(object, false);
    }

    public static String toJson(Object object, boolean includeEmptyAttributes) {
        if(includeEmptyAttributes) {
            return toJson(object, objectMapperIncludeNulls.writer());
        }
        return toJson(object, objectMapper.writer());
    }

    public static String toJson(Object object, String dateFormat) {
        return toJson(object, dateFormat, false);
    }

    public static String toJson(Object object, String dateFormat, boolean includeEmptyAttributes) {
        if(includeEmptyAttributes) {
            return toJson(objectMapperIncludeNulls.writer(), object, dateFormat);
        }
        return toJson(objectMapper.writer(), object, dateFormat);
    }

    private static String toJson(ObjectWriter writer, Object object, String dateFormat) {
        writer.with(new SimpleDateFormat(dateFormat));
        return toJson(object, writer);
    }

    public static <V> String toJson(Object jsonObject, Class<V> viewClass) {
        ObjectWriter objectWriter = objectMapper.writerWithView(viewClass);
        try {
            return objectWriter.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e);
        }
    }

    private static String toJson(Object object, ObjectWriter writer) {
        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e);
        }
    }

    public static String toPrettyJson(Object object) {
        return toJson(object, objectMapper.writerWithDefaultPrettyPrinter());
    }

    public static void setDefaultObjectMapperProperties(ObjectMapper mapper) {
        registerModules(mapper);
        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(FAIL_ON_EMPTY_BEANS)
                .enable(ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .addMixIn(ErrorData.class, ErrorDataMixin.class)
                .addMixIn(ErrorDataBuilder.class, ErrorDataBuilderMixin.class)
                .setSerializationInclusion(Include.NON_EMPTY)
                .setSerializationInclusion(Include.NON_NULL)
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    }

    private static void registerModules(ObjectMapper objectMapper) {
        objectMapper.registerModules(
                new JavaTimeModule(),
                new Jdk8Module(),
                new JsonComponentModule(),
                new ParameterNamesModule()
        );
    }

    public static ObjectMapper newObjectMapper() {
        return objectMapper();
    }
}
