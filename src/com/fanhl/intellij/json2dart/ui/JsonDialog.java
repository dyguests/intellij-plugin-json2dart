package com.fanhl.intellij.json2dart.ui;

import com.fanhl.intellij.json2dart.parser.IParser;
import com.fanhl.intellij.json2dart.processer.ConvertBridge;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
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

    private final ConvertBridge convertBridge;

    public JsonDialog(Project project, PsiFile psiFile, PsiClass psiClass) throws HeadlessException {
        this.project = project;
        this.psiFile = psiFile;
        this.psiClass = psiClass;

        assignViews();

        convertBridge = new ConvertBridge(project, psiFile, psiClass);
    }

    private void assignViews() {
        setContentPane(panel1);

        setTitle("json2dart");
        getRootPane().setDefaultButton(generateJB);
        this.setAlwaysOnTop(true);

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

        IParser.Type type = IParser.Type.TEST;
        convertBridge.convert(classNameStr, jsonStr, type);
    }
}
