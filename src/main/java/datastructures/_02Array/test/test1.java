package datastructures._02Array.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/15 13:01
 * @description:
 */
public class test1 {
    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();

        ArrayList<String> strings = new ArrayList<>(3);
        strings.add("a");
        strings.add("b");
        strings.add("c");
        String s = strings.get(strings.size()-1);
        System.out.println(s);


    }
}
