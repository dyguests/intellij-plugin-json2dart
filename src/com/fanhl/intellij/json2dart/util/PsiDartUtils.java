package com.fanhl.intellij.json2dart.util;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class PsiDartUtils {
    /**
     * 语言id
     */
    public static final String DART_LANGUAGE_ID = "Dart";
    /**
     * 缩进
     */
    public static final String INDENTATION = "  ";

    public static boolean isDartFile(PsiFile file) {
        return file != null && DART_LANGUAGE_ID.equals(file.getLanguage().getID());
    }

    @NotNull
    public static String createIndentation() {
        return INDENTATION;
    }
}
