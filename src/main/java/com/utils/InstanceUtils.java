package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class InstanceUtils {

    public static  <T> T generateInstance(Class<T> clazz, String fileName){
        T instance = null;
        try {
            instance = clazz.newInstance();
            Properties props = PropertyUtils.getInstance(fileName);
            for (Field declaredField : clazz.getDeclaredFields()) {
                String fieldName = declaredField.getName();
                String propValue = props.getProperty(clazz.getSimpleName() + "." + fieldName);

                String methodName = "set" + StringUtils.capitalize(fieldName);
                Method method2 = clazz.getDeclaredMethod(methodName, String.class);

                method2.invoke(instance,propValue );
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }


}
