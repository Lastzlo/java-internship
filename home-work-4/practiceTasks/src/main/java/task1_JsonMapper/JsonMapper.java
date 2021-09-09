package task1_JsonMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonMapper {

    public static String toJSON(Object o) {
        return "";
    }

//    public static <T> T fromJSON(String json, Class<T> expClass) {
//        Map<String, String> jsonToMapKeyValue = jsonToMapKeyValue(json);
//        return null;
//    }

//    private static Map<String,String> jsonToMapKeyValue(String json) {
//        String line = removeBraces(json);
//        Map<String,String> keyValue = new HashMap<>();
//
//        int currIndex = 0;
//
//        boolean hasNextNode = hasNextNode(line, currIndex);
//        KeyValue key = getNext(line, currIndex);
//
//
//        return keyValue;
//    }

//    private static KeyValue getNext(String line, Integer index) {
//        return null;
//    }

    private static boolean hasNextNode(String line, Integer index) {
        if(line.toCharArray()[index] == '\"') return true;
        return false;
    }

//    private static class KeyValue {
//        private String key;
//        private String value;
//
//        public KeyValue(String key, String value) {
//            this.key = key;
//            this.value = value;
//        }
//
//        public String getKey() {
//            return key;
//        }
//
//        public String getValue() {
//            return value;
//        }
//    }

    private static String removeBraces(String json) {
        return json.substring(1, json.length() - 1);
    }

}
