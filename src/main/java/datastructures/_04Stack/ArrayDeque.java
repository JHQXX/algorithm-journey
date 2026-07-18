package datastructures._04Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/17 05:52
 * @description: 使用队列来实现先进后出的/后进先出
 */
public class ArrayDeque<E>  implements  Deque<E>{

    private final Logger logger = LoggerFactory.getLogger(ArrayDeque.class);


    /**
     * 存储双端队列元素的数组。双端队列的容量就是这个数组的长度，它总是 2 的幂。
     * 数组永远不允许变满，除非在 addX 方法中短暂地在变满后立即调整大小（请参阅 doubleCapacity），
     * 从而避免头部和尾部环绕以彼此相等。我们还保证所有不包含双端队列元素的数组单元始终为空。
     */
    transient Object[] elements;

    /**
     * 双端队列头部元素索引(将被 remove() 或 pop()删除的元素) 如果双端队列为空 则等于tail的任意数字
     */
    transient int head;

    /**
     * 将下一个元素添加到双端队列尾部的索引(通过addLast(E) add(E) push(E))
     */
    transient int tail;

    /**
     * 无参构造 默认初始化容量为16
     */
    public ArrayDeque() {
        this.elements = new Object[16];
    }

    /**
     * 双端队列的容量翻倍 仅在满时调用 即头部和尾部缠绕相等时
     */
    private void doubleCapacity(){
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p;
        int newCapacity = n << 1;
        if (newCapacity < 0){
            throw new IllegalStateException("Capacity overflow");
        }
        Object[] a = new Object[newCapacity];

        /*
         * src      - 源数组
         * srcPos   – 源数组中的起始位置
         * dest     - 目标数组
         * destPos  – 目标数据中的起始位置
         * length   – 要复制的数组元素的数量
         */
        System.arraycopy(elements,p,a,0,r);
        System.arraycopy(elements,0,a,r,p);
        elements = a;
        head = 0;
        tail = n;
    }

    @Override
    public void push(E e) {
        if (e == null){
            throw new NullPointerException();
        }
        elements[head = (head-1) & (elements.length -1)] = e;
        logger.info("push index head : {} ", head);
        if (head == tail){
            doubleCapacity();
        }
    }

    @Override
    public E pop() {
        int h = head;
        @SuppressWarnings("unchecked")
        E result = (E)elements[h];
        if (result == null){
            return null;
        }
        elements[h] = null;
        head = (h + 1) & (elements.length -1);
        logger.info("pop.idx {} = {} & {}", head, Integer.toBinaryString(h + 1), Integer.toBinaryString(elements.length - 1));
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }
}
