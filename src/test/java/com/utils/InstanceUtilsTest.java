package com.utils;

import com.models.request.Omdb;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class InstanceUtilsTest {

    @Test
    public void generateInstance() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Omdb omdb = InstanceUtils.generateInstance(Omdb.class);
//        System.out.println(omdb.getApikey());
       /* String propertyUtils = PropertyUtils.getInstance().getPropValue("Omdb.apikey");
        System.out.println(propertyUtils);*/
        System.out.println(StringUtils.getUrlString(omdb));

    }
}