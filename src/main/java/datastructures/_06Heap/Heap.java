package datastructures._06Heap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Lee
 * @Description: 堆-接口
 * @DateTime: 2026/7/20 上午9:36
 **/
public class Heap<E> implements IHeap<E> {

    private Logger logger = LoggerFactory.getLogger(Heap.class);

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    transient Object[] queue;

    private int size = 0;

    //默认长度11
    public Heap() {
        queue = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
