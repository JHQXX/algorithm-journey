package datastructures._04Stack;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/17 05:50
 * @description: 双端队列抽象接口
 */
public interface Deque<E> {

    void push(E e);

    E pop();

    boolean isEmpty();

}
