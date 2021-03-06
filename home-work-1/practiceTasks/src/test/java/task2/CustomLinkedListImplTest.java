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

        assertEquals(iterator.previous(), "one");   //???????????????????? ?????????????? ?????????????? ?? ?????????????????? ?? ??????????????????????
        assertThrows(NoSuchElementException.class, ()-> iterator.previous()); //???????? ?????? ?????????????????????? ???? ???????????????????????? ???????????????????? NoSuchElementException
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

        assertEquals(iterator.previous(), "two");   //???????????????????? ?????????????? ?????????????? ?? ?????????????????? ?? ??????????????????????
        assertEquals(iterator.previous(), "one");   //???????????????????? ?????????????? ?????????????? ?? ?????????????????? ?? ??????????????????????
        assertEquals(iterator.next(), "one");       //???????????????????? ?????????????? ?????????????? ?? ?????????????????? ?? ????????????????????
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
        iterator.remove();  //?????????????? three

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

        assertThrows(IllegalStateException.class, iterator::remove); //?????????? ???????????? ???????? ???????????? ?????????? ?????????????? next() ?????? previous(), ?????????? ?????????? ?????????????????????????? ???????????????????? IlligalStateException
        assertEquals(1, customLinkedList.getSize());
    }

    @Test
    void getIterator_whenNextIndex_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("one");
        customLinkedList.add("one");
        customLinkedList.add("one");

        ListIterator<String> iterator = customLinkedList.getIterator();

        assertEquals(iterator.next(), "one");
        assertEquals(iterator.nextIndex(), 2);
        assertEquals(iterator.next(), "one");
        assertEquals(iterator.nextIndex(), 3);

    }

    @Test
    void getIterator_whenNextIndexMoreThanSize_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("one");
        customLinkedList.add("one");
        customLinkedList.add("one");

        ListIterator<String> iterator = customLinkedList.getIterator();

        while (iterator.hasNext()) {
            iterator.next();
            assertTrue(iterator.nextIndex() <= customLinkedList.getSize());
        }

    }

    @Test
    void getIterator_whenPreviousIndex_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("one");
        customLinkedList.add("one");
        customLinkedList.add("one");

        ListIterator<String> iterator = customLinkedList.getIterator();

        assertEquals(iterator.next(), "one");
        assertEquals(iterator.previousIndex(), -1);
        assertEquals(iterator.next(), "one");
        assertEquals(iterator.previousIndex(), 1);
        assertEquals(iterator.next(), "one");
        assertEquals(iterator.previousIndex(), 2);
        iterator.previous();
        assertEquals(iterator.previousIndex(), 1);

    }

    @Test
    void getIterator_whenSet_thenIllegalStateException() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("one");

        ListIterator<String> iterator = customLinkedList.getIterator();

        assertThrows(IllegalStateException.class, ()-> iterator.set("bad"));
    }

    @Test
    void getIterator_whenSet_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");
        customLinkedList.add("three");
        customLinkedList.add("three");

        ListIterator<String> iterator = customLinkedList.getIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("two")) {
                iterator.set("new word");
                break;
            }
        }

        assertEquals(customLinkedList.getSize(), 5);
        customLinkedList.getIterator()
                .forEachRemaining((str) -> assertNotEquals(str, "two"));

    }

    @Test
    void getIterator_whenSet2_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");
        customLinkedList.add("four");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.previous();
        iterator.previous();    //three
        iterator.set("new word");

        assertEquals(customLinkedList.getSize(), 4);
        customLinkedList.getIterator()
                .forEachRemaining((str) -> assertNotEquals(str, "three"));

    }

    @Test
    void getIterator_whenSet3_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");
        customLinkedList.add("two");
        customLinkedList.add("three");
        customLinkedList.add("four");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();

        while (iterator.hasPrevious()) {
            iterator.previous();
        }
        iterator.set("new word");

        assertEquals(customLinkedList.getSize(), 4);
        customLinkedList.getIterator()
                .forEachRemaining((str) -> assertNotEquals(str, "one"));

    }

    @Test
    void getIterator_whenAdd_butCollectionEmpty_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.add("one");

        assertEquals(customLinkedList.getSize(), 1);
        assertFalse(iterator.hasNext());
        assertEquals(iterator.previous(), "one");
        assertFalse(iterator.hasPrevious());
    }

    @Test
    void getIterator_whenAdd_butNextNodeNotNull_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.add("two");

        assertEquals(customLinkedList.getSize(), 2);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "one");
    }

    @Test
    void getIterator_whenAdd_butNextNodeNull_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();
        iterator.add("two");

        assertEquals(customLinkedList.getSize(), 2);
        assertFalse(iterator.hasNext());
        assertEquals(iterator.previous(), "one");
    }

    @Test
    void getIterator_whenAdd_butPrevNodeNull_thenCorrect() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedListImpl<>();
        customLinkedList.add("one");

        ListIterator<String> iterator = customLinkedList.getIterator();
        iterator.next();//one
        iterator.previous(); //one
        iterator.add("two");

        assertEquals(customLinkedList.getSize(), 2);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "one");
    }
}