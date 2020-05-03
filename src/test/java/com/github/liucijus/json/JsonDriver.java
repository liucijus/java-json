package com.github.liucijus.json;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonDriver {
    public static String format(String input) throws Exception {
        return format(input, new NoOpTransformer());
    }

    public static String format(String input, StringPropertyTransformer transformer) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Json.parse(inputStream, outputStream, transformer);

        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }
}
