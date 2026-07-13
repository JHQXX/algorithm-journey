package datastructures._01Link_List;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/14 05:33
 * @description: List的通用实现类
 */
//todo 在接口上使用注释的用法是什么
//从设计规范的角度来看，如果我们要实现一个公用的方法，最好的形式是先做一个接口
public interface List<E>{

    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(E e);

    E get(int index);

    void printLinkList();

}
