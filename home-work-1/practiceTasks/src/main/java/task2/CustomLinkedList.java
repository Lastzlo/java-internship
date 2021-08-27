package task2;

import java.util.Iterator;

public interface CustomLinkedList<E>{

    boolean add(E value);
    boolean remove(E value);

    int getSize();
    Iterator<E> getIterator();

}
