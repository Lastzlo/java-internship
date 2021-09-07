package task_3Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class CustomQueueImplTest {

    @Test
    void offer_thenTrue() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        Assertions.assertTrue(queue.offer(123));
        Assertions.assertTrue(queue.offer(456));
        Assertions.assertTrue(queue.offer(789));

        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    void isEmpty_butQueueIsEmpty_thenTrue() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    void isEmpty_butQueueNotEmpty_thenFalse() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();
        queue.offer(123);
        Assertions.assertFalse(queue.isEmpty());
    }


    @Test
    void peek_butQueueIsEmpty_thenNull() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        queue.offer(123);
        queue.remove();

        Assertions.assertTrue(queue.isEmpty());
        Integer value = queue.peek();
        assertNull(value);
    }

    @Test
    void peek_thenHeadValue() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        queue.offer(123);
        queue.offer(234);
        queue.offer(345);

        Integer value = queue.peek();
        Assertions.assertEquals(value, 123);
    }

    @Test
    void poll_butQueueIsEmpty_thenNull() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        queue.offer(123);
        queue.offer(456);

        assertEquals(queue.poll(), 123);
        assertEquals(queue.poll(), 456);
        Assertions.assertTrue(queue.isEmpty());

        assertNull(queue.poll());
    }

    @Test
    void poll_thenHeadValue() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        queue.offer(123);
        queue.offer(456);

        assertEquals(queue.poll(), 123);
        assertEquals(queue.poll(), 456);
    }

    @Test
    void element_butQueueIsEmpty_thenNoSuchElementException() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        queue.offer(123);
        queue.remove();

        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertThrows(NoSuchElementException.class, queue::element);
    }

    @Test
    void element_thenHeadValue() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        queue.offer(123);
        queue.offer(234);
        queue.offer(345);

        Integer value = queue.element();
        Assertions.assertEquals(value, 123);
    }

    @Test
    void remove_butQueueIsEmpty_thenNoSuchElementException() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        queue.offer(123);
        queue.remove();

        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertThrows(NoSuchElementException.class, queue::remove);
    }

    @Test
    void remove_thenHeadValue() {
        CustomQueueImpl<Integer> queue = new CustomQueueImpl<>();

        queue.offer(123);
        queue.offer(234);
        queue.offer(345);
        Integer value = queue.remove();

        Assertions.assertEquals(value, 123);
    }

    @Test
    void Queue_remove_review() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(123);
        Integer remove = queue.remove();
        System.out.println("remove = " + remove);

        Assertions.assertThrows(NoSuchElementException.class, queue::remove);
    }

    @Test
    void Arrays_copyOfRange_review() {

        int[] array = {10, 11, 12, 13};
        System.out.println(Arrays.toString(array));

        int[] copy = Arrays.copyOfRange(array, 0, 1);
        System.out.println(Arrays.toString(copy));

        copy = Arrays.copyOfRange(array, 0, 2);
        System.out.println(Arrays.toString(copy));

        copy = Arrays.copyOfRange(array, 1, 2);
        System.out.println(Arrays.toString(copy));

    }
}