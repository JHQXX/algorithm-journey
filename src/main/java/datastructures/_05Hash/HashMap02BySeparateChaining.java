package datastructures._05Hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/18 06:46
 * @description: 分离法/拉链法解决哈希碰撞
 * 确实可以解决哈希碰撞，但是如果碰撞的很多，那么对于拿取而言，就是一个噩梦，而且由于动态因子是8，也就是只有8个
 * 槽位，其实我感觉很容易发生碰撞，如果发生碰撞，那么就会在链表中堆积，对于拿取而言，链表也不是一个最好的选择，需要不断的循环遍历
 * 直到能拿到
 *
 */
public class HashMap02BySeparateChaining <K,V> implements Map<K,V>{

    private final Logger logger = LoggerFactory.getLogger(HashMap02BySeparateChaining.class);
    private final LinkedList<Node<K,V>>[] tab = new LinkedList[8];


    @Override
    public void put(K key, V value) {
        int index = key.hashCode() & (tab.length -1);
        if (tab[index] == null){
            tab[index] = new LinkedList<>();
            tab[index].add(new Node<>(key,value));
        }else {
            tab[index].add(new Node<>(key,value));
        }
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() & (tab.length -1);
        if (tab[index] == null){
            return null;
        }else {
            LinkedList<Node<K, V>> nodes = tab[index];
            for (Node<K,V> node : nodes){
                if (node.key.equals(key)){
                    return node.value;
                }
            }
        }
        return null;
    }


    static class Node<K,V>{
        final K key;
        V value;

        public Node(K key ,V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }
    }
}
