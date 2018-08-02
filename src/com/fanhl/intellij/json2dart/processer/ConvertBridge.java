package com.fanhl.intellij.json2dart.processer;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiFile;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ConvertBridge {

    private final Project project;
    private final PsiFile psiFile;
    private final PsiClass psiClass;

    public ConvertBridge(Project project, PsiFile psiFile, PsiClass psiClass) {
        this.project = project;
        this.psiFile = psiFile;
        this.psiClass = psiClass;
    }

    /**
     * @param classNameStr 类名
     * @param jsonStr      json字符串
     */
    public void convert(String classNameStr, String jsonStr) {
        String commentStr = generateComment(classNameStr, jsonStr);

        if (commentStr == null) {
            return;
        }

        WriteCommandAction.runWriteCommandAction(project, () -> {
            PsiElementFactory factory = PsiElementFactory.SERVICE.getInstance(project);
            PsiComment comment = factory.createCommentFromText(commentStr, psiFile);
            psiFile.addBefore(comment, psiFile.getFirstChild());
        });
    }

    /**
     * 这里要生成代码
     * <p>
     * **目前先生成comment
     *
     * @param classNameStr
     * @param jsonStr
     * @return
     */
    private String generateComment(String classNameStr, String jsonStr) {
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

    private String generateClassEntity(String classNameStr, JSONObject jsonObject) {

        StringBuilder sb = new StringBuilder();
        sb.append("/*");

        sb.append("class ");
        sb.append(classNameStr);
        sb.append(" {\n");
        sb.append(jsonObject.toString());
        sb.append("}\n");

        sb.append("*/\n");

        return sb.toString();
    }

    private String generateClassEntity(String classNameStr, JSONArray jsonArray) {
        return null;
    }
}
