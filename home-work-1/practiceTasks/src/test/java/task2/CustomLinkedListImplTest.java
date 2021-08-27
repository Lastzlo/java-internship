package task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListImplTest {

    @Test
    void add() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        assert customLinkedList.add("one") : "it should be true";
        assert customLinkedList.add("two") : "it should be true";
        assert customLinkedList.add("three") : "it should be true";

    }

    @Test
    void getSize() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        customLinkedList.add("one");
        customLinkedList.add("one");
        customLinkedList.add("one");

        assert (customLinkedList.getSize() == 3) : "it should be 3";
    }

    @Test
    void remove() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        assert customLinkedList.remove("two") : "it should be true";
        assert (customLinkedList.getSize() == 2) : "it should be 2";

        assert customLinkedList.remove("three") : "it should be true";
        assert (customLinkedList.getSize() == 1) : "it should be 1";



    }

    @Test
    void getIterator_whenHasNext_thenFalse() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        Iterator<String> iterator = customLinkedList.getIterator();

        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void getIterator_whenNextHasNull_thenNoSuchElementException() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        Iterator<String> iterator = customLinkedList.getIterator();

        Assertions.assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void getIterator_whenListContain3Elements_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        List<String> resultList = new ArrayList<>();

        Iterator<String> iterator = customLinkedList.getIterator();
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }

        Assertions.assertTrue(resultList.contains("one"));
        Assertions.assertTrue(resultList.contains("two"));
        Assertions.assertTrue(resultList.contains("three"));
        assertEquals(3, resultList.size());
    }

    @Test
    void getIterator_whenForEachRemaining_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        List<String> resultList = new ArrayList<>();

        Iterator<String> iterator = customLinkedList.getIterator();
        iterator.forEachRemaining(resultList::add);

        Assertions.assertTrue(resultList.contains("one"));
        Assertions.assertTrue(resultList.contains("two"));
        Assertions.assertTrue(resultList.contains("three"));
        assertEquals(3, resultList.size());
    }

    @Test
    void getIterator_whenRemove_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        Iterator<String> iterator = customLinkedList.getIterator();
        iterator.hasNext();
        iterator.next();
        iterator.remove(); //"one"

        assertEquals(2, customLinkedList.getSize());


        List<String> resultList = new ArrayList<>();
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        Assertions.assertTrue(resultList.contains("two"));
        Assertions.assertTrue(resultList.contains("three"));


    }
}