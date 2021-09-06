package task1_ObjectMapper.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task1_ObjectMapper.dto.UserDTO;
import task1_ObjectMapper.pojo.User;

import java.util.Objects;
import java.util.Optional;

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
    void whenMap_thenCorrect() {
        UserTest userTest = new UserTest(12, "NIck", "url");
        UserDTO expectedUserDTO = new UserDTO(12, "NIck", "url");

        Optional<UserDTO> optionalUserDTO = ObjectMapper.map(userTest, UserDTO.class);
        Assertions.assertTrue(optionalUserDTO.isPresent());

        UserDTO actualDto = optionalUserDTO.get();
        Assertions.assertEquals(expectedUserDTO, actualDto);

    }


    @Test
    void whenMap_butInputClassHaveNotDtoField_thenOptionalEmpty() {
        Optional<UserDTO> optionalUserDTO = ObjectMapper.map(USER, UserDTO.class);
        Assertions.assertTrue(optionalUserDTO.isEmpty());
    }
}

class UserTest {

    private long id;
    private String nick;
    private String pictureUrl;

    public UserTest() {
    }

    public UserTest(long id, String nick, String pictureUrl) {
        this.id = id;
        this.nick = nick;
        this.pictureUrl = pictureUrl;
    }
}