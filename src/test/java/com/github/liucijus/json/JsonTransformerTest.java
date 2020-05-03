package com.github.liucijus.json;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JsonTransformerTest {
    @Test
    public void removes_property() throws Exception {
        String input = "{\"a\": \"val1\", \"b\": \"val2\"}";

        String result = JsonDriver.format(input, new RemovingTransformer());

        String expected = "{\n" +
                "    \"b\": \"val2\"\n" +
                "}\n";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void adds_property() throws Exception {
        String input = "{\"a\": \"val1\"}";

        String result = JsonDriver.format(input, new AddingTransformer());

        String expected = "{\n" +
                "    \"a\": \"val1\",\n" +
                "    \"b\": \"val2\"\n" +
                "}\n";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void modifies_property() throws Exception {
        String input = "{\"a\": \"val1\"}";

        String result = JsonDriver.format(input, new ModifeingTransformer());

        String expected = "{\n" +
                "    \"a\": \"new val\"\n" +
                "}\n";

        Assert.assertEquals(expected, result);
    }

    private static class AddingTransformer implements StringPropertyTransformer {

        @Override
        public Map<String, String> transform(String key, String value) {
            Map<String, String> properties = new HashMap<>();
            properties.put(key, value);
            properties.put("b", "val2");
            return properties;
        }

        @Override
        public boolean appliesTo(String key) {
            return key.equals("a");
        }
    }

    private static class RemovingTransformer implements StringPropertyTransformer {

        @Override
        public Map<String, String> transform(String key, String value) {
            return Collections.emptyMap();
        }

        @Override
        public boolean appliesTo(String key) {
            return key.equals("a");
        }
    }

    private static class ModifeingTransformer implements StringPropertyTransformer {
        @Override
        public Map<String, String> transform(String key, String value) {
            return Collections.singletonMap("a", "new val");
        }

        @Override
        public boolean appliesTo(String key) {
            return key.equals("a");
        }
    }
}
