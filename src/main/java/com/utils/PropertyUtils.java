package com.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {


    public static Properties getInstance(String fileName){
        Properties props = new Properties();
        try {
            props.load(PropertyUtils.class.getClassLoader().getResourceAsStream(fileName+".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

}
