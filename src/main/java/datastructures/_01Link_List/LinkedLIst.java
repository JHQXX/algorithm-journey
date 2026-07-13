package datastructures._01Link_List;


/**
 * @Author: Lee
 * @Description: 链表
 * @DateTime: 2026/7/13 下午5:04
 **/
public class LinkedLIst<E> implements List<E>{

    //链表的长度
    transient int size = 0;
    //链表的第一个元素
    transient Node<E> first;
    //链表的最后一个元素
    transient Node<E> last;
    //无参构造
    public LinkedLIst() {
    }

    //头插法 新元素是第一个
    void linkFirst(E e){
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(e,f,null);
        first = newNode;
        if (f == null){
            last = newNode;
        }else {
            f.prev = newNode;
        }
        size++;
    }

    //尾插法 新元素是最后一个
    void linkLast(E e){
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e,null,l);
        last = newNode;
        if (l == null){
            first =newNode;
        }else {
            l.next = newNode;
        }
        size++;
    }


    @Override
    public boolean add(E e) {
        linkFirst(e);
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        linkFirst(e);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null){
            for (Node<E> x = first ; x != null ; x = x.next){
                if (x.data == null){

                }
            }
        }

        return false;
    }

    E unlink(Node<E> x){
        //我需要把这个从原来的链表中拆出来
        final E  e = x.data;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;
        //说明这是最前面那一个
        if (prev == null){
            first = next;
        }else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        }else {
            next.prev = prev;
            x.next = null;
        }
        x.data = null;
        size --;
        return e;

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void printLinkList() {

    }


    /**
     * ?表示不确定的Java类型
     * T表示 具体的一个Java类型
     * K V 分别代表Java键值中的key Value
     * E 代表Element
     * @param <E>
     */
    private static class Node<E>{
        E data;
        //自引用
        Node<E> next;
        Node<E> prev;
        //不存在无参构造
        public Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

}
