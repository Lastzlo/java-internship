package task1_JsonMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonMapper {

    public static Map<String, Object> fromJSON(String json) {
        if(json.length() == 2) return new HashMap<>();
        return parseObject(json, 0);
    }

    private static Map<String, Object> parseObject(String json, Integer index) {
        int currCharIndex = index + 1;

        Map<String, Object> map = new HashMap<>();
        while (hasNextPair(json, currCharIndex)) {
            currCharIndex = skipBrace(currCharIndex);

            String key = getKeyString(json, currCharIndex);
            currCharIndex = skipKey(key, currCharIndex);

            currCharIndex = skipBrace(currCharIndex);
            currCharIndex = skipColon(currCharIndex);

            currCharIndex = skipBrace(currCharIndex);

            String valueString = getValueString(json, currCharIndex);
            currCharIndex = skipKey(valueString, currCharIndex);

            currCharIndex = skipBrace(currCharIndex);

            currCharIndex = skipComma(json, currCharIndex);


            Object value = parseValue(valueString);
            map.put(key, value);
        }

        return map;
    }

    private static Object parseValue(String valueString) {
        return null;
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

    private static boolean hasNextPair(String s, Integer index) {
        return s.charAt(index) != '}';
    }

}
