package task1_JsonMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
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
    void String_indexOf() {
        String line = "\"firstName\":\"Tom\"";

        char[] chars = line.toCharArray();
        int index1 = line.indexOf('\"');
        int index2 = line.indexOf('\"', 1);

        assertEquals(0, index1);
        String key = line.substring(1, line.indexOf('\"',1));
        assertEquals(10, index2);
        assertEquals("firstName", key);

    }

    @Test
    void hasNextPair() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //give
        int currCharIndex = 1;
        String json = "{\"firstName\":\"Tom\"}";

        //when
        String methodName = "hasNextPair";
        Class<JsonMapper> aClass = JsonMapper.class;
        Method method = aClass.getDeclaredMethod(methodName, String.class, Integer.class);
        method.setAccessible(true);
        Boolean hasNextPair = (Boolean) method.invoke(null, json, currCharIndex);

        //then
        System.out.println("hasNextPair = " + hasNextPair);
        assertTrue(hasNextPair);
    }

    @Test
    void getKeyString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //give
        int currCharIndex = 2;
        String json = "{\"firstName\":\"Tom\"}";

        //when
        String methodName = "getKeyString";
        Class<JsonMapper> aClass = JsonMapper.class;
        Method method = aClass.getDeclaredMethod(methodName, String.class, Integer.class);
        method.setAccessible(true);
        String key = (String) method.invoke(null, json, currCharIndex);

        //then
        System.out.println("key = " + key);
        assertEquals("firstName", key);
    }

    @Test
    void parseObject() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //give
        int currCharIndex = 0;
        String json = "{\"firstName\":\"Tom\"}";

        //when
        String methodName = "parseObject";
        Class<JsonMapper> aClass = JsonMapper.class;
        Method method = aClass.getDeclaredMethod(methodName, String.class, Integer.class);
        method.setAccessible(true);
        Map<String, Object> map = (Map<String, Object>) method.invoke(null, json, currCharIndex);

        //then
        assertEquals(1, map.size());
        assertEquals("Tom", map.get("firstName"));
    }

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

    @Test
    void hasNextNode_thenTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //give
        String line = "\"firstName\":\"Tom\"";
        int index = 0;

        //when
        String methodName = "hasNextNode";
        Class<JsonMapper> aClass = JsonMapper.class;
        Method method = aClass.getDeclaredMethod(methodName, String.class, Integer.class);
        method.setAccessible(true);

        Assertions.assertTrue((Boolean) method.invoke(null, line, index));
    }

    @Test
    void hasNextNode_thenFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //give
        String line = "firstName\":\"Tom\"";
        int index = 0;

        //when
        String methodName = "hasNextNode";
        Class<JsonMapper> aClass = JsonMapper.class;
        Method method = aClass.getDeclaredMethod(methodName, String.class, Integer.class);
        method.setAccessible(true);

        Assertions.assertFalse((Boolean) method.invoke(null, line, index));
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