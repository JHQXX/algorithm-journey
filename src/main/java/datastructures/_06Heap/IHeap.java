package datastructures._06Heap;

/**
 * @Author: Lee
 * @Description: 抽象接口
 * @DateTime: 2026/7/20 上午9:36
 **/
public interface IHeap<E> {

    boolean add(E e);

    boolean offer(E e);

    E poll();

    E peek();
}
