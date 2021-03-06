package task1_JsonMapper;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonMapperTest {

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
        String json = "{\"firstName\":\"Tom\",\"lastName\":\"Jones\"}";

        //when
        String methodName = "parseObject";
        Class<JsonMapper> aClass = JsonMapper.class;
        Method method = aClass.getDeclaredMethod(methodName, String.class, Integer.class);
        method.setAccessible(true);
        Map<String, Object> map = (Map<String, Object>) method.invoke(null, json, currCharIndex);

        //then
        assertEquals(2, map.size());
        assertEquals("Tom", map.get("firstName"));
        assertEquals("Jones", map.get("lastName"));
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