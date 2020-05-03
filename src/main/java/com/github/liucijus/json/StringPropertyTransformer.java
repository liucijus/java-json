package com.github.liucijus.json;

import java.util.Map;

public interface StringPropertyTransformer {
    Map<String, String> transform(String key, String value);
    boolean appliesTo(String key);
}
