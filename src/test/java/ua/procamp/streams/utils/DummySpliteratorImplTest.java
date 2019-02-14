package ua.procamp.streams.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DummySpliteratorImplTest {

    private DummySpliterator spliterator;

    @Before
    public void init() {
        spliterator = new DummySpliteratorImpl(-2, 0, 2, 4);
    }

    @Test
    public void goToFirst() {
        int expected = -2;
        spliterator.goToFirst();
        int actual = spliterator.getCur().getItemValue();
        assertEquals(expected, actual);
    }

    @Test
    public void getSize() {
        int expected = 4;
        int actual = spliterator.getSize();
        assertEquals(expected, actual);
    }

    @Test
    public void hasNest() {
        boolean expected = true;
        spliterator.goToFirst();
        assertEquals(expected, spliterator.hasNest());
    }

    @Test(expected = NullPointerException.class)
    public void hasNestAfterLast() {
        spliterator.goToFirst();
        spliterator.next().getNext().getNext().getNext().getNext();
    }

    @Test
    public void next() {
        int expected = 0;
        spliterator.goToFirst();
        int actual = spliterator.next().getItemValue();
        assertEquals(expected, actual);
    }

    @Test
    public void remove() {
        int expected = 0;
        spliterator.goToFirst();
        spliterator.remove(spliterator.getCur());
        int actual = spliterator.getCur().getItemValue();
        assertEquals(expected, actual);
    }

    @Test
    public void forEachRemaining() {
//        spliterator.forEachRemaining();
    }

    @Test
    public void doCurrent() {
    }
}