package com.github.liucijus.json;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class JsonTest {
    @Test
    public void empty_file_stays_empty() throws Exception {
        String empty = "";

        String result = format(empty);

        assertEquals(empty, result);
    }

    @Test
    public void empty_formatted_object_stays_the_same() throws Exception {
        String emptyObject = "{}";

        String result = format(emptyObject);

        assertEquals("{\n\n}", result);
    }

    @Test
    public void removes_empty_spaces() throws Exception {
        String emptyObject = "  \t { \n\t   } \n\n";

        String result = format(emptyObject);

        assertEquals("{\n\n}", result);
    }

    @Test
    public void formats_array() throws Exception {
        String emptyObject = "[1,2]";

        String result = format(emptyObject);

        System.out.println(result);
        System.out.println("[\n    1,\n    2\n]");
        assertEquals("[\n    1,\n    2\n]", result);
    }

    @Test
    public void formats_object() throws Exception {
        String emptyObject = "{\"a\": 1, \"b\": 2}";

        String result = format(emptyObject);

        String expected = "{\n" +
                "    \"a\": 1,\n" +
                "    \"b\": 2\n" +
                "}";
        assertEquals(expected, result);
    }

    @Test
    public void preserves_string_content() throws Exception {
        String emptyObject = "   \"abc def\"  ";

        String result = format(emptyObject);

        String expected = "\"abc def\"";
        assertEquals(expected, result);
    }

    @Test
    public void formats_nested_object() throws Exception {
        String emptyObject = "[{\"a\": {\"b\": 1, \"c\": \"value\"}, [\"a\", 1, \"b\"]}]";

        String result = format(emptyObject);

        String expected = "[\n" +
                "    {\n" +
                "        \"a\": {\n" +
                "            \"b\": 1,\n" +
                "            \"c\": \"value\"\n" +
                "        },\n" +
                "        [\n" +
                "            \"a\",\n" +
                "            1,\n" +
                "            \"b\"\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        assertEquals(expected, result);
    }

    private String format(String input) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Json.parse(inputStream, outputStream);

        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

}
