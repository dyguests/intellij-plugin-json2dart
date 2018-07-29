package com.fanhl.intellij.json2dart.ui;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;

import javax.swing.*;
import java.awt.*;

/**
 * @author fanhl
 */
public class JsonDialog extends JFrame {

    private final Project project;
    private final PsiFile psiFile;
    private final PsiClass psiClass;


    public JsonDialog(Project project, PsiFile psiFile, PsiClass psiClass) throws HeadlessException {
        this.project = project;
        this.psiFile = psiFile;
        this.psiClass = psiClass;

        setTitle("json2dart");
        this.setAlwaysOnTop(true);
    }
}
