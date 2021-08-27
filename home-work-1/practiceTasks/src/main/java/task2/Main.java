package task2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> stringLinkedList = new LinkedList<>();

        Iterator<String> iterator = stringLinkedList.iterator();

        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }

        stringLinkedList.add("one");
        stringLinkedList.add("two");
        stringLinkedList.add("three");

        iterator = stringLinkedList.iterator();

        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }

    }


}
