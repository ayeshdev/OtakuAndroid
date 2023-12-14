package com.otaku.otaku.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(Context context){
        properties = new Properties();

        try {
            InputStream inputStream = context.getResources().getAssets().open("config.properties");
            properties.load(inputStream); // Load properties from the InputStream
            inputStream.close(); // Close the InputStream after loading

            // Log all properties to verify if they are loaded properly
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                Log.e("ConfigReader", key + " : " + value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
