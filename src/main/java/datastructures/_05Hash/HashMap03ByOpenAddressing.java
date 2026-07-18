package datastructures._05Hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/18 07:08
 * @description: 开放寻址
 */
public class HashMap03ByOpenAddressing <K,V> implements  Map<K,V>{

    private final Logger logger = LoggerFactory.getLogger(HashMap03ByOpenAddressing.class);
    private final Node<K , V>[] tab = new Node[8];



    @Override
    public void put(K key, V value) {
        int index = key.hashCode() & (tab.length - 1);
        if (tab[index] == null){
            tab[index] = new Node<>(key,value);
        }else {
            for (int i = index ; i < tab.length ; i++ ){
                if (tab[i] == null){
                    tab[i] = new Node<>(key,value);
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int index =key.hashCode() & (tab.length -1);
        if (tab[index] == null){
            for (int i = index ; i < tab.length ; i++){
                if (tab[i] != null){
                    if (tab[i].key.equals(key)){
                        return tab[i].value;
                    }
                }
            }
            return null;
        }else {
            return tab[index].value;
        }
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
