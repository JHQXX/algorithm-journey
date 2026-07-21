package datastructures._06Heap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @Author: Lee
 * @Description: 堆-接口
 * @DateTime: 2026/7/20 上午9:36
 **/
public class Heap<E> implements IHeap<E> {

    private Logger logger = LoggerFactory.getLogger(Heap.class);

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    transient Object[] queue;

    //元素个数
    private int size = 0;

    //默认长度11
    public Heap() {
        queue = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    @Override
    public boolean offer(E e){
        if (e == null){
            throw new NullPointerException();
        }
        //拿取堆堆尾巴
        int i = size;
        if (i >= queue.length){
            //这个是扩容操作
            grow(i + 1);
        }
        //元素加一
        size = i +1;
        if (i == 0){
            queue[0] = e;
        }else {
            sifUp(i , e);
        }
        return  true;
    }

    private void grow (int minCapacity){
        int oldCapacity = queue.length;
        //Double size if small ; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1) );
        //overflow-conscious code
        if (newCapacity - Integer.MAX_VALUE - 8 > 0){
            newCapacity = (minCapacity > Integer.MAX_VALUE - 8)? Integer.MAX_VALUE : Integer.MAX_VALUE - 8;
            queue = Arrays.copyOf(queue,newCapacity);
        }

    }

    private void sifUp(int k ,E x){
        siftUpComparable(k , x);
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int k , E x){
        logger.info("入队");
        while (k >0){
            //防止负数出现
            int parent = (k - 1) >>>1;
            Object e = queue[parent];
            //最小堆 x > 0 compareTo自定义函数
            if (compareTo(x ,(E) e) > 0){
                break;
            }
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }


    @SuppressWarnings("unchecked")
    @Override
    public E poll() {
        if (size == 0){
            return null;
        }
        int s = --size;
        E result = (E)queue[0];
        E x = (E)queue[s];
        queue[s] = null;
        if (s != 0){
            siftDown(0,x);
        }
        return result;
    }

    private void siftDown(int k ,E x){
        siftDownComparable(k , x);
    }
    @SuppressWarnings("unchecked")
    private void siftDownComparable(int k , E x){
        //找中间节点
        int half = size >>>1;
        while (k < half){
            //找到左子节点 和 右子节点进行比较 找出最大的值
            int child = (k << 1) +1;
            Object c = queue[child];
            int right = child + 1;
            //左子节点与右子节点比较 取最小的节点
            if (right < size && compareTo((E) c , (E)queue[right]) > 0){
                c = queue[child = right];
            }
            //目标值与c比较 当目标值小于c值 退出循环 说明此时目标值在合适的位置 迁移完成
            if (compareTo(x,(E) c) <= 0){
                break;
            }
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

    @Override
    public E peek() {
        return (size == 0) ? null : (E)queue[0];
    }

    public int compareTo(E firstElement ,E secondElement){
        throw new RuntimeException("子类未实现 compareTo方法");
    }
}
