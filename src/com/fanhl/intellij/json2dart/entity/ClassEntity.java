package com.fanhl.intellij.json2dart.entity;

import com.fanhl.intellij.json2dart.util.PsiDartUtils;

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

//        sb.append("/*\n");
        sb.append("class {\n");
        fields.forEach(fieldEntity -> {
            sb.append(PsiDartUtils.createIndentation()).append(fieldEntity.type).append(" ").append(fieldEntity.name).append(";").append("\n");
        });
        sb.append("}\n");
//        sb.append("*/\n");

        return sb.toString();
    }

}
