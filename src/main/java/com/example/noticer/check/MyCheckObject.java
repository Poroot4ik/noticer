package com.example.noticer.check;

import java.lang.reflect.Field;

public class MyCheckObject {

    //check object's fields for null except id
    static public boolean isObjectFieldsNull(Object object) {

        Class<? extends Object> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                if (field.get(object) == null && fieldName != "id") {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
