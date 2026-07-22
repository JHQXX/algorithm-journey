package datastructures._06Heap;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/22 09:07
 * @description: 最大堆
 */
public class MaxHeap extends Heap<Integer>{

    @Override
    public int compareTo(Integer firstElement, Integer secondElement) {
        return secondElement.compareTo(firstElement);
    }
}
