package com.otaku.otaku.converter;
import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static String fromList(List<String> sizes) {
        StringBuilder sb = new StringBuilder();
        for (String size : sizes) {
            sb.append(size).append(",");
        }
        return sb.toString();
    }

    @TypeConverter
    public static List<String> toList(String sizesString) {
        return Arrays.asList(sizesString.split(","));
    }
}

