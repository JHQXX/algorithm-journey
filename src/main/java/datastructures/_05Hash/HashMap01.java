package datastructures._05Hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/18 06:34
 * @description: 不完美哈希
 * 为什么说不完美，因为这里没处理哈希冲突，后一个元素的hash值大于7的时候，那么一定是覆盖前一个值的
 */
public class HashMap01 <K , V> implements  Map<K,V>{

    private final Logger log = LoggerFactory.getLogger(HashMap01.class);

    private final Object[] tab = new Object[8];


    @Override
    public void put(K key, V value) {
        //按位& 使得index最大值是 7
       int index = key.hashCode() & (tab.length -1);
        tab[index] = value;
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() & (tab.length -1 );
        return (V) tab[index];
    }
}
