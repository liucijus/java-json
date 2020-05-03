package com.github.liucijus.json;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class Json {
    public static void main(String[] args) throws Exception {
        parse(System.in, System.out);
    }

    public static void parse(InputStream inputStream, OutputStream outputStream) throws Exception {
        parse(inputStream, outputStream, new NoOpTransformer());
    }

    public static void parse(InputStream inputStream, OutputStream outputStream, StringPropertyTransformer transformer)
            throws IOException, IllegalAccessException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Writer writer = new OutputStreamWriter(outputStream);

        int c;
        int currentLevel = 0;
        boolean addIndent = false;

        while ((c = skipWhitespace(reader)) != -1) {
            // fixme: move to move declarative state machine processing; smaller methods
            if (isString(c)) {
                if (addIndent) {
                    writer.write('\n');
                    writer.write(indentFor(currentLevel));
                    addIndent = false;
                }
                String value = readStringValue(reader);

                if (transformer.appliesTo(value)) {
                    transform(transformer, reader, writer, currentLevel, value);
                } else {
                    writer.write(asJsonString(value));
                }

            } else if (isComma(c)) {
                writer.write(c);
                writer.write('\n');
                writer.write(indentFor(currentLevel));
            } else if (isCollon(c)) {
                writer.write(c);
                writer.write(' ');
            } else if (isClosing(c)) {
                currentLevel--;
                if (!addIndent) {
                    writer.write('\n');
                    writer.write(indentFor(currentLevel));
                }
                writer.write(c);
                addIndent = false;
            } else if (isOpening(c)) {
                if (addIndent) {
                    writer.write('\n');
                    writer.write(indentFor(currentLevel));
                }
                writer.write(c);
                currentLevel++;
                addIndent = true;
            } else {
                if (addIndent) {
                    writer.write('\n');
                    writer.write(indentFor(currentLevel));
                    addIndent = false;
                }

                writer.write(c);
            }
        }
        writer.write('\n');
        writer.flush();
    }

    private static void transform(
            StringPropertyTransformer transformer,
            BufferedReader reader,
            Writer writer,
            int currentLevel,
            String value)
            throws IOException, IllegalAccessException {
        // fixme: currently only happy path implementation
        int c = skipWhitespace(reader);
        reader.mark(1000);

        if (isCollon(c)) {
            c = skipWhitespace(reader);
            if (isString(c)) {
                String propertyValue = readStringValue(reader);

                Map<String, String> modifiedProperties = transformer.transform(value, propertyValue);
                if (modifiedProperties.isEmpty()) {
                    trySkipComma(reader);
                } else {
                    String update = modifiedProperties.entrySet().stream()
                            .map(entry -> asJsonString(entry.getKey()) + ": " + asJsonString(entry.getValue()))
                            .collect(joining(",\n" + String.valueOf(indentFor(currentLevel))));
                    writer.write(update);
                }
            } else {
                throw new IllegalAccessException();
            }
        } else {
            reader.reset();
        }
    }

    private static void trySkipComma(BufferedReader reader) throws IOException {
        reader.mark(1000);
        int possiblyComma = skipWhitespace(reader);
        if (possiblyComma != ',')
            reader.reset();
    }

    private static String asJsonString(String value) {
        return "\"" + value + "\"";
    }

    private static String readStringValue(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        char c;
        while ((c = (char) reader.read()) != '"') {
            builder.append(c);
        }
        return builder.toString();
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

    private static int skipWhitespace(Reader reader) throws IOException {
        int c;
        while ((c = reader.read()) != -1) {
            if (!Character.isWhitespace(c))
                return (char) c;
        }
        return -1;
    }
}
