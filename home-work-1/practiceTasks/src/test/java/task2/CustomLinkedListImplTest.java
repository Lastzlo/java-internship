package task2;

import org.junit.jupiter.api.Test;

import java.util.*;

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
    void remove_whenRemoveNullValue() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        customLinkedList.add(null);
        customLinkedList.add("two");
        customLinkedList.add(null);
        customLinkedList.add("three");

        assert customLinkedList.remove(null) : "it should be true";
        assert (customLinkedList.getSize() == 3) : "it should be 3";

        assert customLinkedList.remove(null) : "it should be true";
        assert (customLinkedList.getSize() == 2) : "it should be 2";

    }

    @Test
    void getIterator_whenHasNext_thenFalse() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        Iterator<String> iterator = customLinkedList.getIterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void getIterator_whenHasNext_thenTrue() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();
        iterator.next();

        assertTrue(iterator.hasNext());
    }

    @Test
    void getIterator_whenNextHasNull_thenNoSuchElementException() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        Iterator<String> iterator = customLinkedList.getIterator();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void getIterator_whenNext_thenReturnValue() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();
        assertEquals(iterator.next(), "two");
    }

    @Test
    void getIterator_whenHasPrevious_thenFalse() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        ListIterator<String> iterator = customLinkedList.getIterator();

        assertFalse(iterator.hasPrevious());
    }

    @Test
    void getIterator_whenHasPrevious_thenTrue() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();
        iterator.next();

        assertTrue(iterator.hasPrevious());
    }

    @Test
    void getIterator_whenPreviousHasNull_thenNoSuchElementException() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();    //one

        assertEquals(iterator.previous(), "one");   //возвращает текущий элемент и переходит к предыдущему
        assertThrows(NoSuchElementException.class, ()-> iterator.previous()); //если нет предыдущего то генерируется исключение NoSuchElementException
    }

    @Test
    void getIterator_whenPrevious_thenReturnValue() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();    //one
        iterator.next();    //two

        assertEquals(iterator.previous(), "two");   //возвращает текущий элемент и переходит к предыдущему
        assertEquals(iterator.previous(), "one");   //возвращает текущий элемент и переходит к предыдущему
        assertEquals(iterator.next(), "one");       //возвращает текущий элемент и переходит к следующему
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

        assertTrue(resultList.contains("one"));
        assertTrue(resultList.contains("two"));
        assertTrue(resultList.contains("three"));
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

        assertTrue(resultList.contains("one"));
        assertTrue(resultList.contains("two"));
        assertTrue(resultList.contains("three"));
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
        assertTrue(resultList.contains("two"));
        assertTrue(resultList.contains("three"));


    }

    @Test
    void getIterator_whenRemoveButUsePrevious_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");

        ListIterator<String> iterator = customLinkedList.getIterator();
        assertEquals(iterator.next(), "one");
        assertEquals(iterator.next(), "two");
        assertEquals(iterator.next(), "three");

        assertEquals(iterator.previous(), "three");
        iterator.remove();  //удалили three

        assertEquals(2, customLinkedList.getSize());

        assertEquals(iterator.previous(), "two");
        iterator.remove();

        assertEquals(1, customLinkedList.getSize());
    }

    @Test
    void getIterator_whenRemove_thenIllegalStateException() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");

        ListIterator<String> iterator = customLinkedList.getIterator();

        assertThrows(IllegalStateException.class, ()-> iterator.remove()); //метод должен быть вызван после методов next() или previous(), иначе будет сгенерировано исключение IlligalStateException
        assertEquals(1, customLinkedList.getSize());
    }

}