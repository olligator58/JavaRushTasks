package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    int size;
    int filledLevel;
    int maxLevel;

    public CustomTree() {
        root = new Entry<>("0");
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    private Entry<String> addChild(Entry<String> parent, String childName) {
        Entry<String> child = new Entry<>(childName);
        child.level = parent.level + 1;
        child.parent = parent;
        if (parent.availableToAddLeftChildren) {
            parent.leftChild = child;
            parent.availableToAddLeftChildren = false;
        } else {
            parent.rightChild = child;
            parent.availableToAddRightChildren = false;
        }
        return child;
    }

    private Entry<String> findFirstAvailableParent() {
        Entry<String> result = findAvailabeByLevel(root, filledLevel);
        if (result == null) {
            filledLevel++;
            result = findAvailabeByLevel(root, filledLevel);
        }
        return result;
    }

    private Entry<String> findAvailabeByLevel(Entry<String> entry, int level) {
        Entry<String> result;
        if (entry == null) {
            return null;
        }

        if (entry.isAvailableToAddChildren() && entry.level == level) {
            result = entry;
            return result;
        }
        if (entry.level == level) {
            return null;
        }

        result = findAvailabeByLevel(entry.leftChild, level);
        if (result == null) {
            result = findAvailabeByLevel(entry.rightChild, level);
        }
        return result;
    }

    private Entry<String> findElementByName(Entry<String> entry, String name) {
        Entry<String> result;
        if (entry == null) {
            return null;
        }
        if (entry.elementName.equals(name)) {
            result = entry;
            return result;
        }
        result = findElementByName(entry.leftChild, name);
        if (result == null) {
            result = findElementByName(entry.rightChild, name);
        }
        return result;
    }

    private void recalcSizeAndMaxLevel() {
        size = 0;
        maxLevel = 0;
        calcSizeAndMaxLevel(root);
        size--;
    }

    private void calcSizeAndMaxLevel(Entry<String> entry) {
        if (entry == null) {
            return;
        }

        size++;
        if (entry.level > maxLevel) {
            maxLevel = entry.level;
        }
        calcSizeAndMaxLevel(entry.leftChild);
        calcSizeAndMaxLevel(entry.rightChild);
    }

    private void makeParentsAvailable(Entry<String> entry, int level) {
        if (entry == null) {
            return;
        }

        if (entry.level == level) {
            entry.availableToAddLeftChildren = true;
            entry.availableToAddRightChildren = true;
            return;
        }
        makeParentsAvailable(entry.leftChild, level);
        makeParentsAvailable(entry.rightChild, level);
    }

    @Override
    public boolean add(String s) {
        Entry<String> availableParent = findFirstAvailableParent();
        Entry<String> child = addChild(availableParent, s);
        if (child.level > maxLevel) {
            maxLevel = child.level;
        }
        size++;
        return true;
    }

    public String getParent(String s) {
        Entry<String> entryWithNameS = findElementByName(root, s);
        return (entryWithNameS != null) ? (entryWithNameS.parent != null) ? entryWithNameS.parent.elementName : null : null;
    }

    public void printTree(Entry<String> entry) {
        if (entry == null) {
            return;
        }
        System.out.print(entry.elementName + " ");
        printTree(entry.leftChild);
        printTree(entry.rightChild);
    }

    @Override
    public boolean remove(Object o) {
        if (o.getClass() != String.class) {
            throw new UnsupportedOperationException();
        }

        String elementName = (String) o;
        Entry<String> entryWithNameS = findElementByName(root, elementName);
        if (entryWithNameS == null) {
            return false;
        }
        Entry<String> parent = entryWithNameS.parent;
        entryWithNameS.parent = null;
        if (entryWithNameS == parent.leftChild) {
            parent.leftChild = null;
        } else {
            parent.rightChild = null;
        }
        recalcSizeAndMaxLevel();
        if (findAvailabeByLevel(root, filledLevel) == null && maxLevel <= filledLevel) {
            filledLevel = maxLevel;
            makeParentsAvailable(root, maxLevel);
        }
        return true;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        int level;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
