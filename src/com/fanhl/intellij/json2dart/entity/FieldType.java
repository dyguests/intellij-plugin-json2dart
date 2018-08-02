package com.fanhl.intellij.json2dart.entity;

public enum FieldType {
    /**
     * 假类型
     */
    DUMMY;

    public static FieldType findType(Object value) {
        // FIXME: 2018/8/2 之后加上类型
        return DUMMY;
    }
}
