package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        private Object[] elements = getElements();
        private int currentIndex = -1;
        private int nextIndex;
        private int expectedModCount = modCount;

        private Object[] getElements() {
            Object[] result = null;
            try {
                Field elements = Solution.this.getClass().getSuperclass().getDeclaredField("elementData");
                elements.setAccessible(true);
                result = (Object[]) elements.get(Solution.this);
            } catch (Exception ignored) {
            }
            return result;
        }

        private void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            return size() > 0;
        }

        @Override
        public T next() {
            checkForComodification();
            if (size() == 0) {
                throw new NoSuchElementException();
            }
            T next = (T) elements[nextIndex];
            currentIndex = nextIndex;
            nextIndex = (nextIndex + 1 < size()) ? nextIndex + 1 : 0;
            return next;
        }

        @Override
        public void remove() {
            checkForComodification();
            if (currentIndex == -1) {
                throw new IllegalStateException();
            }
            try {
                Solution.this.remove(currentIndex);
                nextIndex = (nextIndex > currentIndex) ? --nextIndex : 0;
                currentIndex = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
