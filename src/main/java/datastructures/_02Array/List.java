package datastructures._02Array;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/15 12:50
 * @description: 定义规范接口
 */
public interface List<E> {

    boolean add(E e);

    E remove(int index);

    E get(int index);
}
