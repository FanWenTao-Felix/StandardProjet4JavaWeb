package com.kxw.example;

import java.util.*;

/**
 * Created by kingsonwu on 15/10/31.
 *
 *{<a href='http://ifeve.com/difference-between-super-t-and-extends-t-in-java/'>@link</a>}
 */
public class ExtendOrSuperT {

    /**
     * PECS

     请记住PECS原则：生产者（Producer）使用extends，消费者（Consumer）使用super。

     生产者使用extends
     如果你需要一个列表提供T类型的元素（即你想从列表中读取T类型的元素），你需要把这个列表声明成<? extends T>，比如List<? extends Integer>，因此你不能往该列表中添加任何元素。

     消费者使用super
     如果需要一个列表使用T类型的元素（即你想把T类型的元素加入到列表中），你需要把这个列表声明成<? super T>，比如List<? super Integer>，因此你不能保证从中读取到的元素的类型。

     即是生产者，也是消费者
     如果一个列表即要生产，又要消费，你不能使用泛型通配符声明列表，比如List<Integer>。
     */


    public static void main(String[] args) {
        Collections.copy(new ArrayList<Integer>(),new ArrayList<Integer>());
    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        int srcSize = src.size();
        if (srcSize > dest.size())
            throw new IndexOutOfBoundsException("Source does not fit in dest");

        if (srcSize < 10 ||
                (src instanceof RandomAccess && dest instanceof RandomAccess)) {
            for (int i=0; i<srcSize; i++)
                dest.set(i, src.get(i));
        } else {
            ListIterator<? super T> di=dest.listIterator();
            ListIterator<? extends T> si=src.listIterator();
            for (int i=0; i<srcSize; i++) {
                di.next();
                di.set(si.next());
            }
        }
    }
}
