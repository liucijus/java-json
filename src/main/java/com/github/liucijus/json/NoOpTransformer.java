package com.github.liucijus.json;

import java.util.Map;

public class NoOpTransformer implements StringPropertyTransformer {
    @Override
    public Map<String, String> transform(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean appliesTo(String key) {
        return false;
    }
}
