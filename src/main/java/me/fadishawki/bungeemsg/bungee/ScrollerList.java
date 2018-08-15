package me.fadishawki.bungeemsg.bungee;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import java.util.Arrays;
import java.util.List;

public class ScrollerList<T> implements Cloneable {

    private List<T> list;
    private int index;

    public ScrollerList(T... params) {
        this(Arrays.asList(params));
    }

    public ScrollerList(List<T> list) {
        this.list = list;
        this.index = 0;
    }

    public T next() {
        index++;

        if (list.size() == index)
            index = 0;

        return list.get(index);
    }

    public boolean hasNext() {
        return list.size() != index + 1;
    }

    public T get() {
        return list.get(index);
    }

    public void setList(T... params) {
        setList(Arrays.asList(params));
    }

    public void setList(List<T> list) {
        this.list = list;
        this.index = 0;
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}
