package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.*;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T> {
        private Object[] elements;
        private int currentIndex;
        private int nextIndex;

        public RoundIterator() {
            elements = fillElements();
            for (int i = 0; i < size(); i++) {
                elements[i] = get(i);
            }
            currentIndex = -1;
            nextIndex = 0;
        }

        private Object[] fillElements() {
            Object[] result = new Object[size()];
            for (int i = 0; i < size(); i++) {
                result[i] = get(i);
            }
            return result;
        }

        private boolean checkSize() {
            return size() == elements.length;
        }

        @Override
        public boolean hasNext() {
            if (!checkSize()) {
                throw new ConcurrentModificationException();
            }
            return elements.length > 0;
        }

        @Override
        public T next() {
            if (!checkSize()) {
                throw new ConcurrentModificationException();
            }
            if (elements.length == 0) {
                throw new NoSuchElementException();
            }
            T next = (T) elements[nextIndex];
            currentIndex = nextIndex;
            nextIndex = (nextIndex + 1 < elements.length) ? nextIndex + 1 : 0;
            return next;
        }

        @Override
        public void remove() {
            if (!checkSize()) {
                throw new ConcurrentModificationException();
            }
            if (currentIndex == -1) {
                throw new IllegalStateException();
            }
            Solution.this.remove(currentIndex);
            nextIndex = (nextIndex > currentIndex) ? --nextIndex : 0;
            currentIndex = -1;
            elements = fillElements();
        }
    }
}
