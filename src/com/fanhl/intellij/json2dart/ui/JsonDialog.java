package com.fanhl.intellij.json2dart.ui;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;

import javax.swing.*;

public class JsonDialog extends JFrame {

    private final Project project;
    private final PsiFile psiFile;
    private final PsiClass psiClass;

    public JsonDialog(Project project, PsiFile psiFile, PsiClass psiClass) {
        this.project = project;
        this.psiFile = psiFile;
        this.psiClass = psiClass;


    }
}
