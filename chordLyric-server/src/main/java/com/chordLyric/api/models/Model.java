package com.chordLyric.api.models;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Model<T> implements Serializable {

    public abstract T getId();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(newLine);
        result.append("Object {");
        result.append(newLine);

        Field[] fields = this.getClass().getDeclaredFields();

        AccessibleObject.setAccessible(fields, true);

        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                result.append("    ");
                try {
                    result.append(field.getName());
                    result.append(": ");

                    Object object = field.get(this);

                    if (object instanceof Model) {
                        result.append(((Model) field.get(this)));
//                        result.append(((Model) field.get(this)).getId());
                    } else {
                        result.append(field.get(this));
                    }
                } catch (IllegalAccessException e) {
                    log.error("Try to access private properties: {}", e);
                }
                result.append(newLine);
            }
        }

        result.append("}");

        return result.toString();
    }
}
