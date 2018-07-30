package com.fanhl.intellij.json2dart.ui;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;

import javax.swing.*;
import java.awt.*;

public class JsonDialog extends JFrame {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton button1;

    private final Project project;
    private final PsiFile psiFile;
    private final PsiClass psiClass;


    public JsonDialog(Project project, PsiFile psiFile, PsiClass psiClass) throws HeadlessException {
        this.project = project;
        this.psiFile = psiFile;
        this.psiClass = psiClass;

        setContentPane(panel1);

        setTitle("json2dart");
        this.setAlwaysOnTop(true);
    }
}
