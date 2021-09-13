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
            String key = getKey(json, currCharIndex);       //in currCharIndex = 1 // "
            currCharIndex = skipKey(key, currCharIndex);    //in currCharIndex = 1 // "

            currCharIndex = skipColon(currCharIndex);       //in currCharIndex = 12 // :

            Value value = getValue(json, currCharIndex);    //in currCharIndex = 13 // "
            currCharIndex = skipValue(value, currCharIndex);

            currCharIndex = skipComma(json, currCharIndex);

            map.put(key, value.getValue());
        }

        return map;
    }

    private static int skipKey(String key, Integer index) {
        return index + key.length() + 2;
    }

    private static int skipValue(Value value, Integer index) {
        return index + value.getStringLength();
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

    private static String getKey(String s, Integer index) {
        index = skipBrace(index);
        final int start = index;
        final int end = s.indexOf('"', start);

        return s.substring(start, end);
    }

    private static Value getValue(String s, Integer index) {
        int valStart = index; // in 13 "

        switch (s.charAt(valStart)) {
            case ('"') : {
                String value = parseStrValue(s, index);
                return new Value(value.length() + 2, value);
            }
            default: {
                throw new IllegalArgumentException("Json value type" +
                        " that start at symbol: " + valStart +
                        " not supported");
            }
        }

    }

    private static String parseStrValue(String s, Integer index) {
        index = skipBrace(index);   // in 13 "
        final int start = index;
        final int end = s.indexOf('"', start);

        return s.substring(start, end);
    }

    private static boolean hasNextPair(String s, Integer index) {
        return s.charAt(index) != '}';
    }

}

class Value {

    private int stringLength;
    private Object value;

    public Value(int stringLength, Object value) {
        this.stringLength = stringLength;
        this.value = value;
    }

    public int getStringLength() {
        return stringLength;
    }


    public Object getValue() {
        return value;
    }
}
