package com.fanhl.intellij.json2dart.processer;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiFile;

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

        WriteCommandAction.runWriteCommandAction(project, () -> {
            PsiElementFactory factory = PsiElementFactory.SERVICE.getInstance(project);
            PsiComment comment = factory.createCommentFromText("// test", psiFile);
            psiFile.addBefore(comment, psiFile.getFirstChild());
        });


    }
}
