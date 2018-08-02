package com.fanhl.intellij.json2dart.parser;

import com.fanhl.intellij.json2dart.entity.ClassEntity;
import com.fanhl.intellij.json2dart.entity.FieldEntity;
import com.fanhl.intellij.json2dart.entity.FieldType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.Iterator;
import java.util.PrimitiveIterator;

public class DefaultParser implements IParser {

    /**
     * 这里要生成代码
     * <p>
     * **目前先生成comment
     *
     * @param classNameStr
     * @param jsonStr
     * @return
     */
    @Override
    public ClassEntity generate(String classNameStr, String jsonStr) {
        if (jsonStr.startsWith("{")) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return generateClassEntity(classNameStr, jsonObject);
        } else if (jsonStr.startsWith("[")) {
            JSONArray jsonArray;
            try {
                jsonArray = new JSONArray(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return generateClassEntity(classNameStr, jsonArray);
        } else {
            return null;
        }

    }

    private ClassEntity generateClassEntity(String classNameStr, JSONObject jsonObject) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.name = getFormatClassName(classNameStr);

        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            Object next = keys.next();
            if (!(next instanceof String)) {
                continue;
            }
            String key = (String) next;

            FieldEntity fieldEntity = new FieldEntity();
            fieldEntity.name = key;
            try {
                Object value = jsonObject.get(key);
                fieldEntity.valueSample = value;
                fieldEntity.type = FieldType.findType(value);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            classEntity.addField(fieldEntity);
        }

        return classEntity;
    }

    private ClassEntity generateClassEntity(String classNameStr, JSONArray jsonArray) {
        // FIXME: 2018/8/2 之后写

        return null;
    }

    private String getFormatClassName(String name) {
        // FIXME: 2018/8/2 这里加上驼峰命名
        PrimitiveIterator.OfInt iterator = name.chars().iterator();
        while (iterator.hasNext()) {
            int next = iterator.nextInt();
        }
        return name;
    }
}
