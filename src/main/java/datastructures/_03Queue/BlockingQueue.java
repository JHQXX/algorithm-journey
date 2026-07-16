package datastructures._03Queue;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/16 13:05
 * @description: 阻塞队列
 */
public interface BlockingQueue<E> extends Queue<E>{

    boolean add(E e);

    boolean offer(E e);

}
