package task2;

import java.util.ListIterator;

public interface CustomLinkedList<E>{

    boolean add(E value);
    boolean remove(Object o);

    int getSize();
    ListIterator<E> getIterator();

}
