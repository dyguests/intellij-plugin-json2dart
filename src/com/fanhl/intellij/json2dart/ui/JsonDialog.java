package com.fanhl.intellij.json2dart.ui;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.*;

public class JsonDialog extends JFrame {
    private JPanel panel1;
    private JTextField generateTextField;
    private JTextArea jsonJTA;
    private JButton generateJB;

    private final Project project;
    private final PsiFile psiFile;
    private final PsiClass psiClass;


    public JsonDialog(Project project, PsiFile psiFile, PsiClass psiClass) throws HeadlessException {
        this.project = project;
        this.psiFile = psiFile;
        this.psiClass = psiClass;

        setContentPane(panel1);

        setTitle("json2dart");
        getRootPane().setDefaultButton(generateJB);
        this.setAlwaysOnTop(true);

        initListener();
    }

    private void initListener() {
        generateJB.addActionListener(e -> {
            onGenerate();
        });
    }

    /**
     * 生成 dart 代码
     */
    private void onGenerate() {
        setAlwaysOnTop(false);

        String classNameStr = generateTextField.getText().trim();
        if (TextUtils.isBlank(classNameStr)) {
            return;
        }

        String jsonStr = jsonJTA.getText().trim();
        if (TextUtils.isBlank(jsonStr)) {
            return;
        }


        PsiElementFactory factory = JavaPsiFacade.getElementFactory(project);
        PsiMethod psiMethod = factory.createMethodFromText("void a(){}", psiClass);
        psiFile.add(psiMethod);
    }
}
