package com.fanhl.intellij.json2dart;

import com.fanhl.intellij.json2dart.ui.JsonDialog;
import com.fanhl.intellij.json2dart.util.PsiDartUtils;
import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import org.jetbrains.annotations.NotNull;

/**
 * json 2 dart
 *
 * @author fanhl
 */
public class MainAction extends BaseGenerateAction {
    @SuppressWarnings("unused")
    public MainAction() {
        super(null);
    }

    @SuppressWarnings("unused")
    public MainAction(CodeInsightActionHandler handler) {
        super(handler);
    }

    @Override
    protected boolean isValidForClass(PsiClass targetClass) {
        return super.isValidForClass(targetClass);
    }

    @Override
    protected boolean isValidForFile(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        if (project == null) {
            return false;
        }

        if (editor == null) {
            return false;
        }

        if (file == null) {
            return false;
        }

        if (!PsiDartUtils.isDartFile(file)) {
            return false;
        } else if (file instanceof PsiCompiledElement) {
            return false;
        } else {
//            PsiClass targetClass = this.getTargetClass(editor, file);
//            return targetClass != null && this.isValidForClass(targetClass);

            return true;
        }
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        if (project == null) {
            return;
        }

        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            return;
        }

        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (psiFile == null) {
            return;
        }


        PsiClass psiClass = getTargetClass(editor, psiFile);

        JsonDialog dialog = new JsonDialog(project, psiFile, psiClass);
//        dialog.setProject(project);
//        dialog.setFile(psiFile);
//        dialog.setClass(psiClass);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
