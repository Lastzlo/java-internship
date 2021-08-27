package task2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class CustomLinkedListImpl <E> implements CustomLinkedList<E> {

    private Node<E> first;
    private Node<E> last;

    private int size = 0;

    public CustomLinkedListImpl() {
    }

    private void linklast(E value) {
        final Node<E> bufferLast = last;
        final Node<E> newNode = new Node<>(last, value, null);
        last = newNode;
        if(bufferLast == null) {
            first = newNode;
        } else {
            bufferLast.next = newNode;
        }
        size++;
    }

    private void unlink(Node<E> node) {
        final Node<E> bufferPrev = node.prev;
        final Node<E> bufferNext = node.next;

        if(bufferPrev == null) {
            first = bufferNext;
        } else {
            bufferPrev.next = bufferNext;
            node.prev = null;   //очистили ссылку
        }

        if(bufferNext == null) {
            last = bufferPrev;
        } else {
            bufferNext.prev = bufferPrev;
            node.next = null; //очистили ссылку
        }

        size--;
    }

    @Override
    public boolean add(E value) {
        linklast(value);
        return true;
    }

    @Override
    public boolean remove(E removeValue) {
        for (Node<E> i = first; i != null; i = i.next) {
            if(removeValue.equals(i.value)) {
                unlink(i);
                return true;
            }
        }

        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> getIterator() {
        return new IteratorImpl();
    }

    private static class Node<E> {
        E value;
        Node<E> next = null;
        Node<E> prev = null;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private class IteratorImpl implements Iterator<E> {

        private Node<E> nextNode;
        private Node<E> prevNode;

        // hasModified means that we can remove value from collection
        // hasModified set TRUE only after next() or previous(), or would be
        // generated throws IllegalStateException
        private boolean hasModified;

        public IteratorImpl() {
            this.nextNode = first;
            this.hasModified = false;
        }

        @Override
        public boolean hasNext() {
            return this.nextNode != null;
        }

        @Override
        public E next() {
            if(nextNode == null) throw new NoSuchElementException();
            prevNode = nextNode;
            nextNode = nextNode.next;

            hasModified = true;
            return prevNode.value;
        }

        @Override
        public void remove() {
            if(!hasModified) throw new IllegalStateException();
            unlink(prevNode);
            hasModified = false;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

}
