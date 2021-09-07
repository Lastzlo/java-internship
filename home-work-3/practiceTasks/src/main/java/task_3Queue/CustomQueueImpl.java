package task_3Queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CustomQueueImpl<E> implements CustomQueue<E>{

    private Object[] data = new Object[0];
    private int count = 0;

    @Override
    public boolean offer(E e) {
        if(count == data.length) {
            data = Arrays.copyOf(data, count + 1);
        }
        data[count] = e;
        count++;
        return true;
    }

    @Override
    public E peek() {
        return isEmpty() ? null : (E)data[0];
    }

    @Override
    public E poll() {
        if(isEmpty()) return null;
        E value = (E)data[0];
        count--;
        data = Arrays.copyOfRange(data, 1, data.length);
        return value;
    }

    @Override
    public E element() {
        if (data.length == 0) throw new NoSuchElementException();
        return (E)data[0];
    }

    @Override
    public E remove() {
        if (data.length == 0) throw new NoSuchElementException();
        E value = (E)data[0];
        count--;
        data = Arrays.copyOfRange(data, 1, data.length);
        return value;
    }

    public boolean isEmpty() {
        return (count == 0);
    }
}
