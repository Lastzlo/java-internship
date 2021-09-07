package task1_ObjectMapper.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task1_ObjectMapper.dto.CarDTO;
import task1_ObjectMapper.dto.UserDTO;
import task1_ObjectMapper.pojo.Car;
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

        UserDTO actualDto = ObjectMapper.map(userTest, UserDTO.class);
        Assertions.assertEquals(expectedUserDTO, actualDto);
    }

    @Test
    void whenMap_thenCorrect2() {
        Car car = new Car(
                new User(12, "url", "Jony", "jony123", "desc", "location", 123, 254),
                "BMW M3",
                61287,
                7.2d,
                "Wow"
        );
        CarDTO expected = new CarDTO(
                new User(12, "url", "Jony", "jony123", "desc", "location", 123, 254),
                "BMW M3",
                61287);


        CarDTO actualDto = ObjectMapper.map(car, CarDTO.class);
        Assertions.assertEquals(expected, actualDto);
    }

    @Test
    void whenMap_butInputClassWithAnnotation_thenCorrect() {
        UserWithAnnotation user = new UserWithAnnotation(12, "NIck", "url");
        UserDTO expectedUserDTO = new UserDTO(12, "NIck", "url");

        UserDTO actualDto = ObjectMapper.map(user, UserDTO.class);
        Assertions.assertEquals(expectedUserDTO, actualDto);
    }

    @Test
    void whenMap_butInputClassHasSameFieldAndAnnotatedField_thenRuntimeException() {
        UserWithAnnotation2 user = new UserWithAnnotation2(12, "Full_NAme", "NIck", "url");
        String expMessage = "java.security.InvalidKeyException: Input class has two fields with same name: nick";

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> ObjectMapper.map(user, UserDTO.class));
        Assertions.assertEquals(expMessage,exception.getMessage());
    }


    @Test
    void whenMap_butInputClassHaveNotDtoField_thenRuntimeException() {
        String expMessage = "java.lang.NoSuchFieldException: Input class don't declare field with name: pictureUrl";

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> ObjectMapper.map(USER, UserDTO.class));
        Assertions.assertEquals(expMessage,exception.getMessage());
    }

    @Test
    void whenMap_butDtoWithoutEmptyConstructor_thenRuntimeException() {
        String expMessage = "java.lang.NoSuchMethodException: Export class has not empty constructor";

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> ObjectMapper.map(USER, UserDtoWithoutEmptyConstructor.class));
        Assertions.assertEquals(expMessage,exception.getMessage());
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