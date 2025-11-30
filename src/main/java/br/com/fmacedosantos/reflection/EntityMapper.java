package br.com.fmacedosantos.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class EntityMapper {

    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> source = input.getClass();
        Class<?> target = Class.forName(source.getName() + "DTO");

        O targetClass = (O) target.getDeclaredConstructor().newInstance();

        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if (areFieldsCompatible(sourceField, targetField)) {
                    try {
                        sourceField.setAccessible(true);
                        targetField.setAccessible(true);

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

    private boolean areFieldsCompatible(Field source, Field target) {
        return source.getName().equals(target.getName())
                && source.getType().equals(target.getType());
    }
}
