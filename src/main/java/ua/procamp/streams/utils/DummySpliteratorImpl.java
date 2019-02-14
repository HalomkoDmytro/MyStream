package ua.procamp.streams.utils;

import ua.procamp.streams.function.IntConsumer;

import java.util.Objects;

public class DummySpliteratorImpl implements DummySpliterator {

    private int size;
    private Node first;
    private Node last;
    private Node current;

    public DummySpliteratorImpl(int... values) {
        Objects.requireNonNull(values);
        size = 0;
        for (int i : values) {
            add(i);
        }
    }

    private void add(int i) {
        if (first == null) {
            first = new Node(null, i);
            last = first;
            current = first;
        } else {
            Node temp = new Node(last, i);
            last.setNext(temp);
            last = temp;
        }
        size++;
    }

    @Override
    public Node getCur() {
        return current;
    }

    @Override
    public void goToFirst() {
        current = first;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean hasNest() {
        if (current.getNext() != null) {
            return true;
        }
        return false;
    }

    @Override
    public Node next() {
        current = current.getNext();
        return current;
    }

    @Override
    public Node remove(Node toRemoveNode) {
        Objects.requireNonNull(toRemoveNode);

        if (size > 1) {
            if (toRemoveNode == first) {
                first = first.getNext();
                current = first;
                return current;
            } else if (toRemoveNode == last) {
                last = last.getNext();
                return null;
            } else {
                Node previous = toRemoveNode.getPrev();
                Node second = toRemoveNode.getNext();
                previous.setNext(second);
                second.setPrev(previous);
                current = second;
                return current;
            }
        }

        first = null;
        last = null;
        current = null;

        size--;
        return null;
    }

    @Override
    public void forEachRemaining(IntConsumer action) {
        Objects.requireNonNull(action);

        Node temp = first;
        while (temp != null) {
            action.accept(temp.getItemValue());
            temp = temp.getNext();
        }
    }

    @Override
    public void doCurrent(IntConsumer action) {
        Objects.requireNonNull(action);

        action.accept(current.getItemValue());
    }
}
