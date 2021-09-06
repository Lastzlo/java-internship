package task_3Queue;

import java.util.NoSuchElementException;

public interface CustomQueue<E> {
    E element();

    boolean offer(E e);

    E peek();

    E poll();

    E remove();
}
