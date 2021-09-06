package task1_ObjectMapper.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task1_ObjectMapper.dto.UserDTO;
import task1_ObjectMapper.pojo.User;

import java.util.Optional;


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
    void whenMap_butInputClassWithAnnotation_thenCorrect() {
        UserWithAnnotation user = new UserWithAnnotation(12, "NIck", "url");
        UserDTO expectedUserDTO = new UserDTO(12, "NIck", "url");

        Optional<UserDTO> optionalUserDTO = ObjectMapper.map(user, UserDTO.class);
        Assertions.assertTrue(optionalUserDTO.isPresent());

        UserDTO actualDto = optionalUserDTO.get();
        Assertions.assertEquals(expectedUserDTO, actualDto);

    }

    @Test
    void whenMap_butInputClassHasSameFieldAndAnnotatedField_thenOptionalEmpty() {
        UserWithAnnotation2 user = new UserWithAnnotation2(12, "Full_NAme", "NIck", "url");

        Optional<UserDTO> optionalUserDTO = ObjectMapper.map(user, UserDTO.class);
        Assertions.assertTrue(optionalUserDTO.isEmpty());

    }


    @Test
    void whenMap_butInputClassHaveNotDtoField_thenOptionalEmpty() {
        Optional<UserDTO> optionalUserDTO = ObjectMapper.map(USER, UserDTO.class);
        Assertions.assertTrue(optionalUserDTO.isEmpty());
    }

    @Test
    void whenMap_butDtoWithoutEmptyConstructor_thenOptionalEmpty() {
        Optional<UserDtoWithoutEmptyConstructor> optionalUserDTO =
                ObjectMapper.map(USER, UserDtoWithoutEmptyConstructor.class);
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

class UserDtoWithoutEmptyConstructor {

    private long id;
    private String nick;
    private String pictureUrl;

    public UserDtoWithoutEmptyConstructor(long id, String nick, String pictureUrl) {
        this.id = id;
        this.nick = nick;
        this.pictureUrl = pictureUrl;
    }
}

class UserWithAnnotation {

    private long id;
    @NewName(name = "nick")
    private String nickkk;

    @NewName(name = "pictureUrl")
    private String picture;

    @Ignore
    private String someInfo;

    public UserWithAnnotation() {
    }

    public UserWithAnnotation(long id, String nick, String pictureUrl) {
        this.id = id;
        this.nickkk = nick;
        this.picture = pictureUrl;
    }
}

class UserWithAnnotation2 {

    private long id;
    @NewName(name = "nick")
    private String fullName;

    private String nick;

    @NewName(name = "pictureUrl")
    private String pictureUrl;

    public UserWithAnnotation2() {
    }

    public UserWithAnnotation2(long id, String fullName, String nick, String pictureUrl) {
        this.id = id;
        this.fullName = fullName;
        this.nick = nick;
        this.pictureUrl = pictureUrl;
    }
}