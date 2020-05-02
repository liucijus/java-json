package com.github.liucijus.json;

import java.io.*;
import java.util.Arrays;

public class Json {
    public static void main(String[] args) throws IOException {
        parse(System.in, System.out);
    }

    public static void parse(InputStream inputStream, OutputStream outputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Writer writer = new OutputStreamWriter(outputStream);

        int c = -1;
        int currentLevel = 0;
        boolean stringValue = false;
        boolean addIndent = false;
        while ((c = reader.read()) != -1) {
            if (stringValue) {
                if (isString(c)) {
                    stringValue = false;
                }
                writer.write(c);

            } else
            if (isComma(c)) {
                writer.write(c);
                writer.write('\n');
                writer.write(indentFor(currentLevel));
            } else if (isCollon(c)) {
                writer.write(c);
                writer.write(' ');
            } else if (isClosing(c)) {
                currentLevel--;
                writer.write('\n');
                writer.write(indentFor(currentLevel));
                writer.write(c);
                addIndent = false;
            } else if (isOpening(c)) {
                if (addIndent) {
                    writer.write(indentFor(currentLevel));
                }
                writer.write(c);
                writer.write('\n');
                currentLevel++;
                addIndent = true;
            } else if (!Character.isWhitespace(c)) {
                if (addIndent) {
                    writer.write(indentFor(currentLevel));
                    addIndent = false;
                }

                if (isString(c))
                    stringValue = true;
                writer.write(c);
            }
        }

        writer.flush();
    }

    private static boolean isCollon(int c) {
        return ':' == c;
    }

    private static char[] indentFor(int currentLevel) {
        char[] indentation = new char[currentLevel * 4];
        Arrays.fill(indentation, ' ');
        return indentation;
    }

    private static boolean isComma(int c) {
        return ',' == c;
    }

    private static boolean isClosing(int c) {
        return c == ']' || c == '}';
    }

    private static boolean isOpening(int c) {
        return c == '[' || c == '{';
    }

    private static boolean isString(int c) {
        return c == '"';
    }
}
