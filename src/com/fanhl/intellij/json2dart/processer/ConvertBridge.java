package com.fanhl.intellij.json2dart.processer;

import com.fanhl.intellij.json2dart.entity.ClassEntity;
import com.fanhl.intellij.json2dart.parser.IParser;
import com.fanhl.intellij.json2dart.parser.ParserFactory;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

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
     * @param type         转换器类型
     */
    public void convert(@Nullable String classNameStr, @Nullable String jsonStr, @Nullable IParser.Type type) {

        IParser parser = ParserFactory.buildParser(type);

        ClassEntity commentStr = parser.generate(classNameStr, jsonStr);

        if (commentStr == null) {
            return;
        }

        WriteCommandAction.runWriteCommandAction(project, () -> {
            PsiElementFactory factory = PsiElementFactory.SERVICE.getInstance(project);
            PsiComment comment = factory.createCommentFromText(commentStr.toString(), psiFile);
            psiFile.addAfter(comment, psiFile.getLastChild());
        });
    }
}
