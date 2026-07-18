package datastructures._05Hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;
import java.util.Map;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/18 07:30
 * @description: 布谷鸟哈希解决哈希冲突
 */
public class HashMap05ByCuckooHashing <K,V> extends AbstractMap<K,V>implements Map<K,V> {

    private final Logger logger = LoggerFactory.getLogger(HashMap05ByCuckooHashing.class);


    static final int DEFAULT_INITIAL_CAPACITY = 8;
    //1往右移动30位 也就是 2^30次方
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR =0.75f;

    transient Entry<K,V>[] table;
    transient int size;

    //扩容的阀值
    int threshold;

    final float loadFactor;

    final transient HashFunction<K> hash1;
    final transient HashFunction<K> hash2;

    static final Object NULL_KEY = new Object();

    public HashMap05ByCuckooHashing(float loadFactor, HashFunction<K> hash1, HashFunction<K> hash2) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
        this.hash1 = new DefaultHashFunction<>(2);
        this.hash2 = new DefaultHashFunction<>(3);
    }

    static class Entry<K,V> implements Map.Entry<K,V>{
        final K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final boolean equals(Object o){
            if (!(o instanceof Map.Entry)){
                return false;
            }
            Map.Entry e = (Map.Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();

            if (Objects.equals(k1 ,k2)){
                Object v1 = getValue();
                Object v2 = e.getValue();
                return Objects.equals(v1, v2);
            }
            return false;

        }

        @Override
        public K getKey() {
            return HashMap05ByCuckooHashing.unmaskNull(key);
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    //定义hash功能
    interface HashFunction<T>{
        int hash(Object key,int limit);
    }

    //null返回同一个元素
    static <T> T maskNull(T key){
        //map运行null作为key的关键
        return  key == null ? (T) NULL_KEY : key;
    }

    //是否返回的key是null
    static <T> T unmaskNull(T key){
        return  (key == NULL_KEY ? null : key);
    }

    //创建默认实现类
    static class DefaultHashFunction<T> implements HashFunction<T>{

        private static final Random ENGINE = new Random();
        private int rounds;

        public DefaultHashFunction(int rounds) {
            this.rounds = rounds;
        }

        @Override
        public int hash(Object key, int limit) {
            //基于这个hash设置随机的起点，可以理解为同样的随机因子，那么会有相同结果
            ENGINE.setSeed(key.hashCode());
            //随机范围 为 0 ～ limit-1的范围
            int h = ENGINE.nextInt(limit);
            //往下走的次数，可以理解随机次数
            for (int i = 1 ; i < this.rounds ; i++){
                h = ENGINE.nextInt(limit);
            }
            return h;
        }
    }

    //实现抽象类的
    public Set<Map.Entry<K,V>> entrySet(){
        Set<Map.Entry<K,V>> es = new HashSet<Map.Entry<K,V>>();
        for (Map.Entry<K, V> e : table) {
            if (e != null){
                es.add(e);
            }
        }
        return es;
    }

    @Override
    public V get(Object key) {
        //哪去一个对象
        Object k = maskNull(key);
        //在第一个hash表中寻找
        int hash = hash(hash1,k);

        Object k2 ;
        Entry<K, V> e = table[hash];
        //通过第一个Hash计算拿值
        if (e!=null && (((k2 = e.key)==k) || k.equals(k2))){
            return e.value;
        }
        //拿第二个hash
        hash = hash(hash2,k);
        e = table[hash];
        if (e!=null && (((k2 = e.key)==k) || k.equals(k2))){
            return e.value;
        }
        return null;
    }

    private int hash(HashFunction<K> func , Object key){
        return func.hash(key,table.length);
    }

    private boolean insertEntry(Entry<K,V> e){
        int count = 0;
        Entry<K ,V> current = e;
        int index = hash(hash1 , current.key);
        while (current != e || count < table.length){
            //找出原住民
            Entry<K,V> temp = table[index];
            //找到空的作为座位了
            if (temp == null){
                table[index] = current;
                return true;
            }
            //找到的位置不为空，则把原本的人踢出去
            table[index] = current;
            //使用原住民进行遍历 此刻 current != e 已经不成立了 但是 count < table.length 成立 目前容器的长度是 8
            current = temp;
            //使用原住民进行二次hash找它下家，一定是存在的，因为我们的设计一个原住民存在两个不同的空间 也就意味着，它会不断的遍历，直到count等于table.length 说明所有的空间都满了
            if (index == hash(hash1,current.key)){
                index = hash(hash2,current.key);
            }else {
                index = hash(hash1,current.key);
            }
            ++count;
        }
        return false;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public V put(K key, V value){
        return put(key,value,false);
    }

    private V put(K key, V value,boolean isRehash){
        Object k = maskNull(key);
        //调用父类的判断
        if (containsKey(k)){
            return null;
        }
        //判断是否需要扩容
        if (insertEntry(new Entry<K,V>( (K) k,value))){
            if (!isRehash){
                size++;
            }
            return null;
        }
        //扩容
        rehash(2 * table.length);
        //递归
        return put( (K) k,value);
    }

    private void rehash(int newCapacity){
        //旧的元素
        Entry<K,V>[] oldTable = table;
        //旧的长度
        int oldCapacity = oldTable.length;
        //防止溢出，因为扩容是2倍扩容 int的最大值是 2^31-1（最高位是符号位）
        if (oldCapacity >= MAXIMUM_CAPACITY){
            //调节扩容的判断为最高值
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry<K,V>[] newTable = new Entry[newCapacity];
        table = newTable;
        for (Entry<K, V> e : oldTable) {
            if (e != null){
                put(e.key,e.value,true);
            }
        }
        threshold = (int)(newCapacity * loadFactor);
    }

    public int size(){
        return size;
    }
}
