package task1_ObjectMapper.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ObjectMapper {

    public static <T> Optional<T> map(Object inputObj, Class<T> exportClass) {
        T exportInst;
        try {
            Constructor<?> ctor = getEmptyConstructor(exportClass.getConstructors());

            try {
                exportInst = (T) ctor.newInstance();
            } catch (InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
                return Optional.empty();
            }

            Map<String, Object> inputNameFieldsAndValues =
                    getInputFieldsValues(inputObj);

            Field[] expFields = exportClass.getDeclaredFields();
            for(Field f : expFields) {

                String expFieldName = f.getName();
                if (! inputNameFieldsAndValues.containsKey(expFieldName)) {
                    throw new NoSuchFieldException(
                            "Input class don't declare field with name: " + expFieldName);
                }

                f.setAccessible(true);
                f.set(exportInst, inputNameFieldsAndValues.get(expFieldName));
            }


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(exportInst);
    }

    private static Map<String, Object> getInputFieldsValues(Object inputObj) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> resultMap = new HashMap<>();
        Field[] declaredFields = inputObj.getClass().getDeclaredFields();

        for(Field f : declaredFields) {

            if (f.isAnnotationPresent(Ignore.class)) continue;

            f.setAccessible(true);
            Object o = f.get(inputObj);

            String fieldName;
            if(f.isAnnotationPresent(NewName.class)) {
                NewName newName = f.getAnnotation(NewName.class);
                fieldName = newName.name();
            } else {
                fieldName = f.getName();
            }

            if(resultMap.containsKey(fieldName)) throw new NoSuchFieldException("Input class has fields with same name");

            resultMap.put(fieldName, o);

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
