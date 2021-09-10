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

    public static Map<String, Object> fromJSON(String json) {
        return parseObject(json, 0);
    }

    private static Map<String, Object> parseObject(String json, Integer index) {
        int currCharIndex = index + 1;

        Map<String, Object> map = new HashMap<>();
        while (hasNextPair(json, currCharIndex)) {
            currCharIndex = skipComma(json, currCharIndex);
            currCharIndex = skipBrace(currCharIndex);

            String key = getKeyString(json, currCharIndex);
            currCharIndex = skipKey(key, currCharIndex);

            currCharIndex = skipBrace(currCharIndex);
            currCharIndex = skipColon(currCharIndex);

            currCharIndex = skipBrace(currCharIndex);

            String valueString = getValueString(json, currCharIndex);
            currCharIndex = skipKey(valueString, currCharIndex);

            currCharIndex = skipBrace(currCharIndex);

//
//            Object value = parseValue(valueString);
//            map.put(key, value);
            map.put(key, valueString);
        }

        return map;
    }

    private static String getValueString(String s, Integer index) {
        final int start = index;
        final int end = s.indexOf('"', start);

        return s.substring(start, end);
    }

    private static int skipKey(String key, Integer index) {
        return index + key.length();
    }

    private static int skipColon(Integer index) {
        return index + 1;
    }

    private static int skipComma(String s, Integer index) {
        if(s.charAt(index) == ',') return index + 1;
        return index;
    }

    private static int skipBrace(Integer index) {
        return index + 1;
    }

    private static String getKeyString(String s, Integer index) {
        final int start = index;
        final int end = s.indexOf('"', start);

        return s.substring(start, end);
    }

//    private static KeyValue getNext(String line, Integer index) {
//
//        String key = getKey
//        int keyStart = index;
//        int keyEnd = line.indexOf('\"',1);
//
//        String key = line.substring(keyStart, keyEnd);
//
//        int valueStart = keyEnd + 2;
//
//        String value =  getValue(line, valueStart);
//
//
//
//        int valueEnd = line.indexOf('\"',1);
//
//
//        //PriorityQueue priorityQueue
//        return null;
//    }

//    private static String getValue(String line, int valueStart) {
//        char startSymbol = line.charAt(valueStart);
//
//        getEnd
//
//        return "";
//    }

    private static boolean hasNextPair(String s, Integer index) {
        return s.charAt(index) != '}';
    }

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
//        public int getLength() {
//            return 1 + key.length() + 3 + value.length() + 1;
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
