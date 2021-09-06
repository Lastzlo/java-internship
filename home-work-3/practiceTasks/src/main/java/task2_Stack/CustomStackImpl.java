package task2_Stack;

import java.util.Arrays;

public class CustomStackImpl <E> implements CustomStack<E> {

    private Object[] data;
    private int count = 0;

    public CustomStackImpl() {
        this(10);
    }

    public CustomStackImpl(int initCount) {
        if(initCount < 0) throw new IllegalArgumentException("count should be more than 0");
        data = new Object[initCount];
    }

    @Override
    public void push(E value) {
        if(count == data.length) {
            data = Arrays.copyOf(data, count + 1);
        }
        data[count] = value;
        count++;
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;

        count--;
        E value = (E) data[count];
        data = Arrays.copyOfRange(data, 0, count);
        return value;
    }

    @Override
    public E peek() {
        return (isEmpty()) ? null : (E) data[count - 1];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
