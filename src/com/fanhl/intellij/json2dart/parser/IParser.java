package com.fanhl.intellij.json2dart.parser;

import com.fanhl.intellij.json2dart.entity.ClassEntity;

public interface IParser {
    ClassEntity generate(String classNameStr, String jsonStr);

    enum Type {
        TEST
    }
}
