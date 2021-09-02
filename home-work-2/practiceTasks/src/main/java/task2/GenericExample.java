package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GenericExample {
    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("text");
        list.add(111);

        List<Integer> integerList = new ArrayList<>();

        integerList.add(222);
        integerList.add(111);
    }
}
