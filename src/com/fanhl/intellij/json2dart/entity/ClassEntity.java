package com.fanhl.intellij.json2dart.entity;

import java.util.ArrayList;
import java.util.List;

public class ClassEntity {
    public String name;
    public List<FieldEntity> fields = new ArrayList<>();

    public void addField(FieldEntity fieldEntity) {
        fields.add(fieldEntity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("class {");
        fields.forEach(fieldEntity -> {
            sb.append("    ").append(fieldEntity.type).append(" ").append(fieldEntity.name).append(";").append("\n");
        });
        sb.append("}");

        return sb.toString();
    }
}
