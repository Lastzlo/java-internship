package task1_JsonMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

//    @Test
//    void toJSON() {
//    }


//    @Test
//    void fromJSON() {
//        String json = "{\"firstName\":\"Tom\",\"lastName\":\"Jackson\",\"gender\":\"male\"}";
//        TestClass expected = new TestClass("Tom", "Jackson", "male");
//        TestClass actual = JsonMapper.fromJSON(json, TestClass.class);
//
//        Assertions.assertEquals(expected.toString(), actual.toString());
//        Assertions.assertEquals(expected, actual);
//    }

//    @Test
//    void jsonToMapKeyValue() {
//
//    }

    @Test
    void removeBraces() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //give
        String json = "{\"firstName\":\"Tom\"}";
        String exp = "\"firstName\":\"Tom\"";

        //when
        String methodName = "removeBraces";
        Class<JsonMapper> aClass = JsonMapper.class;
        Method method = aClass.getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(null, json);

        //then
        assertEquals(json.length() - 2, actual.length());
        assertEquals(exp, actual);
        System.out.println("actual = " + actual);
    }
}

//class TestClass {
//    private String firstName;
//    private String lastName;
//    private String gender;
//
//    public TestClass() {
//    }
//
//    public TestClass(String firstName, String lastName, String gender) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.gender = gender;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TestClass testClass = (TestClass) o;
//        return firstName.equals(testClass.firstName) && lastName.equals(testClass.lastName) && gender.equals(testClass.gender);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(firstName, lastName, gender);
//    }
//
//    @Override
//    public String toString() {
//        return "TestClass{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", gender='" + gender + '\'' +
//                '}';
//    }
//}