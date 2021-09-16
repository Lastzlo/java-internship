package QuestionTask;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {

        TreeMap<String, String> map = new TreeMap<>();

        map.put("BB", "Bogdan");
        map.put("AA", "Anton");
        map.put("SS", "Sasha");

        for (Map.Entry<String, String>
                entry : map.entrySet())
            System.out.println(
                    "[" + entry.getKey()
                            + ", " + entry.getValue() + "]");


        TreeSet<String> set = new TreeSet<String>();

        // Use add() method to add elements into the Set
        set.add("Bogdan");
        set.add("Anton");
        set.add("Sasha");

        // Displaying the TreeSet
        System.out.println("TreeSet: " + set);

        // Creating an iterator
        Iterator value = set.iterator();

        // Displaying the values after iterating through the set
        System.out.println("The iterator values are: ");
        while (value.hasNext()) {
            System.out.println(value.next());
        }

    }
}
