package com.android.architecture.practice.data.db.converter;

import android.text.TextUtils;

import com.android.architecture.practice.data.log.DataLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liangbx.android.common.util.ObjectMapperUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {

    public static String getDBValue(Object model) {
        if (model == null) {
            return null;
        }
        try {
            return ObjectMapperUtils.getMapperInstance().writeValueAsString(model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getModelValue(String data, Class<T> clazz) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, clazz);
        } catch (IOException e) {
            DataLogger.get().e(e);
        }
        return null;
    }

    public static <T> List<T> getModelListValue(String data, Class<T> clazz) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        JavaType type = ObjectMapperUtils.constructParametricType(ArrayList.class, clazz);
        try {
            return ObjectMapperUtils.getMapperInstance().readValue(data, type);
        } catch (IOException e) {
            DataLogger.get().e(e);
            return null;
        }
    }

}
