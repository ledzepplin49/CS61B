package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    private static class IntAbscomparator<Integer> implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Math.abs((java.lang.Integer) o1) - Math.abs((java.lang.Integer)o2);
        }
    }
    @Test
    public void testMaxArrayDeque() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntAbscomparator());
        mad.addLast(-10);
        mad.addLast(5);
        assertEquals((Integer) (-10), mad.max());
    }
}
