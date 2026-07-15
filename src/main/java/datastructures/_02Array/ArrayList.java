package datastructures._02Array;

import java.util.Arrays;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/15 12:51
 * @description: 数组
 */
public class ArrayList<E> implements List<E>{
    //默认初始化空间
    private static  final  int DEFAULT_CAPACITY = 10;

    //空元素
    private static final  Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    //ArrayList 元素数组缓存区
    transient Object[] elementData;

    //List集合元素数量
    private int size;

    //默认空元素
    public ArrayList(){
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public boolean add(E e) {
        //确保内部容量
        int minCapacity = size + 1;
        //如果开始是空元素
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            //和默认做比较
            minCapacity = Math.max(minCapacity,DEFAULT_CAPACITY);
        }
        //扩容判断
        if (minCapacity - elementData.length>0){
            int oldCapacity = elementData.length;
            //新的空间是 原来旧的空间 + 旧的空间/2
            int newCapacity = oldCapacity + (oldCapacity>>1);

            if (newCapacity - elementData.length < 0){
                newCapacity = minCapacity;
            }
            if (newCapacity - MAX_ARRAY_SIZE > 0){
                newCapacity = hugeCapacity(minCapacity);
            }
            elementData = Arrays.copyOf(elementData,newCapacity);
        }
        //添加元素
        elementData[size++] = e;
        return true;
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static int hugeCapacity(int minCapacity){
        if (minCapacity < 0){
            //溢出检查
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    @Override
    public E remove(int index) {
        E oldValue =(E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0){
            //从原始数组的某个位置，拷贝到目标对象的某个位置开始后的n个元素
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public E get(int index) {
        return (E)elementData[index];
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
