package task1_ObjectMappper.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task1_ObjectMappper.dto.UserDTO;
import task1_ObjectMappper.pojo.User;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperTest {

    final private static User USER;

    static {
        USER = new User(
                1L,
                "https://i.imgur.com/PxYbP0Z.jpeg",
                "KeanuReeves",
                "neo",
                "Sample description",
                "USA",
                42,
                42
        );
    }

    @Test
    void map() {

        UserDTO expectedDto = UserDTO.fromUser(USER);
        UserDTO actualDto = ObjectMapper.map(USER, UserDTO.class);

        System.out.println(expectedDto);
        Assertions.assertEquals(expectedDto, actualDto);

    }
}