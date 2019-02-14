package ua.procamp.streams.stream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsIntStreamTest {

    private IntStream asIntStream;

    @Before
    public void init(){
        asIntStream = AsIntStream.of(1, 2, 3);
    }

    @Test
    public void average() {
        double expected = 2.0;
        double actual = asIntStream.average();
        assertEquals(0, Double.compare(expected, actual));
    }

    @Test
    public void max() {
        int expected = 3;
        int actual = asIntStream.max();
        assertEquals(expected, actual);
    }

    @Test
    public void min() {
        int expected = 1;
        int actual = asIntStream.min();
        assertEquals(expected, actual);
    }

    @Test
    public void count() {
        long expected = 3;
        long actual = asIntStream.count();
        assertEquals(expected, actual);
    }

    @Test
    public void sum() {
        int expected = 6;
        int actual = asIntStream.sum();
        assertEquals(expected, actual);
    }

    @Test
    public void filter() {
        int expected = 1;
        long actuatl = asIntStream.filter(x -> x < 2).count();
        assertEquals(expected, actuatl);
    }

}