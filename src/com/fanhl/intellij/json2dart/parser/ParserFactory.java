package com.fanhl.intellij.json2dart.parser;

public class ParserFactory {
    public static IParser buildParser(IParser.Type type) {
        return new DefaultParser();
    }

}
