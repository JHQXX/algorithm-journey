package datastructures._03Queue;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/16 05:52
 * @description: 队列的抽象接口
 */
public interface Queue<E> {

    boolean add(E e);

    boolean offer(E e);

    E poll();

    E peek();

}
