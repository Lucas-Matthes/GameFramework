package com.example.eventdrivenengine.userinputs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserInput {
    private final Map<UserInputKey, Object> values;

    public UserInput(Map<UserInputKey, Object> values) {
        this.values = Collections.unmodifiableMap(new HashMap<>(values));
    }

    public boolean contains(UserInputKey key) {
        return values.containsKey(key);
    }

    public <T> T get(UserInputKey key, Class<T> type) {
        Object value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(
                    "No value found for key: " + key);
        }
        if (!type.isInstance(value)) {
            throw new IllegalArgumentException(
                    "Value for key '" + key +
                            "' is not of type " + type.getSimpleName());
        }
        return type.cast(value);
    }

    public Map<UserInputKey, Object> getValues() {
        return values;
    }
}
