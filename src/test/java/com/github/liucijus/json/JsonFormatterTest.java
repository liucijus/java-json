package com.github.liucijus.json;

import org.junit.Test;

import static com.github.liucijus.json.JsonDriver.format;
import static org.junit.Assert.assertEquals;

public class JsonFormatterTest {
    @Test
    public void empty_gets_new_line() throws Exception {
        String empty = "";

        String result = format(empty);

        assertEquals("\n", result);
    }

    @Test
    public void empty_formatted_object_stays_the_same() throws Exception {
        String emptyObject = "{}";

        String result = format(emptyObject);

        assertEquals("{}\n", result);
    }

    @Test
    public void removes_empty_spaces() throws Exception {
        String emptyObject = "  \t { \n\t   } \n\n";

        String result = format(emptyObject);

        assertEquals("{}\n", result);
    }

    @Test
    public void formats_array() throws Exception {
        String emptyObject = "[1,2]";

        String result = format(emptyObject);

        System.out.println(result);
        System.out.println("[\n    1,\n    2\n]");
        assertEquals("[\n    1,\n    2\n]\n", result);
    }

    @Test
    public void formats_object() throws Exception {
        String emptyObject = "{\"a\": 1, \"b\": 2}";

        String result = format(emptyObject);

        String expected = "{\n" +
                "    \"a\": 1,\n" +
                "    \"b\": 2\n" +
                "}\n";
        assertEquals(expected, result);
    }

    @Test
    public void preserves_string_content() throws Exception {
        String emptyObject = "   \"abc def\"  ";

        String result = format(emptyObject);

        String expected = "\"abc def\"\n";
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
                "]\n";
        assertEquals(expected, result);
    }
}
