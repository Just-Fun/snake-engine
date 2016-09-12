package com.codenjoy.dojo.snake.client;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Serzh on 9/12/16.
 */
public class Properties {
    private final static String PROPERTIES_FILE = "src/main/resources/config.properties";

    private String email;

    public Properties() {
        loadProperties();
    }

    private void loadProperties() {
        java.util.Properties property = new java.util.Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            property.load(fis);
            email = property.getProperty("email");

        } catch (IOException e) {
            throw new RuntimeException("Properties не подгрузились. " + e.getCause());
        }
    }

    public String getEmail() {
        return email;
    }
}
