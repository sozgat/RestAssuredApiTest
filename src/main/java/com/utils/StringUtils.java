package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static String[] getFilelsAsStringArray(Class<?> clazz){
        String[] Fields = Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .toArray(String[]::new);

        return Fields;
    }

    public static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        if(str.substring(0, 1).equals("i")){
            str = "ı" + str.substring(1);
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String getUrlString( Object obj ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] fields = StringUtils.getFilelsAsStringArray(obj.getClass());
        List<String> fieldValues = new ArrayList<>();
        int index = 0;
        for (String field : fields) {

            String methodName = "get" + StringUtils.capitalize(field);
            Method method2 = obj.getClass().getDeclaredMethod(methodName);
            Object returnVal = method2.invoke(obj);

            if(null != returnVal){
                if(returnVal instanceof  String){
                    String newVal = (String) returnVal;
                    fieldValues.add(field +"=" + newVal);
                }
            }
        }
        return String.join("&", fieldValues);
    }

}
