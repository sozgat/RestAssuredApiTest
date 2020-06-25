package com.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static PropertyUtils propUtils = new PropertyUtils();
    private static Properties props = new Properties();

    static{
        try {
            props.load(PropertyUtils.class.getClassLoader().getResourceAsStream("test1.properties"));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /*public PropertyUtils() {
        loadProperties();
    }*/

    public static PropertyUtils getInstance(){


        return propUtils;
    }

    public String getPropValue(String val){
        return props.getProperty(val);
    }




}
