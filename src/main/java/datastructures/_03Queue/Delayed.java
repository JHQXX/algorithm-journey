package datastructures._03Queue;

import java.util.concurrent.TimeUnit;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/16 13:02
 * @description: 延迟队列接口
 */
public interface Delayed extends Comparable<Delayed>{

    long getDelay(TimeUnit unit);
}
