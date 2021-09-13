package task2_JsonMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonMapper {

    public Map<String, Object> fromJSON(String json) {
        if(json.length() == 2) return new HashMap<>();
        return null;
    }


}
