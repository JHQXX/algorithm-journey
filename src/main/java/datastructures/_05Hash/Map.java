package datastructures._05Hash;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/18 06:30
 * @description: Map接口
 */
public interface Map<K , V> {

    void put(K key , V value);

    V get(K key);
}
