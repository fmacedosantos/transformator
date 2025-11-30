package br.com.fmacedosantos.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Transformator {

    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> source = input.getClass();
        Class<?> target = Class.forName(source.getName() + "DTO");

        O targetClass = (O) target.getDeclaredConstructor().newInstance();

        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if (validate(sourceField, targetField)) {
                    try {
                        targetField.set(targetClass, sourceField.get(input));
                        break;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return targetClass;
    }

    private Boolean validate(Field sourceField, Field targetField) {
        if (!(sourceField.getName().equals(targetField.getName())
                && sourceField.getType().equals(targetField.getType()))) {
            return false;
        }

        sourceField.setAccessible(true);
        targetField.setAccessible(true);

        return true;
    }
}
