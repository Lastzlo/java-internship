package task2;

import java.util.Iterator;

public interface CustomLinkedList<E>{

    boolean add(E value);
    boolean remove(Object o);

    int getSize();
    Iterator<E> getIterator();

}
