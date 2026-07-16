package datastructures._03Queue;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/16 05:53
 * @description: 双端队列的接口
 */
public interface Deque<E> extends Queue<E>{

    void addFirst(E  e);

    void addLast(E e);

}
