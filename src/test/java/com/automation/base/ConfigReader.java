package com.automation.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();
    private static boolean initialized = false;

    private ConfigReader() {}

    public static synchronized void init() {
        if (initialized) {
            return;
        }
        String configPath = "src/test/resources/config/config.properties";
        try (InputStream input = new FileInputStream(configPath)) {
            properties.load(input);
            initialized = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties from " + configPath, e);
        }
    }

    public static String get(String key) {
        if (!initialized) {
            init();
        }
        return properties.getProperty(key);
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Integer.parseInt(value.trim());
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value.trim());
    }
}



