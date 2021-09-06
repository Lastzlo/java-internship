package task1_ObjectMapper.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ObjectMapper {

    public static <T> Optional<T> map(Object inputObj, Class<T> exportClass) {

        Constructor<?> ctor;
        try {
            ctor = getEmptyConstructor(exportClass.getConstructors());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return Optional.empty();
        }


        T exportInst;
        try {
            exportInst = (T) ctor.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        Map<String, Object> inputNameFieldsAndValues = getInputFieldsValues(inputObj);


        Field[] expFields = exportClass.getDeclaredFields();
        for(Field f : expFields) {

            String expFieldName = f.getName();
            if (! inputNameFieldsAndValues.containsKey(expFieldName)) {
                try {
                    throw new NoSuchFieldException(
                            "Input class don't declare field with name: " + expFieldName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }

            try {
                f.setAccessible(true);
                f.set(exportInst, inputNameFieldsAndValues.get(expFieldName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        return Optional.of(exportInst);
    }

    private static Map<String, Object> getInputFieldsValues(Object inputObj) {
        Map<String, Object> resultMap = new HashMap<>();
        Field[] declaredFields = inputObj.getClass().getDeclaredFields();

        for(Field f : declaredFields) {
            try {
                f.setAccessible(true);
                resultMap.put(f.getName(), f.get(inputObj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    private static Constructor<?> getEmptyConstructor(Constructor<?>[] allConstructors) throws NoSuchFieldException {
        for(Constructor<?> ctor : allConstructors) {
            if (ctor.getParameterTypes().length == 0) return ctor;
        }
        throw new NoSuchFieldException("Export class has not EMPTY CONSTRUCTOR");
    }



}
