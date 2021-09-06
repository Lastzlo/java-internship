package task2_Stack;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CustomStackImplTest {

    @Test
    void push() throws NoSuchFieldException, IllegalAccessException {
        CustomStackImpl<Integer> stack = new CustomStackImpl<>();
        
        stack.push(123);
        stack.push(234);

        Class<? extends CustomStackImpl> stackClass = stack.getClass();
        Field declaredField = stackClass.getDeclaredField("data");
        declaredField.setAccessible(true);
        Object[] data = (Object[]) declaredField.get(stack);

        assertEquals(data[0],123);
        assertEquals(data[1],234);
    }

    @Test
    void pop() throws NoSuchFieldException, IllegalAccessException {
        CustomStackImpl<Integer> stack = new CustomStackImpl<>();

        for (int i = 0; i < 100; i++) {
            stack.push(i + 100);
        }

        assertEquals(stack.pop(), 199);

        Class<? extends CustomStackImpl> stackClass = stack.getClass();
        Field declaredField = stackClass.getDeclaredField("data");
        declaredField.setAccessible(true);
        Object[] data = (Object[]) declaredField.get(stack);

        assertEquals(data.length, 99);
        assertEquals(stack.pop(), 198);

        data = (Object[]) declaredField.get(stack);
        assertEquals(data.length, 98);

        while (! stack.isEmpty()) {
            stack.pop();
        }

        data = (Object[]) declaredField.get(stack);
        assertEquals(data.length, 0);

        assertNull(stack.pop());

    }

    @Test
    void peek() {
        CustomStackImpl<Integer> stack = new CustomStackImpl<>();

        stack.push(123);
        stack.push(234);

        assertEquals(stack.peek(), 234);
        assertEquals(stack.peek(), 234);
        assertEquals(stack.peek(), 234);
        assertEquals(stack.peek(), 234);
        assertEquals(stack.peek(), 234);
    }

    @Test
    void peek_whenEmpty_thenNull() {
        CustomStackImpl<Integer> stack = new CustomStackImpl<>();

        assertNull(stack.pop());
        assertNull(stack.pop());
        assertNull(stack.pop());
    }

    @Test
    void isEmpty() {
        CustomStackImpl<Integer> stack = new CustomStackImpl<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void isEmpty_whenPopAllValues() {
        CustomStackImpl<Integer> stack = new CustomStackImpl<>();

        stack.push(123);
        stack.push(234);

        assertEquals(stack.pop(), 234);
        assertEquals(stack.pop(), 123);

        assertTrue(stack.isEmpty());
        assertTrue(stack.isEmpty());
    }
}