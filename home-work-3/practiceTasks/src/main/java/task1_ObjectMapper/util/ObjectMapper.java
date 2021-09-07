package task1_ObjectMapper.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapper {

    public static <T> T map(Object inputObj, Class<T> expClass) {
        T expInstance = getInstance(expClass);

        Map<String, Object> mapNamesAndFields = getMapFieldsNameAndValues(inputObj);

        Field[] expClassFields = expClass.getDeclaredFields();
        for(Field expField : expClassFields) {
            String expFieldName = expField.getName();

            if (! mapNamesAndFields.containsKey(expFieldName)) {
                try {
                    throw new NoSuchFieldException(
                            "Input class don't declare field with name: " + expFieldName);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            }

            Object inputFieldValue = mapNamesAndFields.get(expFieldName);
            try {
                expField.setAccessible(true);
                expField.set(expInstance, inputFieldValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return expInstance;
    }

    private static <T> T getInstance(Class<T> expClass) {
        final T expInstance;
        try {
            Constructor<?> emptyConstructor =
                    getEmptyConstructor(expClass.getConstructors());

            emptyConstructor.setAccessible(true);
            expInstance = (T) emptyConstructor.newInstance();
        } catch (InstantiationException
                | InvocationTargetException
                | IllegalAccessException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return expInstance;
    }

    private static Constructor<?> getEmptyConstructor(Constructor<?>[] allConstructors) throws NoSuchMethodException {
        for(Constructor<?> ctor : allConstructors) {
            if (ctor.getParameterTypes().length == 0 ) {
                return ctor;
            }
        }

        throw new NoSuchMethodException("Export class has not empty constructor");
    }

    private static Map<String, Object> getMapFieldsNameAndValues(Object inputObj) {
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = inputObj.getClass().getDeclaredFields();

        for(Field field : declaredFields) {
            String fieldName;

            if (field.isAnnotationPresent(Ignore.class)) continue;

            if (field.isAnnotationPresent(NewName.class)) {
                NewName newName = field.getAnnotation(NewName.class);
                fieldName = newName.name();
            } else {
                fieldName = field.getName();
            }

            if(map.containsKey(fieldName)) {
                try {
                    throw new InvalidKeyException(
                            "Input class has two fields with same name: " + fieldName);
                } catch (InvalidKeyException e) {
                    throw new RuntimeException(e);
                }
            }

            field.setAccessible(true);
            try {
                Object value = field.get(inputObj);
                map.put(fieldName, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

        return map;
    }



}
