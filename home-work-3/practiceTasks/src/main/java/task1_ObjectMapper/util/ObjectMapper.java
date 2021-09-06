package task1_ObjectMapper.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ObjectMapper {

    public static <T> Optional<T> map(Object importObject, Class<T> expectedType) {
        Constructor ctor;
        try {
            ctor = getEmptyConstructor(expectedType.getConstructors());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        T export;
        try {
            export = (T)ctor.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }


        Class<?> importClass = importObject.getClass();
        Field[] fromFields = importClass.getDeclaredFields();

        Map<String, String> fromFieldsNames = getFromFieldsNewNames(fromFields);

        Field[] expFields = expectedType.getDeclaredFields();

        for(Field f : expFields) {
            f.setAccessible(true);

            String expFieldName = f.getName();
            if (! fromFieldsNames.containsKey(expFieldName)) {
                try {
                    throw new NoSuchFieldException("Import class don't declare field with name: " + expFieldName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    return Optional.empty();
                }

                //continue;
            }


            String importFieldName = fromFieldsNames.get(expFieldName);
            Field declaredField;
            try {
                declaredField = importClass.getDeclaredField(importFieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return Optional.empty();
            }
            declaredField.setAccessible(true);

            try {
                f.set(export, declaredField.get(importObject));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        return Optional.of(export);
    }

    private static Constructor getEmptyConstructor(Constructor<?>[] allConstructors) throws NoSuchFieldException {
        for(Constructor ctor : allConstructors) {
            if (ctor.getParameterTypes().length == 0) return ctor;
        }
        throw new NoSuchFieldException("Export class has not EMPTY CONCTRUCTOR");
    }

    private static Map<String, String> getFromFieldsNewNames(Field[] fromFields) {
        Map<String, String> oldName_newNameMap = new HashMap<>();
        for(Field f : fromFields) {
            oldName_newNameMap.put(f.getName(), f.getName());
        }
        return  oldName_newNameMap;
    }



}
