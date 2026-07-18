package datastructures._05Hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/18 07:15
 * @description: 合并散列
 */
public class HashMap04ByCoalescedHashing<K , V>  implements Map<K,V>{


    private final Logger logger = LoggerFactory.getLogger(HashMap04ByCoalescedHashing.class);
    private final Node<K , V>[] tab = new Node[8];




    @Override
    public void put(K key, V value) {
        int index = key.hashCode() & (tab.length -1);
        if (tab[index] == null){
            tab[index] = new Node<>(key,value);
            return;
        }
        if (Objects.equals(tab[index].key,key)){
            tab[index] = new Node<>(key,value);
            return;
        }

        int cursor = tab.length -1;
        while (tab[cursor] != null && tab[cursor].key != key){
            --cursor;
        }

        tab[cursor] = new Node<>(key ,value);

        //将碰撞节点指向这个新的节点 这里是从碰撞节点开始，不断往后遍历，找一个空的indexOfNext
        while (tab[index].idxOfNext != 0){
            index = tab[index].idxOfNext;
        }

        tab[index].idxOfNext =cursor;
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() & (tab.length -1);
        if (tab[index] != null) {
            while (tab[index].idxOfNext != 0) {
                if (Objects.equals(tab[index].key, key)) {
                    return tab[index].value;
                } else {
                    index = tab[index].idxOfNext;
                }
            }
        }
        return null;
    }

    static class Node<K,V>{
        final K key;
        V value;
        int idxOfNext;

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

        public int getIdxOfNext() {
            return idxOfNext;
        }

        public void setIdxOfNext(int idxOfNext) {
            this.idxOfNext = idxOfNext;
        }
    }
}
