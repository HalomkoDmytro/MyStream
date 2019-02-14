package ua.procamp.streams.utils;


import ua.procamp.streams.function.IntConsumer;

public interface DummySpliterator {

    int getSize();

    boolean hasNest();

    Node next();

    Node remove(Node myNode);

    Node getCur();

    void goToFirst();

    void forEachRemaining(IntConsumer action);

    void doCurrent(IntConsumer action);
}
