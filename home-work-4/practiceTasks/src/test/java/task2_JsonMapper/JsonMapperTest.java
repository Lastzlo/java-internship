package task2_JsonMapper;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    @Test
    void fromJSON_butJsonLength2_thenEmptyMap() {
        String json = "{}";

        Map<String, Object> map = new JsonMapper().fromJSON(json);
        assertTrue(map.isEmpty());
    }
}