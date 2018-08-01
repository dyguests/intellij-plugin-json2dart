package com.fanhl.intellij.json2dart.util;

import com.intellij.psi.PsiFile;

public class PsiDartUtils {

    public static final String DART_LANGUAGE_ID = "Dart";

    public static boolean isDartFile(PsiFile file) {
        return file != null && DART_LANGUAGE_ID.equals(file.getLanguage().getID());
    }
}
