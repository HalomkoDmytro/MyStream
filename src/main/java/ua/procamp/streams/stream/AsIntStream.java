package ua.procamp.streams.stream;

import ua.procamp.streams.function.IntBinaryOperator;
import ua.procamp.streams.function.IntConsumer;
import ua.procamp.streams.function.IntPredicate;
import ua.procamp.streams.function.IntToIntStreamFunction;
import ua.procamp.streams.function.IntUnaryOperator;
import ua.procamp.streams.utils.ArrayUtils;
import ua.procamp.streams.utils.DummySpliterator;
import ua.procamp.streams.utils.DummySpliteratorImpl;
import ua.procamp.streams.utils.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

public class AsIntStream implements IntStream {

    private static DummySpliterator spliterator;

    private AsIntStream() {
    }

    private AsIntStream(DummySpliterator spliterator) {
        this.spliterator = spliterator;
    }

    public static IntStream of(int... values) {
        spliterator = new DummySpliteratorImpl(values);

        return new AsIntStream(spliterator);
    }

    @Override
    public Double average() {
        long size = spliterator.getSize();

        return (double) (sum() / size);
    }

    @Override
    public Integer max() {

        spliterator.goToFirst();
        Node node = spliterator.getCur();
        Integer max = node.getItemValue();
        BinaryOperator<Integer> findMax = (i1, i2) -> i1.compareTo(i2) > 0 ? i1 : i2;

        while (node.getNext() != null) {
            max = findMax.apply(max, node.getNext().getItemValue());
            node = node.getNext();
        }

        return max;
    }

    @Override
    public Integer min() {

        spliterator.goToFirst();
        Node node = spliterator.getCur();
        Integer min = node.getItemValue();
        BinaryOperator<Integer> findMin = (i1, i2) -> i1.compareTo(i2) < 0 ? i1 : i2;

        while (node.getNext() != null) {
            min = findMin.apply(min, node.getNext().getItemValue());
            node = node.getNext();
        }

        return min;
    }

    @Override
    public long count() {
        return (long) spliterator.getSize();
    }

    @Override
    public Integer sum() {

        spliterator.goToFirst();
        Node node = spliterator.getCur();
        Integer sum = node.getItemValue();
        BinaryOperator<Integer> summary = (i1, i2) -> i1 + i2;

        while (node.getNext() != null) {
            sum = summary.apply(sum, node.getNext().getItemValue());
            node = node.getNext();
        }

        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {

        spliterator.goToFirst();
        Node node = spliterator.getCur();

        while (node != null) {
            if (!predicate.test(node.getItemValue())) {
                node = spliterator.remove(node);
            } else {
                node = node.getNext();
            }
        }

        return this;
    }

    @Override
    public void forEach(IntConsumer action) {
        Objects.requireNonNull(action);

        spliterator.forEachRemaining(action);
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        Objects.requireNonNull(mapper);

        spliterator.goToFirst();
        Node node = spliterator.getCur();

        while (node != null) {
            node.setItemValue(mapper.apply(node.getItemValue()));
            node = node.getNext();
        }

        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        Objects.requireNonNull(func);

        spliterator.goToFirst();
        Node node = spliterator.getCur();
        List<int[]> intStreamList = new ArrayList<>();

        while (node != null) {
            intStreamList.add(func.applyAsIntStream(node.getItemValue()).toArray());
            node = node.getNext();
        }

        int[] valueFromSubStream = {};
        for (int[] arr : intStreamList) {
            valueFromSubStream = ArrayUtils.joinArray(valueFromSubStream, arr);
        }

        return AsIntStream.of(valueFromSubStream);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        Objects.requireNonNull(op);

        spliterator.goToFirst();
        Node node = spliterator.getCur();

        while (node != null) {
            identity = op.apply(identity, node.getItemValue());
            node = node.getNext();
        }

        return identity;
    }

    @Override
    public int[] toArray() {

        int[] arr = new int[spliterator.getSize()];
        int index = 0;
        spliterator.goToFirst();
        Node node = spliterator.getCur();

        while (node != null) {
            arr[index++] = node.getItemValue();
            node = node.getNext();
        }

        return arr;
    }


}
