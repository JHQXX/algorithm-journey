package datastructures._06Heap;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/22 09:08
 * @description: 最小堆
 */
public class MinHeap extends Heap<Integer>{


    @Override
    public int compareTo(Integer firstElement, Integer secondElement) {
        return firstElement.compareTo(secondElement);
    }
}
