package task1_JsonMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonMapper {

    public static String toJSON(Object o) {
        return "";
    }

    public static <T> T fromJSON(String json, Class<T> expClass) {
        Map<String, String> jsonToMapKeyValue = jsonToMapKeyValue(json);
        return null;
    }

    private static Map<String,String> jsonToMapKeyValue(String json) {
        String removeBraces = removeBraces(json);

        Map<String,String> keyValue = new HashMap<>();

        return keyValue;
    }

    private static String removeBraces(String json) {
        return json.substring(1, json.length() - 1);
    }

}
